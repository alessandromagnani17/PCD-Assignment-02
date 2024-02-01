package pcd.ass02_parte2.Model.Utility;

import pcd.ass02_parte2.Controller.Collectors.ClassCollector;
import pcd.ass02_parte2.Controller.Collectors.InterfaceCollector;

/**
 * Represents a wrapper for the class and interface information.
 *
 */
public class Wrapper {

    public Wrapper(){}

    /**
     * Wrap the information in the specified ClassCollector.
     *
     * @param classCollector The ClassCollector.
     * @return A string with the class information.
     */
    public String wrapClassInformation(ClassCollector classCollector) {

        boolean flag = false;
        for(MethodInfo elem: classCollector.getClassReport().getMethodsInfo()){
            if (elem.getName().equals("main")) flag = true;
        }

        String classInformation = "\n------------------------ Class Analyzed ------------------------ \n";
        if (flag){
            classInformation = classInformation.concat("[MAIN CLASS] ");
        }
        classInformation = classInformation.concat("Class name: " + classCollector.getClassReport().getClassName() + "\n" +
                "Class path: " + classCollector.getClassReport().getClassPath() + "\n" +
                "Information about fields: " + "\n\n");
        for(int i = 0; i < classCollector.getClassReport().getFieldsInfo().size(); i++){
            String str = "\t- " + classCollector.getClassReport().getFieldsInfo().get(i).getModifier()
                    + classCollector.getClassReport().getFieldsInfo().get(i).getType() + " "
                    + classCollector.getClassReport().getFieldsInfo().get(i).getName() + "\n";
            classInformation = classInformation.concat(str);
        }
        classInformation = classInformation.concat("\nInformation about methods: " + "\n\n");
        for(int i = 0; i < classCollector.getClassReport().getMethodsInfo().size(); i++){
            String str = "\t- Method name: " + classCollector.getClassReport().getMethodsInfo().get(i).getName() + "\n"
                    + "\t- Visibility: " + classCollector.getClassReport().getMethodsInfo().get(i).getVisibility() + "\n"
                    + "\t- Return type: " + classCollector.getClassReport().getMethodsInfo().get(i).getType() + "\n"
                    + "\t- Parameters: " + classCollector.getClassReport().getMethodsInfo().get(i).getParameters() + "\n"
                    + "\t- Start line: " + classCollector.getClassReport().getMethodsInfo().get(i).getSrcBeginLine() + "\n"
                    + "\t- End line: " + classCollector.getClassReport().getMethodsInfo().get(i).getEndBeginLine() + "\n\n";
            classInformation = classInformation.concat(str);
        }
        classInformation = classInformation.concat("------------------------------------------------------------------");

        return classInformation;
    }

    /**
     * Wrap the information in the specified InterfaceCollector.
     *
     * @param interfaceCollector The InterfaceCollector.
     * @return A string with the interface information.
     */
    public String wrapInterfaceInformation(InterfaceCollector interfaceCollector) {
        String interfaceInformation = "\n------------------------ Interface Analyzed ------------------------ \n" +
                "Interface name: " + interfaceCollector.getInterfaceReport().getInterfaceName() + "\n" +
                "Interface path: " + interfaceCollector.getInterfaceReport().getInterfacePath() + "\n" +
                "Information about methods: " + "\n\n";
        for(int i = 0; i < interfaceCollector.getInterfaceReport().getMethodsInfo().size(); i++){
            String str;
            str = "\t- Methods name: " + interfaceCollector.getInterfaceReport().getMethodsInfo().get(i).getName() + "\n"
                    + "\t- Return type: " + interfaceCollector.getInterfaceReport().getMethodsInfo().get(i).getType() + "\n"
                    + "\t- Parameters: " + interfaceCollector.getInterfaceReport().getMethodsInfo().get(i).getParameters() + "\n\n";
            interfaceInformation = interfaceInformation.concat(str);
        }
        interfaceInformation = interfaceInformation.concat("------------------------------------------------------------------");

        return interfaceInformation;
    }

}
