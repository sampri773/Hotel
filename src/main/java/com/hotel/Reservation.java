package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Reservation {
    private String id;
    private Client client;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestCount;
}
;