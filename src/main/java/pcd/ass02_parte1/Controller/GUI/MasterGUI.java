package pcd.ass02_parte1.Controller.GUI;

import pcd.ass02_parte1.Model.Body;
import pcd.ass02_parte1.Model.Boundary;
import pcd.ass02_parte1.Model.P2d;
import pcd.ass02_parte1.Model.V2d;
import pcd.ass02_parte1.View.SimulationView;
import pcd.ass02_parte2.Model.Monitor;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Represents the Thread Master of the simulation.
 *
 */
public class MasterGUI extends Thread {

    private static final int NUM_CORE = Runtime.getRuntime().availableProcessors();
    private final SimulationView simulationView;
    private final Boundary bounds = new Boundary(-4.0, -4.0, 4.0, 4.0);
    private final ArrayList<Body> bodies = new ArrayList<>();
    private final Random rand = new Random(System.currentTimeMillis());
    private final ExecutorService executorService1;
    private final ExecutorService executorService2;
    private Monitor stopFlag;
    private final double dt;
    private final int nSteps;
    private final int nBalls;
    private double vt;

    /**
     * Creates a new MasterGUI.
     *
     * @param viewer   The SimulationView.
     * @param nSteps   The number of iteration.
     * @param nBalls   The number of balls.
     * @param stopFlag The Monitor's variable.
     */
    public MasterGUI(SimulationView viewer, int nSteps, int nBalls, Monitor stopFlag) {
        this.simulationView = viewer;
        this.nSteps = nSteps;
        this.nBalls = nBalls;
        this.stopFlag = stopFlag;
        this.executorService1 = Executors.newFixedThreadPool(NUM_CORE);
        this.executorService2 = Executors.newFixedThreadPool(NUM_CORE);
        this.vt = 0;
        this.dt = 0.01;
    }

    /**
     * Body of the Thread Master.
     *
     */
    public void run() {
        long t0 = System.currentTimeMillis();
        int startIndex;
        int nBallsForTask = 50;

        for (int i = 0; i < this.nBalls; i++) {
            double x = bounds.getX0()*0.25 + rand.nextDouble() * (bounds.getX1() - bounds.getX0()) * 0.25;
            double y = bounds.getY0()*0.25 + rand.nextDouble() * (bounds.getY1() - bounds.getY0()) * 0.25;
            this.bodies.add(new Body(i, new P2d(x, y), new V2d(0, 0), 10));
        }

        for(int i = 0; i < this.nSteps; i++){

            if(!stopFlag.isStopped()){
                for(startIndex = 0; startIndex < this.nBalls; startIndex = startIndex + nBallsForTask){
                    Future<Void> fut = this.executorService1.submit(new FirstOperationTask(this.bodies, this.dt, startIndex, nBallsForTask, stopFlag));
                    waitFinish(fut);
                }

                for(startIndex = 0; startIndex < this.nBalls; startIndex = startIndex + nBallsForTask){
                    Future<Void> fut1 = this.executorService2.submit(new SecondAndThirdOperationTask(this.bodies, this.dt, this.bounds, startIndex, nBallsForTask, stopFlag));
                    waitFinish(fut1);
                }

                this.simulationView.display(bodies, vt, i, bounds);
                vt = vt + dt;
            }
        }

        long t1 = System.currentTimeMillis() - t0;
        System.out.println("Execution ended | Time elapsed -> " + t1);
    }

    /**
     * Wait for the result of the Task operations.
     *
     * @param fut The future related to the task.
     */
    private void waitFinish(Future<Void> fut) {
        try {
            fut.get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
