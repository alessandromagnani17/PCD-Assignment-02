package pcd.ass02_parte3.View;

import pcd.ass02_parte3.Controller.ControllerView;

/**
 * Represents the View of the solution.
 *
 */
public class View {

    private ViewFrame frame;

    /**
     * Creates a View that creates the frame (ViewFrame).
     *
     */
    public View() {
        frame = new ViewFrame();
    }

    /**
     * Set the controller of the simulation.
     *
     * @param controller The ControllerView.
     */
    public void setController(ControllerView controller) {
        frame.setController(controller);
    }

    /**
     * Append the specified text in the TextArea calling the frame.
     *
     * @param msg The text to be appended.
     */
    public void appendText(String msg) {
        frame.appendText(msg);
    }

    /**
     * Clean the text area.
     *
     */
    public void cleanTextArea() {
        frame.cleanTextArea();
    }
}
