package pcd.ass02_parte1.Controller.Performance;

import pcd.ass02_parte1.Model.Body;
import pcd.ass02_parte1.Model.Boundary;
import pcd.ass02_parte1.Model.P2d;
import pcd.ass02_parte1.Model.V2d;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Represents the Thread Master of the simulation.
 *
 */
public class MasterPerformance extends Thread{

    private static final int NUM_CORE = 10;
    private final Boundary bounds = new Boundary(-4.0, -4.0, 4.0, 4.0);
    private final ArrayList<Body> bodies = new ArrayList<>();
    private final Random rand = new Random(System.currentTimeMillis());
    private final ExecutorService executorService;
    private final double dt;
    private final int nSteps;
    private final int nBalls;
    private double vt;

    /**
     * Creates a new MasterPerformance.
     *
     * @param nSteps   The number of iteration.
     * @param nBalls   The number of balls.
     */
    public MasterPerformance(int nSteps, int nBalls) {
        this.nSteps = nSteps;
        this.nBalls = nBalls;
        this.executorService = Executors.newFixedThreadPool(NUM_CORE);
        this.vt = 0;
        this.dt = 0.01;
    }

    /**
     * Body of the Thread Master.
     *
     */
    public void run() {
        //System.out.println("nBalls: "+ nBalls + " - nSteps: " + nSteps);

        long t0 = System.currentTimeMillis();
        int startIndex;
        int nBallsForTask = 50;

        for (int i = 0; i < this.nBalls; i++) {
            double x = bounds.getX0()*0.25 + rand.nextDouble() * (bounds.getX1() - bounds.getX0()) * 0.25;
            double y = bounds.getY0()*0.25 + rand.nextDouble() * (bounds.getY1() - bounds.getY0()) * 0.25;
            this.bodies.add(new Body(i, new P2d(x, y), new V2d(0, 0), 10));
        }

        for(int i = 0; i < this.nSteps; i++){

            for(startIndex = 0; startIndex < this.nBalls; startIndex = startIndex + nBallsForTask){
                Future<Void> fut = this.executorService.submit(new FirstOperationTask2(this.bodies, this.dt, startIndex, nBallsForTask));
                waitFinish(fut);
            }

            for(startIndex = 0; startIndex < this.nBalls; startIndex = startIndex + nBallsForTask){
                Future<Void> fut1 = this.executorService.submit(new SecondAndThirdOperationTask2(this.bodies, this.dt, this.bounds, startIndex, nBallsForTask));
                waitFinish(fut1);
            }

            vt = vt + dt;
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

