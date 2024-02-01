package pcd.ass02_parte1.Controller.GUI;

import pcd.ass02_parte1.Model.Body;
import pcd.ass02_parte1.Model.V2d;
import pcd.ass02_parte2.Model.Monitor;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Represents the Task for the first operation.
 * The first operations consists in:
 *          - Compute total force of the bodies specified in the constructor's parameters.
 *          - Update velocity of the bodies specified in the constructor's parameters.
 *
 */
public class FirstOperationTask implements Callable<Void> {

    private final ArrayList<Body> bodies;
    private final double dt;
    private final int startIndex;
    private final int nBalls;
    private final Monitor stopFlag;

    /**
     * Creates a new FirstOperationTask.
     *
     * @param bodies     The list of bodies.
     * @param dt         The virtual time step.
     * @param startIndex The index of the first ball to manage.
     * @param nBalls     The number of balls to manage.
     * @param stopFlag   The Monitor's variable.
     */
    public FirstOperationTask(ArrayList<Body> bodies, double dt, int startIndex, int nBalls, Monitor stopFlag) {
        this.bodies = bodies;
        this.dt = dt;
        this.startIndex = startIndex;
        this.nBalls = nBalls;
        this.stopFlag = stopFlag;
    }

    /**
     * Body of the task.
     */
    @Override
    public Void call(){

        computeTotalForceAndUpdateVelocity();

        return null;
    }

    /**
     * Compute total force and update velocity for @nBalls balls starting from @startIndex (with checks for the Monitor's variable).
     */
    private void computeTotalForceAndUpdateVelocity(){
        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {
            if(!stopFlag.isStopped()){
                V2d totalForce = computeTotalForceOnBody(this.bodies.get(i));
                V2d acc = new V2d(totalForce).scalarMul(1.0 / this.bodies.get(i).getMass());
                this.bodies.get(i).updateVelocity(acc, this.dt);
            }
        }
    }

    /**
     * Compute total force of a body (with checks for the Monitor's variable).
     */
    private V2d computeTotalForceOnBody(Body b) {
        V2d totalForce = new V2d(0, 0);

        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {
            if (!b.equals(this.bodies.get(i)) && !stopFlag.isStopped()) {
                try {
                    V2d forceByOtherBody = b.computeRepulsiveForceBy(this.bodies.get(i));
                    totalForce.sum(forceByOtherBody);
                } catch (Exception ex) {
                    System.out.println("" + ex);
                }
            }
        }
        totalForce.sum(b.getCurrentFrictionForce());

        return totalForce;
    }

}
