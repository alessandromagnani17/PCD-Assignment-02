package pcd.ass02_parte3.Controller;

import io.reactivex.rxjava3.core.Observable;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte3.Model.ReportStreamBuilder;
import pcd.ass02_parte3.View.View;

import java.io.File;

/**
 * Represents the Thread Master of the solution.
 *
 */
public class Master extends Thread{

    private final Monitor stopFlag;
    private final File file;
    private final View view;

    /**
     * Creates a new Thread Master passing the file, the view and the Monitor's variable.
     *
     * @param file     The file to be analyzed.
     * @param view     The View.
     * @param stopFlag The Monitor's variable.
     */
    public Master(File file, View view, Monitor stopFlag) {
        this.stopFlag = stopFlag;
        this.file = file;
        this.view = view;
    }

    /**
     * Body of the Thread Master.
     *
     */
    public void run(){
        Observable<String> observable = new ReportStreamBuilder(stopFlag, file).createStringStream();

        observable.subscribe(elem -> this.view.appendText(elem+"\n"));
    }
}
