package pcd.ass02_parte2.Controller;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd.ass02_parte2.Controller.Collectors.ClassCollector;
import pcd.ass02_parte2.Controller.Collectors.InterfaceCollector;
import pcd.ass02_parte2.Controller.Collectors.PackageCollector;
import pcd.ass02_parte2.Controller.Collectors.ProjectCollector;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte2.Model.Reports.*;
import pcd.ass02_parte2.Model.ProjectAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the open-source library with the async methods required.
 *
 */
public class Library implements ProjectAnalyzer {

	private final Vertx vertx;
	private CompilationUnit cu;

	/**
	 * Creates a new Library.
	 *
	 * @param vertx The Vertx instance.
	 */
	public Library(Vertx vertx) {
		this.vertx = vertx;
	}

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file.
	 *
	 * @param srcInterfacePath The path of the interface to be analyzed.
	 * @return The Future related to the InterfaceReport.
	 */
	@Override
	public Future<InterfaceReport> getInterfaceReport(String srcInterfacePath){
		Future<InterfaceReport> fut = vertx.executeBlocking(promise -> {

			InterfaceCollector interfaceCollector = new InterfaceCollector();
			interfaceCollector.getInterfaceReport().setInterfacePath(srcInterfacePath);

			try {
				CompilationUnit cu = StaticJavaParser.parse(new File(srcInterfacePath));
				interfaceCollector.visit(cu, null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			promise.complete(interfaceCollector.getInterfaceReport());
		});

		return fut;
	}

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file.
	 *
	 * @param srcClassPath The path of the class to be analyzed.
	 * @return The Future related to the ClassReport.
	 */
	@Override
	public Future<ClassReport> getClassReport(String srcClassPath) {
		Future<ClassReport> fut = vertx.executeBlocking(promise -> {

			ClassCollector classCollector = new ClassCollector();
			classCollector.getClassReport().setClassPath(srcClassPath);

			try {
				CompilationUnit cu = StaticJavaParser.parse(new File(srcClassPath));
				classCollector.visit(cu, null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			promise.complete(classCollector.getClassReport());
		});

		return fut;
	}

	/**
	 * Async method to retrieve the report about a package,
	 * given the full path of the package folder
	 *
	 * @param srcPackagePath The path of the package to be analyzed.
	 * @return The Future related to the PackageReport.
	 */
	@Override
	public Future<PackageReport> getPackageReport(String srcPackagePath) {
		Future<PackageReport> fut = vertx.executeBlocking(promise -> {

			PackageCollector packageCollector = new PackageCollector();
			File file = new File(srcPackagePath);
			packageCollector.getPackageReport().setPackageName(file.getName());

			for (final File fileEntry : Objects.requireNonNull(file.listFiles())){
				if(!fileEntry.isHidden()){
					if (!fileEntry.isFile()) {
						packageCollector.getPackageReport().addPackage(fileEntry.getName());
					} else if(fileEntry.getName().endsWith(".java")){
						visitClassOrInterface(fileEntry.getPath(), packageCollector);
					}
				}
			}
			promise.complete(packageCollector.getPackageReport());
		});
		return fut;
	}

	/**
	 * Visit the class/interface related to the specified path, create a new
	 * ClassCollector/InterfaceCollector and add it in the specified PackageCollector.
	 *
	 * @param path   		   The file path (class/interface).
	 * @param packageCollector The instance of PackageCollector.
	 */
	private void visitClassOrInterface(String path, PackageCollector packageCollector) {
		try {
			cu = StaticJavaParser.parse(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface()) {
			InterfaceCollector interfaceCollector = new InterfaceCollector();
			interfaceCollector.getInterfaceReport().setInterfacePath(path);
			interfaceCollector.visit(cu, null);
			packageCollector.getPackageReport().addInterfaceReport(interfaceCollector.getInterfaceReport());
		} else {
			ClassCollector classCollector = new ClassCollector();
			classCollector.getClassReport().setClassPath(path);
			classCollector.visit(cu, null);
			packageCollector.getPackageReport().addClassReport(classCollector.getClassReport());
		}
	}

	/**
	 * Async method to retrieve the report about a project
	 * given the full path of the project folder
	 *
	 * @param srcProjectFolderPath The path of the project to be analyzed.
	 * @return The Future related to the ProjectReport.
	 */
	@Override
	public Future<ProjectReport> getProjectReport(String srcProjectFolderPath){
		Future<ProjectReport> fut = vertx.executeBlocking(promise -> {

			ProjectCollector projectCollector = new ProjectCollector();
			PackageCollector packageCollector = new PackageCollector();

			File file = new File(srcProjectFolderPath);

			projectCollector.getProjectReport().setProjectName(file.getName());
			packageCollector.getPackageReport().setPackageName(file.getName());
			projectCollector.getProjectReport().setMainClass(getMainClassName());
			projectCollector.getProjectReport().addPackageReport(packageCollector.getPackageReport());

			try {
				visitProject(projectCollector, packageCollector, file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			promise.complete(projectCollector.getProjectReport());
		});
		return fut;
	}

	/**
	 * Return the name of the Main Class if present.
	 *
	 * @return The name of the Main Class.
	 */
	private String getMainClassName() {
		for (final Map.Entry<String, String> entry : System.getenv().entrySet())
			if (entry.getKey().startsWith("JAVA_MAIN_CLASS")){
				return entry.getValue();
			}
		return "";
	}

	/**
	 * Visit the specified project and for every file calls the visitClassOrInterface() method,
	 * for every directory:
	 * 			- Creates a new PackageCollector;
	 * 			- Sets the package name;
	 * 			- Adds the new PackageCollector to the specified @projectCollector.
	 * 			- Calls this method recursively specifying the new PackageCollector.
	 *
	 * @param projectCollector The istance of ProjectCollector.
	 * @param packageCollector The istance of PackageCollector.
	 * @param file			   The file to be analyzed.
	 */
	private void visitProject(ProjectCollector projectCollector, PackageCollector packageCollector, File file) throws IOException {
		for (final File fileEntry : Objects.requireNonNull(file.listFiles())){
			if(!fileEntry.isHidden()){
				if (!fileEntry.isFile()) {
					PackageCollector packageCollector1 = new PackageCollector();
					packageCollector1.getPackageReport().setPackageName(fileEntry.getName());
					projectCollector.getProjectReport().addPackageReport(packageCollector1.getPackageReport());
					visitProject(projectCollector, packageCollector1, fileEntry);
				} else if(fileEntry.getName().endsWith(".java")){
					visitClassOrInterface(fileEntry.getPath(), packageCollector);
				}
			}
		}
	}

	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * sending on the channel each time a project element is found and printing in the console.
	 *
	 * @param srcProjectFolderName The path of the project to be analyzed.
	 * @param vertx 			   The Vertx instance.
	 * @param topic 			   The name of the channel.
	 */
	@Override
	public void analyzeProject(String srcProjectFolderName, Vertx vertx, String topic) {
		vertx.executeBlocking(promise -> vertx.deployVerticle(new AgentReceiver(topic), res -> vertx.deployVerticle(new AgentSender(srcProjectFolderName, topic))));
	}

	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * sending on the channel each time a project element is found and printing in the GUI.
	 *
	 * @param srcProjectFolderName The path of the project to be analyzed.
	 * @param vertx 			   The Vertx instance.
	 * @param stopFlag 			   The Monitor's variable.
	 * @param controllerView 	   The ControllerView instance.
	 * @param topic 			   The name of the channel.
	 */
	@Override
	public void analyzeProjectWithGUI(String srcProjectFolderName, Vertx vertx, Monitor stopFlag, ControllerView controllerView, String topic) {
		vertx.executeBlocking(promise -> vertx.deployVerticle(new AgentReceiver(controllerView, topic), res -> vertx.deployVerticle(new AgentSender(srcProjectFolderName, stopFlag, topic))));
	}
}
