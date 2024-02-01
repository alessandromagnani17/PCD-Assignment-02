package pcd.ass02_parte2.Model.Utility;

import com.github.javaparser.ast.body.FieldDeclaration;

/**
 * Represents the information about a field.
 *
 */
public class FieldInfoImpl implements FieldInfo {

    private final FieldDeclaration fd;

    public FieldInfoImpl(FieldDeclaration fd) {
        this.fd = fd;
    }

    /**
     * Return the field name.
     *
     * @return The field name.
     */
    @Override
    public String getName() { return this.fd.getVariables().getFirst().get().getNameAsString(); }

    /**
     * Return the field modifier.
     *
     * @return The field modifier.
     */
    @Override
    public String getModifier() {
        if(this.fd.getModifiers().isEmpty()) return "";
        return this.fd.getModifiers().get(0).toString();
    }

    /**
     * Return the field type.
     *
     * @return The field type.
     */
    @Override
    public String getType() {
        return this.fd.getElementType().toString();
    }
}
