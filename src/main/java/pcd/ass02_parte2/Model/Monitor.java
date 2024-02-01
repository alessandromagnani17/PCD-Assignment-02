package pcd.ass02_parte2.Model;

/**
 * Represents the Monitor used for interrupt the analyze.
 *
 */
public class Monitor {

    private boolean stop;

    /**
     * Creates a new Monitor.
     *
     */
    public Monitor() {
        this.stop = false;
    }

    /**
     * Change state of the Monitor's variable.
     *
     */
    public synchronized void stop(){ this.stop = true; }

    /**
     * Reset state of the Monitor's variable.
     *
     */
    public synchronized void resetStop(){ this.stop = false; }

    /**
     * Check if the Monitor's variable is setted.
     *
     * @return the state of the Monitor's variable.
     */
    public synchronized boolean isStopped(){ return this.stop; }

}
