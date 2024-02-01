package pcd.ass02_parte2.Model.Utility;

import com.github.javaparser.ast.body.MethodDeclaration;
import pcd.ass02_parte2.Model.Reports.ClassReport;

/**
 * Represents the information about a method.
 *
 */
public class MethodInfoImpl implements MethodInfo {

    private final MethodDeclaration md;

    public MethodInfoImpl(MethodDeclaration md) {
        this.md = md;
    }

    /**
     * Return the method name.
     *
     * @return The method name.
     */
    @Override
    public String getName() {
        return this.md.getNameAsString();
    }

    /**
     * Return the method type.
     *
     * @return The method type.
     */
    @Override
    public String getType() {
        return this.md.getTypeAsString();
    }

    /**
     * Return the method begin line.
     *
     * @return The method begin line.
     */
    @Override
    public int getSrcBeginLine() {
        return this.md.getBegin().get().line;
    }

    /**
     * Return the method end line.
     *
     * @return The method end line.
     */
    @Override
    public int getEndBeginLine() {
        return this.md.getEnd().get().line;
    }

    /**
     * Return the method parameters.
     *
     * @return The method parameters.
     */
    @Override
    public String getParameters() {
        return this.md.getParameters().toString();
    }

    /**
     * Return the method visibility.
     *
     * @return The method visibility.
     */
    @Override
    public String getVisibility(){ return this.md.getModifiers().get(0).toString(); }
}
