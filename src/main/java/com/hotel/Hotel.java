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
    public boolean isAvailable(Room room,
                               LocalDate dateDebut,
                               LocalDate dateFin) {

        for (Reservation r : reservations) {

            if (r.getRoom().getId() == room.getId()) {

                boolean chevauchement =
                        dateDebut.isBefore(r.getEndDate())
                                && dateFin.isAfter(r.getStartDate());

                if (chevauchement) {
                    return false;
                }
            }
        }

        return true;
    }

    public Reservation createReservation(Client client,Room room,LocalDate startDate,LocalDate endDate,int guestCount){

        // Vérifier les dates
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Les dates ne peuvent pas être nulles.");
        }

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }

        // Vérifier que le client existe
        if (client == null) {
            throw new IllegalArgumentException("Client introuvable.");
        }

        // Vérifier que la chambre existe
        if (room == null) {
            throw new IllegalArgumentException("Chambre introuvable.");
        }

        // Vérifier la capacité
        if (guestCount > room.getCapacity().ordinal()) {
            throw new IllegalArgumentException("La capacité de la chambre est insuffisante.");
        }

        var reservation = new Reservation(1,client,room,startDate,endDate,guestCount);
        boolean available = isAvailable(room,startDate,endDate);
        if (!available) {
            throw new IllegalStateException("La chambre est déjà réservée pour cette période.");
        }
        return reservation;
    }

}