package pcd.ass02_parte2.Model.Reports;

import pcd.ass02_parte2.Model.Utility.FieldInfo;
import pcd.ass02_parte2.Model.Utility.MethodInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information about a class.
 *
 */
public class ClassReportImpl implements ClassReport {

    private String className;
    private String classPath;
    private final List<MethodInfo> methodsInfo = new ArrayList<>();
    private final List<FieldInfo> fieldsInfo = new ArrayList<>();

    /**
     * Return the class name.
     *
     * @return The class name.
     */
    @Override
    public String getClassName() {
        return this.className;
    }

    /**
     * Return the class path.
     *
     * @return The class path.
     */
    @Override
    public String getClassPath() {
        return this.classPath;
    }

    /**
     * Return the information about methods.
     *
     * @return The list of MethodInfo.
     */
    @Override
    public List<MethodInfo> getMethodsInfo() {
        return this.methodsInfo;
    }

    /**
     * Return the information about fields.
     *
     * @return The list of FieldInfo.
     */
    @Override
    public List<FieldInfo> getFieldsInfo() {
        return this.fieldsInfo;
    }

    /**
     * Set the class name.
     *
     * @param className The class name.
     */
    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Set the class path.
     *
     * @param classPath The class path.
     */
    @Override
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    /**
     * Add a MethodInfo.
     *
     * @param methodInfo The MethodInfo.
     */
    @Override
    public void addMethodsInfo(MethodInfo methodInfo) {
        this.methodsInfo.add(methodInfo);
    }

    /**
     * Add a FieldInfo.
     *
     * @param fieldInfo The FieldInfo.
     */
    @Override
    public void addFieldsInfo(FieldInfo fieldInfo) {
        this.fieldsInfo.add(fieldInfo);
    }
}
