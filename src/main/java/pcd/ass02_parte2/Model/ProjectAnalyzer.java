package pcd.ass02_parte2.Model;

import io.vertx.core.*;
import pcd.ass02_parte2.Controller.ControllerView;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte2.Model.Reports.ClassReport;
import pcd.ass02_parte2.Model.Reports.InterfaceReport;
import pcd.ass02_parte2.Model.Reports.PackageReport;
import pcd.ass02_parte2.Model.Reports.ProjectReport;

import java.io.FileNotFoundException;

public interface ProjectAnalyzer {

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file.
	 *
	 * @param srcInterfacePath The path of the interface to be analyzed.
	 * @return The Future related to the InterfaceReport.
	 */
	Future<InterfaceReport> getInterfaceReport(String srcInterfacePath) throws FileNotFoundException;



	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file.
	 *
	 * @param srcClassPath The path of the class to be analyzed.
	 * @return The Future related to the ClassReport.
	 */
	Future<ClassReport> getClassReport(String srcClassPath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a package,
	 * given the full path of the package folder
	 *
	 * @param srcPackagePath The path of the package to be analyzed.
	 * @return The Future related to the PackageReport.
	 */
	Future<PackageReport> getPackageReport(String srcPackagePath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a project
	 * given the full path of the project folder
	 *
	 * @param srcProjectFolderPath The path of the project to be analyzed.
	 * @return The Future related to the ProjectReport.
	 */
	Future<ProjectReport> getProjectReport(String srcProjectFolderPath);

	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * sending on the channel each time a project element is found and printing in the console.
	 *
	 * @param srcProjectFolderName The path of the project to be analyzed.
	 * @param vertx 			   The Vertx instance.
	 * @param topic 			   The name of the channel.
	 */
	void analyzeProject(String srcProjectFolderName, Vertx vertx, String topic);

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
    void analyzeProjectWithGUI(String srcProjectFolderName, Vertx vertx, Monitor stopFlag, ControllerView controllerView, String topic);
}
