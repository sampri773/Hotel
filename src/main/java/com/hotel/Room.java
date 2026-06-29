package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class Room {
    private int id;
    private Client client;
    private double price;
    private List<Bed> bed;
    private PlaceNumber capacity;
    private List<Reservation> reservations;
    private RoomStatus roomStatus;

    public RoomStatus getRoomStatus(LocalDate date) {
        for (Reservation r : reservations) {
            if (!date.isBefore(r.getStartDate()) && !date.isAfter(r.getEndDate())) {
                return RoomStatus.OCCUPIED;
            }
        }
        return RoomStatus.FREE;
    }

    public boolean isOverlap(LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            boolean overlap = start.isBefore(r.getEndDate()) && end.isAfter(r.getStartDate());
            if (overlap) {
                return true;
            }
        }
        return false;
    }
}
