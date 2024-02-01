package pcd.ass02_parte1.Controller.Performance;

import pcd.ass02_parte1.Model.Body;
import pcd.ass02_parte1.Model.V2d;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Represents the Task for the first operation.
 * The first operations consists in:
 *          - Compute total force of the bodies specified in the constructor's parameters.
 *          - Update velocity of the bodies specified in the constructor's parameters.
 *
 */
public class FirstOperationTask2 implements Callable<Void> {

    private final ArrayList<Body> bodies;
    private final double dt;
    private final int startIndex;
    private final int nBalls;

    /**
     * Creates a new FirstOperationTask2.
     *
     * @param bodies     The list of bodies.
     * @param dt         The virtual time step.
     * @param startIndex The index of the first ball to manage.
     * @param nBalls     The number of balls to manage.
     */
    public FirstOperationTask2(ArrayList<Body> bodies, double dt, int startIndex, int nBalls) {
        this.bodies = bodies;
        this.dt = dt;
        this.startIndex = startIndex;
        this.nBalls = nBalls;
    }

    /**
     * Body of the task.
     *
     */
    @Override
    public Void call(){

        computeTotalForceAndUpdateVelocity();

        return null;
    }

    /**
     * Compute total force and update velocity for @nBalls balls starting from @startIndex (without any checks for the Monitor's variable).
     *
     */
    private void computeTotalForceAndUpdateVelocity(){
        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {
            V2d totalForce = computeTotalForceOnBody(this.bodies.get(i));
            V2d acc = new V2d(totalForce).scalarMul(1.0 / this.bodies.get(i).getMass());
            this.bodies.get(i).updateVelocity(acc, this.dt);
        }
    }

    /**
     * Compute total force on a body (without any checks for the Monitor's variable).
     *
     */
    private V2d computeTotalForceOnBody(Body b) {
        V2d totalForce = new V2d(0, 0);

        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {

            if (!b.equals(this.bodies.get(i))) {
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
