package com.mattstine.dddworkshop.pizzashop.infrastructure.events.ports;

import java.util.List;

/**
 * @author Matt Stine
 */
public interface EventLog {

    EventLog IDENTITY = new EventLog() {
        @Override
        public void publish(Topic topic, Event event) {
            throw new RuntimeException("Not implemented!");
        }

        @Override
        public void subscribe(Topic topic, EventHandler handler) {
            throw new RuntimeException("Not implemented!");
        }

        @Override
        public int getNumberOfSubscribers(Topic topic) {
            return -1;
        }

        @Override
        public List<Event> eventsBy(Topic topic) {
            return null;
        }
    };

    void publish(Topic topic, Event event);

    void subscribe(Topic topic, EventHandler handler);

    @SuppressWarnings("unused")
    int getNumberOfSubscribers(Topic topic);

    List<Event> eventsBy(Topic topic);

}
