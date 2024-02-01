package pcd.ass02_parte3.Model;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import pcd.ass02_parte2.Model.Reports.ClassReport;
import pcd.ass02_parte2.Model.Reports.InterfaceReport;
import pcd.ass02_parte2.Model.Reports.PackageReport;
import pcd.ass02_parte2.Model.Reports.ProjectReport;

import java.io.FileNotFoundException;

public interface ProjectAnalyzer {

	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file
	 *
	 * @param srcInterfacePath The path of the interface to be analyzed.
	 * @return The Flowable related to the InterfaceReport.
	 */
	Flowable<InterfaceReport> getInterfaceReport(String srcInterfacePath) throws FileNotFoundException;



	/**
	 * Async method to retrieve the report about a specific class,
	 * given the full path of the class source file
	 *
	 * @param srcClassPath The path of the class to be analyzed.
	 * @return The Flowable related to the ClassReport.
	 */
	Flowable<ClassReport> getClassReport(String srcClassPath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a package,
	 * given the full path of the package folder
	 *
	 * @param srcPackagePath The path of the package to be analyzed.
	 * @return The Flowable related to the PackageReport.
	 */
	Flowable<PackageReport> getPackageReport(String srcPackagePath) throws FileNotFoundException;

	/**
	 * Async method to retrieve the report about a project
	 * given the full path of the project folder 
	 *
	 * @param srcProjectFolderPath The path of the project to be analyzed.
	 * @return The Flowable related to the ProjectReport.
	 */
	Flowable<ProjectReport> getProjectReport(String srcProjectFolderPath);

	/**
	 * Async function that analyze a project given the full path of the project folder,
	 * emitting in the Stream each time a project element is found.
	 *
	 * @param srcProjectFolderName The path of the project to be analyzed.
	 */
	void analyzeProject(String srcProjectFolderName);
}
