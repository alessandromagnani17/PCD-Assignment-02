package pcd.ass02_parte1.Controller.Performance;

import pcd.ass02_parte1.Model.Body;
import pcd.ass02_parte1.Model.Boundary;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Represents the Task for the second operation.
 * The first operations consists in:
 *          - Update the position of the bodies specified in the constructor's parameters.
 *          - Check for eventually boundary collisions of the bodies specified in the constructor's parameters.
 *
 */
public class SecondAndThirdOperationTask2 implements Callable<Void> {

    private final ArrayList<Body> bodies;
    private final Boundary bounds;
    private final double dt;
    private final int startIndex;
    private final int nBalls;

    /**
     * Creates a new SecondAndThirdOperationTask2.
     *
     * @param bodies     The list of bodies.
     * @param dt         The virtual time step.
     * @param bounds     The boundary of the field.
     * @param startIndex The index of the first ball to manage.
     * @param nBalls     The number of balls to manage.
     */
    public SecondAndThirdOperationTask2(ArrayList<Body> bodies, double dt, Boundary bounds, int startIndex, int nBalls) {
        this.bodies = bodies;
        this.dt = dt;
        this.bounds = bounds;
        this.startIndex = startIndex;
        this.nBalls = nBalls;
    }

    /**
     * Body of the task.
     *
     */
    @Override
    public Void call(){
        updatePosition();
        checkBoundaryCollision();

        return null;
    }

    /**
     * Update the position for @nBalls balls starting from @startIndex (without any checks for the Monitor's variable).
     *
     */
    private void updatePosition(){
        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {
            this.bodies.get(i).updatePos(this.dt);
        }
    }

    /**
     * Check for eventually boundary collisions of the @nBalls balls starting from @startIndex (without any checks for the Monitor's variable).
     *
     */
    private void checkBoundaryCollision(){
        for (int i = this.startIndex; i < this.startIndex + this.nBalls; i++) {
            this.bodies.get(i).checkAndSolveBoundaryCollision(this.bounds);
        }
    }
}
