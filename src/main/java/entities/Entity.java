package entities;

import java.util.List;
import java.util.Comparator;
import events.Event;
import resources.Server;
import utils.Statistics;
import events.ArrivalEvent;
import events.EndOfServiceEvent;
import java.util.LinkedList;

public abstract class Entity {
    private static final int classEntityId = 0;

    // attributes
    private int id;
    private double waitingTime;
    private double transitTime;

    // associations
    private Server attendingServer;
    private List<Event> events;

    // others
    /**
     * Used if it is necessary to order chronologically the events of this entity.
     */
    private Comparator<Event> comparator = Event.getComparator();

    public Entity(Statistics statistics) {
        statistics.addEntityIdCount(classEntityId);
        this.id = statistics.getEntityIdCount(classEntityId);
        this.waitingTime = 0;
        this.transitTime = 0;
        this.attendingServer = null;
        this.events = new LinkedList<Event>();
    }

    public abstract void affectAirstrip();

    public int getClassEntityId() {
        return classEntityId;
    }

    public static int getClassId() {
        return classEntityId;
    }

    public int getId() {
        return this.id;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTransitTime() {
        return this.transitTime;
    }

    public void setTransitTime(double transitTime) {
        this.transitTime = transitTime;
    }

    public ArrivalEvent getArrivalEvent() {
        return (ArrivalEvent) events.get(0);
    }

    public EndOfServiceEvent getEndOfServiceEvent() {
        return (EndOfServiceEvent) events.get(1);
    }

    public Server getAttendingServer() {
        return attendingServer;
    }

    public void setAttendingServer(Server attendingServer) {
        this.attendingServer = attendingServer;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvent(Event event) {
        events.add(event);
        events.sort(comparator);
    }
}
