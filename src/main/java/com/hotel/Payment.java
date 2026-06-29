package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {

        private String id;
        private Reservation reservation;
        private double price;
        private PaymentMode mode;

        public String recuGenerate() {
            return "Reçu Paiement #" + id +
                    "\nClient: " + reservation.getClient().getFirstName() +
                    "\nMontant: " + price +
                    "\nMode: " + mode;
        }


}
