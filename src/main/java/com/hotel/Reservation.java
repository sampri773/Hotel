package com.hotel;


public class Reservation {


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class Reservation {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void createBooking(Booking booking) {
        if (booking.getGuestCount() > booking.getRoom().getBedCapacity()) {
            throw new IllegalArgumentException("The number of guests exceeds the room's bed capacity");

        }
        List<Room> freeRooms = getAvailableRooms(booking.getStartDate(), booking.getEndDate());
        if (!freeRooms.contains(booking.getRoom())) {
            throw new IllegalArgumentException("The room is not available");
        }
        bookings.add(booking);
    }

    public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("The start date must be before or equal to end date");
        }
        List<Room> bookedRooms = bookings.stream()
                .filter(booking -> isOverlapping(booking, startDate, endDate))
                .map(Booking::getRoom)
                .collect(Collectors.toList());

        return rooms.stream()
                .filter (room -> !bookedRooms.contains(room))
                .collect(Collectors.toList());
    }
    private boolean isOverlapping(Booking booking, LocalDtae requestedStart, LocalDate requestedEnd){
    return  booking.getStartDate().isBefore(requestedEnd) &&
            booking.getEndDate().isAfter(requestedStart);
    }
}
}