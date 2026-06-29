package com.hotel;



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

        public Payment(String id, Reservation reservation, double price, PaymentMode mode) {
                this.id = id;
                this.reservation = reservation;
                this.price = price;
                this.mode = mode;
        }

        public String getId() {
                return id;
        }

        public Reservation getReservation() {
                return reservation;
        }

        public double getPrice() {
                return price;
        }

        public PaymentMode getMode() {
                return mode;
        }
}
