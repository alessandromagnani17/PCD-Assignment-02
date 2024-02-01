package pcd.ass02_parte3.Controller;

import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte3.View.View;

import java.io.File;

/**
 * Represents the controller of the simulation.
 *
 */
public class ControllerView {

    private final Monitor stopFlag;
    private final View view;

    /**
     * Creates a new ControllerView passing the View.
     *
     * @param view The View.
     */
    public ControllerView(View view) {
        this.stopFlag = new Monitor();
        this.view = view;
    }

    /**
     * Start the simulation (resets the Monitor and cleans the TextArea) creating
     * the Thread Master.
     *
     * @param file The file to be analyzed.
     */
    public synchronized void notifyStarted (File file) {
        stopFlag.resetStop();
        view.cleanTextArea();

        Master master = new Master(file, view, stopFlag);
        master.start();
    }

    /**
     * Stop the simulation.
     *
     */
    public synchronized void notifyStopped () {
        stopFlag.stop();

        view.appendText("Analysis has been interrupted ... press button 'visit' to restart");
    }


}
