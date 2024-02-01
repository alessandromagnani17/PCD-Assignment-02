package pcd.ass02_parte2.Controller.Collectors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import pcd.ass02_parte2.Model.Reports.ClassReport;
import pcd.ass02_parte2.Model.Reports.ClassReportImpl;
import pcd.ass02_parte2.Model.Utility.FieldInfo;
import pcd.ass02_parte2.Model.Utility.FieldInfoImpl;
import pcd.ass02_parte2.Model.Utility.MethodInfo;
import pcd.ass02_parte2.Model.Utility.MethodInfoImpl;

import java.util.List;

/**
 * Represents the collector for the class' information.
 *
 */
public class ClassCollector extends VoidVisitorAdapter<List<String>> {

    private final ClassReport classReport = new ClassReportImpl();

    /**
     * Visit the declaration of the class and set the name.
     *
     */
    public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {
        super.visit(md, collector);
        this.classReport.setClassName(md.getNameAsString());
    }

    /**
     * Visit the methods' declaration of the class and add the methods' info to the class report.
     *
     */
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        MethodInfo methodInfo = new MethodInfoImpl(md);
        this.classReport.addMethodsInfo(methodInfo);
    }

    /**
     * Visit the field' declaration of the class and add the field' info to the class report.
     *
     */
    public void visit(FieldDeclaration fd, List<String> collector) {
        super.visit(fd, collector);
        FieldInfo fieldInfo = new FieldInfoImpl(fd);
        this.classReport.addFieldsInfo(fieldInfo);
    }

    /**
     * Return the instance of ClassReport.
     *
     * @return The ClassReport.
     */
    public ClassReport getClassReport() {
        return this.classReport;
    }
}
