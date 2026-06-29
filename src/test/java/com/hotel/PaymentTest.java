package com.hotel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private final Reservation mockReservation = new Reservation();

    @Test
    void testVerifyPayment_Success_MobileMoney() {
        Payment validPayment = new Payment(
                "PAY-001",
                mockReservation,
                150.0,
                PaymentMode.MOBILEMONEY,
                "1234567890",
                null
        );

        assertTrue(validPayment.verifyPayment(), "Mobile Money payment should be valid.");
    }

    @Test
    void testVerifyPayment_Failure_InvalidCard() {
        Payment invalidPayment = new Payment(
                "PAY-002",
                mockReservation,
                85.50,
                PaymentMode.CARD,
                null,
                "1234-ABCD-5678-EF"
        );

        assertFalse(invalidPayment.verifyPayment(), "Card payment is expected to fail due to the format.");
    }
}

class Reservation {}
enum PaymentMode { CARD, MOBILEMONEY }