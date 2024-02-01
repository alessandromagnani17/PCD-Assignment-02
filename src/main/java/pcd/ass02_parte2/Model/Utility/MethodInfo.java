package pcd.ass02_parte2.Model.Utility;

import pcd.ass02_parte2.Model.Reports.ClassReport;

public interface MethodInfo {

	/**
	 * Return the method name.
	 *
	 * @return The method name.
	 */
	String getName();

	/**
	 * Return the method type.
	 *
	 * @return The method type.
	 */
	String getType();

	/**
	 * Return the method begin line.
	 *
	 * @return The method begin line.
	 */
	int getSrcBeginLine();

	/**
	 * Return the method end line.
	 *
	 * @return The method end line.
	 */
	int getEndBeginLine();

	/**
	 * Return the method parameters.
	 *
	 * @return The method parameters.
	 */
	String getParameters();

	/**
	 * Return the method visibility.
	 *
	 * @return The method visibility.
	 */
	String getVisibility();
		
}
