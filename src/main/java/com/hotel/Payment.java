package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {

        private String id;
        private Reservation reservation;
        private double montant;
        private PaymentMode mode;

        public String genererRecu() {
            return "Reçu Paiement #" + id +
                    "\nClient: " + reservation.getClient().getNom() +
                    "\nMontant: " + montant +
                    "\nMode: " + mode;
        }


}
