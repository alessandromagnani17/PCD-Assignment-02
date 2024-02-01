package pcd.ass02_parte2.Model.Reports;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information about a project.
 *
 */
public class ProjectReportImpl implements ProjectReport{

    private List<PackageReport> packageReports = new ArrayList<>();
    private String mainClass;
    private String projectName;

    /**
     * Set the main class of the project.
     *
     * @param mainClass The main class.
     */
    @Override
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    /**
     * Set the project name.
     *
     * @param projectName The project name.
     */
    @Override
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Add a PackageReport.
     *
     * @param packageReport The PackageReport.
     */
    @Override
    public void addPackageReport(PackageReport packageReport) {
        this.packageReports.add(packageReport);
    }

    /**
     * Return the main class of the project.
     *
     * @return The main class.
     */
    @Override
    public String getMainClass() {
        return this.mainClass;
    }

    /**
     * Return the information about packages.
     *
     * @return The list of PackageReport.
     */
    @Override
    public List<PackageReport> getPackageReports() {
        return this.packageReports;
    }

    /**
     * Return the project name.
     *
     * @return The project name.
     */
    @Override
    public String getProjectName() {
        return this.projectName;
    }

}
