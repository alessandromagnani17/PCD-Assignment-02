package pcd.ass02_parte2.Model.Reports;

import java.util.List;

public interface ProjectReport {

	/**
	 * Return the project name.
	 *
	 * @return The project name.
	 */
	String getProjectName();

	/**
	 * Return the main class of the project.
	 *
	 * @return The main class.
	 */
	String getMainClass();

	/**
	 * Return the information about packages.
	 *
	 * @return The list of PackageReport.
	 */
	List<PackageReport> getPackageReports();

	/**
	 * Set the project name.
	 *
	 * @param projectName The project name.
	 */
	void setProjectName(String projectName);

	/**
	 * Set the main class of the project.
	 *
	 * @param mainClass The main class.
	 */
	void setMainClass(String mainClass);

	/**
	 * Add a PackageReport.
	 *
	 * @param packageReport The PackageReport.
	 */
	void addPackageReport(PackageReport packageReport);
}
