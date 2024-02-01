package pcd.ass02_parte2.Controller.Collectors;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd.ass02_parte2.Model.Reports.*;
import java.util.List;

/**
 * Represents the collector for the package' information.
 *
 */
public class PackageCollector extends VoidVisitorAdapter<List<String>> {

    private final PackageReport packageReport = new PackageReportImpl();

    /**
     * Visit the declaration of the package and set the name.
     *
     */
    public void visit(PackageDeclaration pd, List<String> collector) {
        super.visit(pd, collector);
        this.packageReport.setPackageName(pd.getNameAsString());
    }

    /**
     * Return the instance of PackageReport.
     *
     * @return The PackageReport.
     */
    public PackageReport getPackageReport() {
        return this.packageReport;
    }
}
