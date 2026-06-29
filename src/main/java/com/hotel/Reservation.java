package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestCount;
}
