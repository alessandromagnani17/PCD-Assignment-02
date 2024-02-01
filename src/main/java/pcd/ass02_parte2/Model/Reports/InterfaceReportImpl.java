package pcd.ass02_parte2.Model.Reports;

import pcd.ass02_parte2.Model.Utility.MethodInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information about an interface.
 *
 */
public class InterfaceReportImpl implements InterfaceReport{

    private String interfaceName;
    private String interfacePath;
    private final List<MethodInfo> methodsInfo = new ArrayList<>();

    /**
     * Return the interface name.
     *
     * @return The interface name.
     */
    @Override
    public String getInterfaceName() {
        return this.interfaceName;
    }

    /**
     * Return the interface path.
     *
     * @return The interface path.
     */
    @Override
    public String getInterfacePath() {
        return this.interfacePath;
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
     * Set the interface name.
     *
     * @param interfaceName The interface name.
     */
    @Override
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    /**
     * Set the interface path.
     *
     * @param interfacePath The interface path.
     */
    @Override
    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
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

}
