package com.example;

import java.util.List;

public class Root {

    public Root(List<Tickets> tickets) {
        this.tickets = tickets;
    }

    private final List<Tickets> tickets;

    public List<Tickets> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Root{" +
                "tickets=" + tickets +
                '}';
    }
}
