package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reservation {
    private final  List<Room> rooms = new ArrayList<>();
    private final  List<Booking> bookings = new ArrayList<>();
}
