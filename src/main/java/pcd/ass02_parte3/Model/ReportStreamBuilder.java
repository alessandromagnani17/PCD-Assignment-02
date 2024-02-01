package pcd.ass02_parte3.Model;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import pcd.ass02_parte2.Controller.Collectors.ClassCollector;
import pcd.ass02_parte2.Controller.Collectors.InterfaceCollector;
import pcd.ass02_parte2.Controller.Collectors.PackageCollector;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte2.Model.Utility.Wrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Represents the StreamBuilder that emits the String in the flow.
 *
 */
public class ReportStreamBuilder {

    private final Monitor stopFlag;
    private final File file;
    private final boolean bool;
    private final Wrapper wrapper = new Wrapper();

    /**
     * Creates a new ReportStreamBuilder for the GUI version.
     *
     * @param stopFlag The Monitor's variable.
     * @param file     The file to be analyzed.
     */
    public ReportStreamBuilder(Monitor stopFlag, File file) {
        this.stopFlag = stopFlag;
        this.file = file;
        this.bool = false;
    }

    /**
     * Creates a new ReportStreamBuilder for the console version of analyzeProject.
     *
     * @param stopFlag The Monitor's variable.
     * @param file     The file to be analyzed.
     * @param bool     Boolean that represents the console version.
     */
    public ReportStreamBuilder(Monitor stopFlag, File file, boolean bool) {
        this.stopFlag = stopFlag;
        this.file = file;
        this.bool = bool;
    }

    /**
     * Return an Observable created by using his factory.
     *
     * @return The Observable.
     */
    public Observable<String> createStringStream() {
        return Observable.create(emitter -> {
            visitProject(file, emitter);
            emitter.onComplete();
        });
    }

    /**
     * Visit the specified project and for every file calls the visitClassOrInterface() method,
     * for every directory:
     * 			- Creates a new PackageCollector;
     * 			- Sets the package name;
     * 			- Emits the package found in the channel.
     * 			- Calls this method recursively.
     *
     * @param file    The file to be analyzed.
     * @param emitter The emitter.
     */
    private void visitProject(File file, ObservableEmitter<String> emitter) {

        for (final File fileEntry : Objects.requireNonNull(file.listFiles())) {
            try{
                Thread.sleep(1000);
            } catch (Exception e) { e.printStackTrace(); }
            if (!fileEntry.isHidden() && checkBool()) {
                if (!fileEntry.isFile()) {
                    PackageCollector packageCollector = new PackageCollector();
                    packageCollector.getPackageReport().setPackageName(fileEntry.getName());

                    if(this.bool){
                        System.out.println("\n Found package --> " + fileEntry.getName() + "\n");
                    } else {
                        emitter.onNext("\n Found package --> " + fileEntry.getName() + "\n");
                    }

                    visitProject(fileEntry, emitter);
                } else if(fileEntry.getName().endsWith(".java")){
                    if(this.bool){
                        System.out.println(visitClassOrInterface(fileEntry.getPath()));
                    } else {
                        emitter.onNext(visitClassOrInterface(fileEntry.getPath()));
                    }
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
    private boolean checkBool() {
        if(this.bool) return true;
        return !this.stopFlag.isStopped();
    }

    /**
     * Visit the class/interface related to the specified path, create a new
     * ClassCollector/InterfaceCollector, wrap it and emit it in the flow.
     *
     * @param path The file path (class/interface).
     */
    private String visitClassOrInterface(String path) {

        CompilationUnit cu = null;

        try {
            cu = StaticJavaParser.parse(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (cu.getType(0).asClassOrInterfaceDeclaration().isInterface()) {
            InterfaceCollector interfaceCollector = new InterfaceCollector();
            interfaceCollector.getInterfaceReport().setInterfacePath(path);
            interfaceCollector.visit(cu, null);

            return wrapper.wrapInterfaceInformation(interfaceCollector);
        } else {
            ClassCollector classCollector = new ClassCollector();
            classCollector.getClassReport().setClassPath(path);
            classCollector.visit(cu, null);

            return wrapper.wrapClassInformation(classCollector);
        }
    }

}
