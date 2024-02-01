package pcd.ass02_parte2.Model.Reports;

import pcd.ass02_parte2.Model.Utility.FieldInfo;
import pcd.ass02_parte2.Model.Utility.MethodInfo;

import java.util.List;

public interface ClassReport {

	/**
	 * Return the class name.
	 *
	 * @return The class name.
	 */
	String getClassName();

	/**
	 * Return the class path.
	 *
	 * @return The class path.
	 */
	String getClassPath();

	/**
	 * Return the information about methods.
	 *
	 * @return The list of MethodInfo.
	 */
	List<MethodInfo> getMethodsInfo();

	/**
	 * Return the information about fields.
	 *
	 * @return The list of FieldInfo.
	 */
	List<FieldInfo> getFieldsInfo();

	/**
	 * Set the class name.
	 *
	 * @param className The class name.
	 */
    void setClassName(String className);

	/**
	 * Set the class path.
	 *
	 * @param classPath The class path.
	 */
	void setClassPath(String classPath);

	/**
	 * Add a MethodInfo.
	 *
	 * @param methodInfo The MethodInfo.
	 */
	void addMethodsInfo(MethodInfo methodInfo);

	/**
	 * Add a FieldInfo.
	 *
	 * @param fieldInfo The FieldInfo.
	 */
	void addFieldsInfo(FieldInfo fieldInfo);
}
