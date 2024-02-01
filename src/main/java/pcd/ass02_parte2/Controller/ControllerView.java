package pcd.ass02_parte2.Controller;

import io.vertx.core.Vertx;
import pcd.ass02_parte2.Model.Monitor;
import pcd.ass02_parte2.View.View;

import java.io.File;

/**
 * Represents the Controller for the GUI.
 *
 */
public class ControllerView {

    private final View view;
    private final Monitor stopFlag;

    /**
     * Creates a new ControllerView specifying the View.
     *
     * @param view The View.
     */
    public ControllerView(View view) {
        this.view = view;
        this.stopFlag = new Monitor();
    }

    /**
     * Start the simulation (resets the Monitor and cleans the TextArea) calling
     * the async method analyzeProjectWithGUI().
     *
     * @param file The file to be analyzed.
     */
    public synchronized void notifyStarted(File file) {
        stopFlag.resetStop();
        view.cleanTextArea();

        Vertx vertx = Vertx.vertx();
        Library library = new Library(vertx);

        library.analyzeProjectWithGUI(file.getPath(), vertx, this.stopFlag, this, "channel");
    }

    /**
     * Stop the simulation.
     *
     */
    public synchronized void notifyStopped() {
        stopFlag.stop();

        view.appendText("\nAnalysis has been interrupted ... press button 'visit' to restart");
    }

    /**
     * Append an element to the TextArea.
     *
     * @param element The String to be appended.
     */
    public void addElementInformation(String element) {
        this.view.appendText(element);
    }
}
