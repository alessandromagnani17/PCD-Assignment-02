package pcd.ass02_parte2.Controller.Collectors;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd.ass02_parte2.Model.Reports.ProjectReport;
import pcd.ass02_parte2.Model.Reports.ProjectReportImpl;

import java.util.List;

/**
 * Represents the collector for the project' information.
 *
 */
public class ProjectCollector extends VoidVisitorAdapter<List<String>> {

    private final ProjectReport projectReport = new ProjectReportImpl();

    /**
     * Visit the declaration of the package and set the name.
     *
     */
    public void visit(PackageDeclaration pd, List<String> collector){
        super.visit(pd, collector);
    }

    /**
     * Return the instance of ProjectReport.
     *
     * @return The ProjectReport.
     */
    public ProjectReport getProjectReport() {
        return this.projectReport;
    }
}
