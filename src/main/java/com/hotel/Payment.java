package com.hotel;

public class Payment {

        private String id;
        private Reservation reservation;
        private double price;
        private PaymentMode mode;
        private String reference;
        private String cardNumber;

        public Payment(String id, Reservation reservation, double price, PaymentMode mode, String reference, String cardNumber) {
                this.id = id;
                this.reservation = reservation;
                this.price = price;
                this.mode = mode;
                this.reference = reference;
                this.cardNumber = cardNumber;
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

        public String getReference() {
                return reference;
        }

        public String getCardNumber() {
                return cardNumber;
        }


        public boolean isAmountValid() {
                return price > 0;
        }

        public boolean verifyPayment() {
                if (!isAmountValid()) {
                        return false;
                }

                switch (mode) {
                        case MOBILEMONEY:
                                return verifyReference();
                        case CARD:
                                return verifyCardNumber();
                        default:
                                return true;
                }
        }

        public boolean verifyReference() {
                if (reference == null) {
                        return false;
                }
                return reference.matches("^[0-9]{10}$");
        }

        public boolean verifyCardNumber() {
                if (cardNumber == null) {
                        return false;
                }
                return cardNumber.matches("^[0-9]{16}$");
        }

        @Override
        public String toString() {
                return "Payment{" +
                        "id='" + id + '\'' +
                        ", price=" + price +
                        ", mode=" + mode +
                        ", reference='" + reference + '\'' +
                        '}';
        }
}