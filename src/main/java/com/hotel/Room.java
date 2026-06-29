package com.hotel;



import java.time.LocalDate;
import java.util.List;

public class Room {
    private int id;
    private Client client;
    private double price;
    private List<Bed> bed;
    private PlaceNumber capacity;
    private List<Reservation> reservations;
    private RoomStatus roomStatus;

    public Room(int id, Client client, double price, List<Bed> bed, PlaceNumber capacity, List<Reservation> reservations, RoomStatus roomStatus) {
        this.id = id;
        this.client = client;
        this.price = price;
        this.bed = bed;
        this.capacity = capacity;
        this.reservations = reservations;
        this.roomStatus = roomStatus;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public double getPrice() {
        return price;
    }

    public List<Bed> getBed() {
        return bed;
    }

    public PlaceNumber getCapacity() {
        return capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public RoomStatus getRoomStatus(LocalDate date) {
        for (Reservation r : reservations) {
            if (!date.isBefore(r.getStartDate()) && !date.isAfter(r.getEndDate())) {
                return RoomStatus.OCCUPIED;
            }
        }
        return RoomStatus.FREE;
    }

    public boolean isOverlap(LocalDate start, LocalDate end) {
        for(Reservation r : reservations) {
            boolean overlap = !end.isBefore(r.getStartDate()) && !end.isAfter(r.getEndDate());
            if (overlap) {
                return false;
            }
        }
        return true;
    }
}
