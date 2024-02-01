package pcd.ass02_parte2.Controller;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import pcd.ass02_parte2.Controller.Collectors.ClassCollector;
import pcd.ass02_parte2.Controller.Collectors.InterfaceCollector;
import pcd.ass02_parte2.Controller.Collectors.PackageCollector;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte2.Model.Utility.MethodInfo;
import pcd.ass02_parte2.Model.Utility.Wrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Represents the Agent that sends the message on the topic.
 *
 */
public class AgentSender extends AbstractVerticle {

    private final String path;
    private final String topic;
    private final Monitor stopFlag;
    private final boolean flag;
    private final Wrapper wrapper = new Wrapper();
    private CompilationUnit cu;
    private EventBus eb;

    /**
     * Creates a new AgentSender for the GUI version (point B).
     *
     * @param path     The file path.
     * @param stopFlag The Monitor's variable.
     * @param topic    The name of the channel.
     */
    public AgentSender(String path, Monitor stopFlag, String topic) {
        this.path = path;
        this.stopFlag = stopFlag;
        this.flag = false;
        this.topic = topic;
    }

    /**
     * Creates a new AgentSender for the last method of point A.
     *
     * @param path  The file path.
     * @param topic The name of the channel.
     */
    public AgentSender(String path, String topic) {
        this.path = path;
        this.topic = topic;
        this.stopFlag = null;
        this.flag = true;
    }

    /**
     * Body of the Verticle.
     *
     */
    public void start() {
        log("started to analyze " + this.path);
        this.eb = this.getVertx().eventBus();

        File file = new File(this.path);

        visitProject(file);
    }

    /**
     * Visit the specified project and for every file calls the visitClassOrInterface() method,
     * for every directory:
     * 			- Creates a new PackageCollector;
     * 			- Sets the package name;
     * 			- Publishs the package found in the channel.
     * 			- Calls this method recursively.
     *
     * @param file The file to be analyzed.
     */
    private void visitProject(File file) {
        for (final File fileEntry : Objects.requireNonNull(file.listFiles())) {
            try{
                Thread.sleep(200);
            } catch (Exception e) { e.printStackTrace(); }
            if (!fileEntry.isHidden() && checkMonitor()) {
                if (!fileEntry.isFile()) {
                    PackageCollector packageCollector = new PackageCollector();
                    packageCollector.getPackageReport().setPackageName(fileEntry.getName());
                    this.eb.publish(this.topic, "\n Found package --> " + fileEntry.getName() + "\n");
                    visitProject(fileEntry);
                } else if(fileEntry.getName().endsWith(".java")){
                    visitClassOrInterface(fileEntry.getPath());
                }
            }
        }
    }

    /**
     * Check if the flag is setted (the flag represents the console version or the GUI version)
     * and if not return the value of the Monitor's variable.
     *
     * @return value of @flag if it's true, otherwise the Monitor's variable.
     */
    private boolean checkMonitor() {
        if (this.flag) return true;
        return !this.stopFlag.isStopped();
    }

    /**
     * Visit the class/interface related to the specified path, create a new
     * ClassCollector/InterfaceCollector, wrap it and publish in the channel.
     *
     * @param path The file path (class/interface).
     */
    private void visitClassOrInterface(String path) {
        try {
            cu = StaticJavaParser.parse(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface()) {
            InterfaceCollector interfaceCollector = new InterfaceCollector();
            interfaceCollector.getInterfaceReport().setInterfacePath(path);
            interfaceCollector.visit(cu, null);

            this.eb.publish(this.topic, this.wrapper.wrapInterfaceInformation(interfaceCollector));
        } else {
            ClassCollector classCollector = new ClassCollector();
            classCollector.getClassReport().setClassPath(path);
            classCollector.visit(cu, null);

            this.eb.publish(this.topic, this.wrapper.wrapClassInformation(classCollector));
        }
    }

    private void log(String msg) {
        System.out.println("[REACTIVE AGENT #2] " + msg);
    }
}
