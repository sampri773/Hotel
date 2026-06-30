package com.hotel;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Hotel(int id, String name, String phone, String mail, String address, Rate rate, Manager manager, List<Room> rooms, List<Client> clients, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.rate = rate;
        this.manager = manager;
        this.rooms = rooms;
        this.clients = clients;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public Rate getRate() {
        return rate;
    }

    public Manager getManager() {
        return manager;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

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


        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Les dates ne peuvent pas être nulles.");
        }

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }


        if (client == null) {
            throw new IllegalArgumentException("Client introuvable.");
        }


        if (room == null) {
            throw new IllegalArgumentException("Chambre introuvable.");
        }


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