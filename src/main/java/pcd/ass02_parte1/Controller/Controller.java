package pcd.ass02_parte1.Controller;

import pcd.ass02_parte1.Controller.GUI.MasterGUI;
import pcd.ass02_parte1.View.SimulationView;
import pcd.ass02_parte2.Model.Monitor;

/**
 * Represents the Controller for the GUI.
 *
 */
public class Controller {

	private final Monitor stopFlag;
	private final SimulationView viewer;
	private static final int nBalls = 1000;
	private static final int nSteps = 5000;

	/**
	 * Creates a new Controller specifying the SimulationView.
	 *
	 * @param viewer The SimulationView.
	 */
	public Controller(SimulationView viewer) {
		this.stopFlag = new Monitor();
		this.viewer = viewer;
	}

	/**
	 * Start the simulation creating balls and starting the Master Agent.
	 *
	 */
	public synchronized void notifyStarted() {
		stopFlag.resetStop();
		MasterGUI masterTask = new MasterGUI(this.viewer, nSteps, nBalls, stopFlag);
		masterTask.start();
	}

	/**
	 * Stop the simulation.
	 *
	 */
	public synchronized void notifyStopped() {
		stopFlag.stop();
	}
}
