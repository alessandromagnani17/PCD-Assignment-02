package pcd.ass02_parte2.Model.Reports;

import pcd.ass02_parte2.Model.Utility.MethodInfo;
import java.util.List;

public interface InterfaceReport {

    /**
     * Return the interface name.
     *
     * @return The interface name.
     */
    String getInterfaceName();

    /**
     * Return the interface path.
     *
     * @return The interface path.
     */
    String getInterfacePath();

    /**
     * Return the information about methods.
     *
     * @return The list of MethodInfo.
     */
    List<MethodInfo> getMethodsInfo();

    /**
     * Set the interface name.
     *
     * @param interfaceName The interface name.
     */
    void setInterfaceName(String interfaceName);

    /**
     * Set the interface path.
     *
     * @param interfacePath The interface path.
     */
    void setInterfacePath(String interfacePath);

    /**
     * Add a MethodInfo.
     *
     * @param methodInfo The MethodInfo.
     */
    void addMethodsInfo(MethodInfo methodInfo);
}
