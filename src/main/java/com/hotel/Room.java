package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
