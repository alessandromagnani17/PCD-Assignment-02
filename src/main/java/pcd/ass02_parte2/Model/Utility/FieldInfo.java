package pcd.ass02_parte2.Model.Utility;

import pcd.ass02_parte2.Model.Reports.ClassReport;

public interface FieldInfo {

	/**
	 * Return the field name.
	 *
	 * @return The field name.
	 */
	String getName();

	/**
	 * Return the field modifier.
	 *
	 * @return The field modifier.
	 */
	String getModifier();

	/**
	 * Return the field type.
	 *
	 * @return The field type.
	 */
	String getType();
}
