package pcd.ass02_parte2;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import pcd.ass02_parte2.Controller.ControllerView;
import pcd.ass02_parte2.Controller.Library;
import pcd.ass02_parte2.Model.Reports.ClassReport;
import pcd.ass02_parte2.Model.Reports.InterfaceReport;
import pcd.ass02_parte2.Model.Reports.PackageReport;
import pcd.ass02_parte2.Model.Reports.ProjectReport;
import pcd.ass02_parte2.View.View;

public class Main {

	public static void main(String[] args){

		// TEST FOR GUI (point B).
		View view = new View();
		ControllerView controllerView = new ControllerView(view);
		view.setController(controllerView);


		// TEST FOR POINT A.
		Vertx vertx = Vertx.vertx();
		Library library = new Library(vertx);


		// TESTING REPORT FOR A CLASS.
		Future<ClassReport> fut = library.getClassReport("src/main/java/pcd/ass02_parte2/Controller/Library.java");
		fut.onComplete(e -> {
			System.out.println("\n------------------------ Start Analyze Class ------------------------");
			System.out.println("Class name: " + e.result().getClassName());
			System.out.println("Class path: " + e.result().getClassPath());
			System.out.println("Information about fields:\n");
			e.result().getFieldsInfo().forEach(n -> System.out.println("\t- " + n.getModifier()
					+ "" + n.getType() + " " + n.getName()));
			System.out.println("\nInformation about methods:\n");
			e.result().getMethodsInfo().forEach(n -> {
				System.out.println("\t- Methods name: " + n.getName() + "()");
				System.out.println("\t- Visibility: " + n.getVisibility());
				System.out.println("\t- Return type: " + n.getType());
				System.out.println("\t- Parameters: " + n.getParameters());
				System.out.println("\t- Start line: " + n.getSrcBeginLine());
				System.out.println("\t- End line: " + n.getEndBeginLine()+"\n");
			});
			System.out.println("------------------------ End Analyze Class ------------------------\n");
		});

		// TESTING REPORT FOR AN INTERFACE.
		/*Future<InterfaceReport> fut1 = library.getInterfaceReport("src/main/java/pcd/ass02_parte2/Model/ProjectAnalyzer.java");
		fut1.onComplete(e -> {
			System.out.println("\n------------------------ Start Analyze Interface ------------------------");
			System.out.println("Interface name: " + e.result().getInterfaceName());
			System.out.println("Interface path: " + e.result().getInterfacePath());
			System.out.println("\nInformation about methods:\n");
			e.result().getMethodsInfo().forEach(n -> {
				System.out.println("\t- Methods name: " + n.getName() + "()");
				System.out.println("\t- Return type: " + n.getType());
				System.out.println("\t- Parameters: " + n.getParameters()+"\n");
			});
			System.out.println("------------------------ End Analyze Interface ------------------------\n");
		});*/

		// TESTING REPORT FOR A PACKAGE.
		/*Future<PackageReport> fut2 = library.getPackageReport("src/main/java/pcd/ass02_parte2");
		fut2.onComplete(e -> {
			System.out.println("\n------------------------ Start Analyze Package ------------------------");
			System.out.println("Package name: " + e.result().getPackageName());
			System.out.println("Classes in this package:");
			e.result().getClassReports().forEach(n -> System.out.println("\t- " + n.getClassName()));
			System.out.println("Interfaces in this package:");
			e.result().getInterfaceReports().forEach(n -> System.out.println("\t- " + n.getInterfaceName()));
			System.out.println("Packages in this package:");
			e.result().getPackages().forEach(n -> System.out.println("\t- " + n));
			System.out.println("\n------------------------ End Analyze Package ------------------------\n");
		});*/

		// TESTING REPORT FOR A PROJECT.
		/*Future<ProjectReport> fut3 = library.getProjectReport("src/main/java/pcd/ass02_parte2");
		fut3.onComplete(e -> {
			System.out.println("\n------------------------ Start Analyze Project ------------------------");
			System.out.println("Project name: " + e.result().getProjectName());
			System.out.println("Main class: " + e.result().getMainClass() + "\n");
			System.out.println("Information about this project:");
			e.result().getPackageReports().forEach(n -> {
				System.out.println("Package name: " + n.getPackageName());
				System.out.println("Classes: ");
				n.getClassReports().forEach(k -> System.out.println("\t- " + k.getClassName()));
				System.out.println("Interfaces: ");
				n.getInterfaceReports().forEach(k -> System.out.println("\t- " + k.getInterfaceName()));
				System.out.println("Packages: ");
				n.getPackages().forEach(k -> System.out.println("\t- " + k));
				System.out.println();
			} );
			System.out.println("------------------------ End Analyze Project ------------------------\n");
		});*/

		// TESTING EVENT BUS OF VERTX.
		//library.analyzeProject("src/main/java/pcd/ass02_parte2/Controller", vertx, "channel");

	}
}
