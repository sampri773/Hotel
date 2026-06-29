import com.hotel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelTest {
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;

    private Client john;
    private Client louis;
    private Client guy;
    private Client marc;
    private Client charles;

    private Bed bed1;
    private Bed bed2;
    private Bed bed3;

    private Hotel hotel;

    private Reservation reservation1;
    private Reservation reservation2;

    @BeforeEach
    void setUp() {

        // Beds
        bed1 = new Bed(1, BedTypes.DOUBLE);
        bed2 = new Bed(2, BedTypes.SIMPLE);
        bed3 = new Bed(3, BedTypes.CUSTOMIZED);

        // Clients
        john = new Client(1, "Cena", "John", "john@gmail.com", "0389545612");
        louis = new Client(2, "Rabe", "Louis", "louis@gmail.com", "0321545879");
        guy = new Client(3, "Jean", "Guy", "guy@gmail.com", "0324157418");
        charles = new Client(4, "Leroy", "Charles", "charles@gmail.com", "0345987744");
        marc = new Client(5, "Didier", "Marc", "marc@gmail.com", "0344596687");

        // Rooms (sans réservation encore)
        room1 = new Room(1, guy, 10, List.of(bed1, bed2), PlaceNumber.THREE, List.of(), RoomStatus.FREE);
        room2 = new Room(2, louis, 5, List.of(bed1), PlaceNumber.TWO, List.of(), RoomStatus.FREE);
        room3 = new Room(3, null, 3, List.of(bed2), PlaceNumber.ONE, List.of(), RoomStatus.FREE);
        room4 = new Room(4, null, 10, List.of(bed3), PlaceNumber.CUSTOMIZED, List.of(), RoomStatus.FREE);

        // Reservations (IMPORTANT: maintenant room existe)
        reservation1 = new Reservation(
                1, john, room1,
                LocalDate.of(2026, 7, 4),
                LocalDate.of(2026, 7, 6),
                3
        );

        var reservation1 = new Reservation(1, john, null, LocalDate.of(2026, 7, 4), LocalDate.of(2026, 7, 6), 3);
        var reservation2 = new Reservation(2, charles, null, LocalDate.of(2026, 7, 15), LocalDate.of(2026,7, 30), 1);
        var reservation3 = new Reservation(3, marc, null, LocalDate.of(2026,7, 4), LocalDate.of(2026, 7, 8), 3);

        // Ajouter réservations aux rooms
        room1.getReservations().add(reservation1);
        room3.getReservations().add(reservation2);

        reservation1.setRoom(room1);
        reservation2.setRoom(room3);
        reservation3.setRoom(room1);

        var manager = new Manager(1, "Jack", "Brel", "brel@gmail.com");

    }

    @Test
    void seeIfTheRoom1IsOccupied() {
        RoomStatus status = room1.getRoomStatus(LocalDate.of(2026, 6, 20));
        assertEquals(RoomStatus.OCCUPIED, status);
    }

    @Test
    void seeIfTheRoom3IsFree() {
        RoomStatus status = room3.getRoomStatus(LocalDate.of(2026, 6, 29));
        assertEquals(RoomStatus.FREE, status);
    }

    @Test
    void seeIfThereIsOverlapReservationInRoom1ShouldReturnTrue() {
        boolean result = room1.isOverlap(LocalDate.of(2026, 7, 4), LocalDate.of(2026, 7, 8));
        assertEquals(true, result);
    }

    @Test
    void seeIfThereIsNoOverlapReservationInRoom2ShouldReturnFalse() {
        boolean result = room2.isOverlap(LocalDate.of(2026, 9, 2), LocalDate.of(2026, 9, 10));
        assertEquals(false, result);
    }
}
