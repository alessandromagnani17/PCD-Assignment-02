package pcd.ass02_parte1;

import pcd.ass02_parte1.Controller.Performance.MasterPerformance;
import pcd.ass02_parte1.View.SimulationView;

public class Main {
    public static void main(String[] args) {

        // TEST FOR PERFORMANCE
        MasterPerformance masterPerformance = new MasterPerformance(1000, 1000);
        masterPerformance.start();

        // TEST FOR GUI
        /*SimulationView viewer = new SimulationView(620,620);
        Controller controller = new Controller(viewer);
        viewer.setController(controller);*/
    }
}
