package pcd.ass02_parte2.Controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import pcd.ass02_parte2.Controller.ControllerView;

/**
 * Represents the Agent that reads the message from the channel.
 *
 */
public class AgentReceiver extends AbstractVerticle {

    private final ControllerView controllerView;
    private final Boolean flag;
    private final String topic;

    /**
     * Creates a new AgentReceiver for the GUI version (point B).
     *
     * @param controllerView The instance of ControllerView.
     * @param topic          The name of the channel.
     */
    public AgentReceiver(ControllerView controllerView, String topic) {
        this.flag = true;
        this.controllerView = controllerView;
        this.topic = topic;
    }

    /**
     * Creates a new AgentReceiver for the last method of point A.
     *
     * @param topic The name of the channel.
     */
    public AgentReceiver(String topic) {
        this.flag = false;
        this.controllerView = null;
        this.topic = topic;
    }

    /**
     * Body of the Verticle.
     *
     */
    public void start(Promise<Void> startPromise) {
        log("Starting Agent1 ...");

        EventBus eb = this.getVertx().eventBus();

        eb.consumer(this.topic, message -> {
            if(this.flag){
                this.controllerView.addElementInformation(message.body().toString());
            } else {
                log("!!! Element received !!!\n" + message.body() + "\n");
            }
        });

        log("Agent1 ready to receive messages ...");
        startPromise.complete();
    }

    private void log(String msg) {
        System.out.println("[REACTIVE AGENT #1] " + msg);
    }
}
