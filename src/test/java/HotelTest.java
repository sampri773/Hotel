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
    private Manager manager;
    private Reservation reservation1;
    private Reservation reservation2;
    private Reservation reservationX;
    private Hotel hotel;
    private Client louis;
    private Client john;
    private Client guy;
    private Client marc;
    private Bed bed1;
    private Bed bed2;
    private Bed bed3;

    @BeforeEach
    void setUp() {
        var bed1 = new Bed(1, BedTypes.DOUBLE);
        var bed2 = new Bed(2, BedTypes.SIMPLE);
        var bed3 = new Bed(3, BedTypes.CUSTOMIZED);

        var john = new Client(1, "Cena", "John", "john@gmail.com", "0389545612");
        var louis = new  Client(2, "Rabe", "Louis", "louis@gmail.com", "0321545879");
        var guy = new Client(3, "Jean", "Guy","guy@gmail.com", "0324157418");
        var charles = new Client(4, "Leroy", "Charles", "charles@gmail.com", "0345987744");
        var marc = new Client(5, "Didier", "Marc", "marc@gmail.com", "0344596687");

        var reservation1 = new Reservation(1, john, null, LocalDate.of(2026, 7, 4), LocalDate.of(2026, 7, 6), 3);
        var reservation2 = new Reservation(2, charles, null, LocalDate.of(2026, 7, 15), LocalDate.of(2026,7, 30), 1);
        var reservation3 = new Reservation(3, marc, null, LocalDate.of(2026,7, 4), LocalDate.of(2026, 7, 8), 3);

        var room1 = new Room(1, guy, 10, List.of(bed1, bed2), PlaceNumber.THREE, List.of(reservation1), RoomStatus.OCCUPIED);
        var room2 = new Room(2 , louis, 5, List.of(bed1), PlaceNumber.TWO, List.of(), RoomStatus.OCCUPIED);
        var room3 = new Room(3, null, 3, List.of(bed2), PlaceNumber.ONE, List.of(reservation2), RoomStatus.FREE);
        var room4 = new Room(4, null, 10, List.of(bed3), PlaceNumber.CUSTOMIZED, List.of(), RoomStatus.FREE);

        reservation1.setRoom(room1);
        reservation2.setRoom(room3);
        reservation3.setRoom(room1);

        var manager = new Manager(1, "Jack", "Brel", "brel@gmail.com");

        var hotel = new Hotel(1, "Beach Palm", "0345562488", "beachpalm@gmail.com", "Lot IVO 144 Ivandry", Rate.THREE, manager, List.of(room1, room2, room3), List.of(guy, louis), List.of(reservation1, reservation2));
    }

    @Test
    void seeIfTheRoom1IsOccupied() {
        RoomStatus status = room1.getRoomStatus(LocalDate.of(2026,6,20));
        assertEquals(RoomStatus.OCCUPIED, status);
    }

    @Test
    void seeIfTheRoom3IsFree() {
        RoomStatus status = room3.getRoomStatus(LocalDate.of(2026, 6, 29));
        assertEquals(RoomStatus.FREE, status);
    }

    @Test
    void seeIfThereIsOverlapReservationInRoom1ShouldReturnTrue() {
        boolean result = room1.isOverlap(LocalDate.of(2026, 7, 4), LocalDate.of(2026, 7,8));
        assertEquals(true, result);
    }

    @Test
    void seeIfThereIsNoOverlapReservationInRoom2ShouldReturnFalse() {
        boolean result = room2.isOverlap(LocalDate.of(2026, 9, 2), LocalDate.of(2026,9,10));
        assertEquals(false, result);
    }
}
