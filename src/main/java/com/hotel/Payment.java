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
        private String reference;
        private String cardNumber;


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