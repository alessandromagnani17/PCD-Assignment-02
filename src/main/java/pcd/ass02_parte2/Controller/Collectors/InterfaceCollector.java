package pcd.ass02_parte2.Controller.Collectors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd.ass02_parte2.Model.Reports.InterfaceReport;
import pcd.ass02_parte2.Model.Reports.InterfaceReportImpl;
import pcd.ass02_parte2.Model.Utility.MethodInfo;
import pcd.ass02_parte2.Model.Utility.MethodInfoImpl;

import java.util.List;

/**
 * Represents the collector for the interface' information.
 *
 */
public class InterfaceCollector extends VoidVisitorAdapter<List<String>> {

    private final InterfaceReport interfaceReport = new InterfaceReportImpl();

    /**
     * Visit the declaration of the interface and set the name.
     *
     */
    public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {
        super.visit(md, collector);
        this.interfaceReport.setInterfaceName(md.getNameAsString());
    }

    /**
     * Visit the methods' declaration of the interface and add the methods' info to the interface report.
     *
     */
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        MethodInfo methodInfo = new MethodInfoImpl(md);
        this.interfaceReport.addMethodsInfo(methodInfo);
    }

    /**
     * Return the instance of InterfaceReport.
     *
     * @return The InterfaceReport.
     */
    public InterfaceReport getInterfaceReport() {
        return this.interfaceReport;
    }
}
