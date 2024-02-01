package pcd.ass02_parte2.Model.Reports;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information about a package.
 *
 */
public class PackageReportImpl implements PackageReport {
    private String packageName;
    private final List<InterfaceReport> interfaceReports = new ArrayList<>();
    private final List<ClassReport> classReports = new ArrayList<>();
    private final List<String> packages = new ArrayList<>();

    /**
     * Set the package name.
     *
     * @param packageName The package name.
     */
    @Override
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Add an InterfaceReport.
     *
     * @param interfaceReport The InterfaceReport.
     */
    @Override
    public void addInterfaceReport(InterfaceReport interfaceReport) {
        this.interfaceReports.add(interfaceReport);
    }

    /**
     * Add a ClassReport.
     *
     * @param classReport The ClassReport.
     */
    @Override
    public void addClassReport(ClassReport classReport) {
        this.classReports.add(classReport);
    }

    /**
     * Add a String that represents the package name.
     *
     * @param packageName The package name.
     */
    @Override
    public void addPackage(String packageName){ this.packages.add(packageName); }

    /**
     * Return the package name.
     *
     * @return The package name.
     */
    @Override
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * Return the information about interfaces.
     *
     * @return The list of InterfaceReport.
     */
    @Override
    public List<InterfaceReport> getInterfaceReports(){
        return this.interfaceReports;
    }

    /**
     * Return the information about classes.
     *
     * @return The list of ClassReport.
     */
    @Override
    public List<ClassReport> getClassReports(){
        return this.classReports;
    }

    /**
     * Return the information about packages.
     *
     * @return The list of package's name.
     */
    @Override
    public List<String> getPackages() { return this.packages; }
}
