package com.hotel;


import java.time.LocalDate;


public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestCount;

    public Reservation(int id, Client client, Room room, LocalDate startDate, LocalDate endDate, int guestCount) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestCount = guestCount;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getGuestCount() {
        return guestCount;
    }
}
