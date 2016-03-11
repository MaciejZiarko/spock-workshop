package spockworkshop.basics.interaction;

import java.util.List;

public class Publisher {

    private final List<Subscriber> subscribers;

    public Publisher(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void send(String message) {
        subscribers.forEach(subscriber -> subscriber.receive(message));
    }
}
