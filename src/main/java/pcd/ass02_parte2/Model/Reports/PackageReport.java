package pcd.ass02_parte2.Model.Reports;

import java.util.List;

public interface PackageReport {

    /**
     * Return the package name.
     *
     * @return The package name.
     */
    String getPackageName();

    /**
     * Return the information about interfaces.
     *
     * @return The list of InterfaceReport.
     */
    List<InterfaceReport> getInterfaceReports();

    /**
     * Return the information about classes.
     *
     * @return The list of ClassReport.
     */
    List<ClassReport> getClassReports();

    /**
     * Return the information about packages.
     *
     * @return The list of package's name.
     */
    List<String> getPackages();

    /**
     * Set the package name.
     *
     * @param packageName The package name.
     */
    void setPackageName(String packageName);

    /**
     * Add an InterfaceReport.
     *
     * @param interfaceReport The InterfaceReport.
     */
    void addInterfaceReport(InterfaceReport interfaceReport);

    /**
     * Add a ClassReport.
     *
     * @param classReport The ClassReport.
     */
    void addClassReport(ClassReport classReport);

    /**
     * Add a String that represents the package name.
     *
     * @param packageName The package name.
     */
    void addPackage(String packageName);

}
