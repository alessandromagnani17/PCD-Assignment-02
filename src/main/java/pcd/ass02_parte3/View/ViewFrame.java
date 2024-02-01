package pcd.ass02_parte3.View;

import pcd.ass02_parte3.Controller.ControllerView;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Represents the frame of the solution.
 *
 */
public class ViewFrame implements ActionListener {

    private ControllerView controller;
    private JPanel panelView;
    private JButton visitButton;
    private JButton selectFileButton;
    private JTextArea textArea;
    private JLabel label;
    private JButton stopButton;
    private JScrollPane scrollPane;
    private JFrame frame;
    private File file;

    /**
     * Creates a new ViewFrame.
     *
     */
    public ViewFrame () {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(this.panelView);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        this.selectFileButton.addActionListener(this);
        this.visitButton.addActionListener(e -> controller.notifyStarted(file));
        this.stopButton.addActionListener(e -> controller.notifyStopped());

    }

    /**
     * Set the controller.
     *
     * @param controller The ControllerView.
     */
    public void setController(ControllerView controller) {
        this.controller = controller;
    }

    /**
     * Append the specified text to the TextArea.
     *
     * @param msg The text to be appended.
     */
    public void appendText(String msg) {
        this.textArea.append(msg);
    }

    /**
     * ActionListener for the selectFile button.
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            label.setText("Folder Selected: " + file.getPath());
        } else {
            label.setText("Open command canceled");
        }

        label.setVisible(true);
        stopButton.setEnabled(true);
        visitButton.setEnabled(true);
    }

    /**
     * Clean the TextArea.
     *
     */
    public void cleanTextArea() {
        this.textArea.setText("");
    }
}
