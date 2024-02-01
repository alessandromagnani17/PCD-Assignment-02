package pcd.ass02_parte3;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.vertx.core.Future;
import pcd.ass02_parte2.Model.Reports.ClassReport;
import pcd.ass02_parte2.Model.Reports.InterfaceReport;
import pcd.ass02_parte2.Model.Reports.PackageReport;
import pcd.ass02_parte2.Model.Reports.ProjectReport;
import pcd.ass02_parte3.Controller.ControllerView;
import pcd.ass02_parte3.Controller.Library;
import pcd.ass02_parte3.View.View;

public class Main {

    public static void main(String[] args) {

        // TEST FOR GUI (point B).
        /*View view = new View();
        ControllerView controller = new ControllerView(view);
        view.setController(controller);*/

        // TEST FOR POINT A.
        Library library = new Library();

        // TESTING REPORT FOR A CLASS.
        /*Flowable<ClassReport> obv = library.getClassReport("src/main/java/pcd/ass02_parte3/Controller/Library.java");
        obv.subscribe(res -> {
            System.out.println("\n------------------------ Start Analyze Class ------------------------");
            System.out.println("Class name: " + res.getClassName());
            System.out.println("Class path: " + res.getClassPath());
            System.out.println("Information about fields:\n");
            res.getFieldsInfo().forEach(n -> System.out.println("\t- " + n.getModifier()
                    + "" + n.getType() + " " + n.getName()));
            System.out.println("\nInformation about methods:\n");
            res.getMethodsInfo().forEach(n -> {
                System.out.println("\t- Methods name: " + n.getName() + "()");
                System.out.println("\t- Visibility: " + n.getVisibility());
                System.out.println("\t- Return type: " + n.getType());
                System.out.println("\t- Parameters: " + n.getParameters());
                System.out.println("\t- Start line: " + n.getSrcBeginLine());
                System.out.println("\t- End line: " + n.getEndBeginLine()+"\n");
            });
            System.out.println("------------------------ End Analyze Class ------------------------\n");
        });*/

        // TESTING REPORT FOR AN INTERFACE.
        /*Flowable<InterfaceReport> obv1 = library.getInterfaceReport("src/main/java/pcd/ass02_parte3/Model/ProjectAnalyzer.java");
        obv1.subscribe(res -> {
            System.out.println("\n------------------------ Start Analyze Interface ------------------------");
            System.out.println("Interface name: " + res.getInterfaceName());
            System.out.println("Interface path: " + res.getInterfacePath());
            System.out.println("\nInformation about methods:\n");
            res.getMethodsInfo().forEach(n -> {
                System.out.println("\t- Methods name: " + n.getName() + "()");
                System.out.println("\t- Return type: " + n.getType());
                System.out.println("\t- Parameters: " + n.getParameters()+"\n");
            });
            System.out.println("------------------------ End Analyze Interface ------------------------\n");
        });*/

        // TESTING REPORT FOR A PACKAGE.
        /*Flowable<PackageReport> obv2 = library.getPackageReport("src/main/java/pcd/ass02_parte3");
        obv2.subscribe(res -> {
            System.out.println("\n------------------------ Start Analyze Package ------------------------");
            System.out.println("Package name: " + res.getPackageName());
            System.out.println("Classes in this package:");
            res.getClassReports().forEach(n -> System.out.println("\t- " + n.getClassName()));
            System.out.println("Interfaces in this package:");
            res.getInterfaceReports().forEach(n -> System.out.println("\t- " + n.getInterfaceName()));
            System.out.println("Packages in this package:");
            res.getPackages().forEach(n -> System.out.println("\t- " + n));
            System.out.println("\n------------------------ End Analyze Package ------------------------\n");
        });*/

        // TESTING REPORT FOR A PROJECT.
        /*Flowable<ProjectReport> obv3 = library.getProjectReport("src/main/java/pcd/ass02_parte3");
        obv3.subscribe(res -> {
            System.out.println("\n------------------------ Start Analyze Project ------------------------");
            System.out.println("Project name: " + res.getProjectName());
            System.out.println("Main class: " + res.getMainClass() + "\n");
            System.out.println("Information about this project:");
            res.getPackageReports().forEach(n -> {
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

        // TESTING FOR RX.
        //library.analyzeProject("src/main/java/pcd/ass02_parte3");

    }
}
