package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Hotel {
    private int id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private Rate rate;
    private Manager manager;
    private List<Room> rooms;
    private List<Client> clients;
    private List<Reservation> reservations;

    public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate, int guestCount) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean capacityOk = room.getCapacity() != null
                    && room.getCapacity().ordinal() + 1 >= guestCount;
            boolean dateFree = true;
            for (Reservation reservation : room.getReservations()) {
                boolean overlaps = startDate.isBefore(reservation.getEndDate())
                        && endDate.isAfter(reservation.getStartDate());
                if (overlaps) {
                    dateFree = false;
                    break;
                }
            }
            if (capacityOk && dateFree) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}