package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Reservation {
    private int id;
    private Client client;
    private Date dateDebut;
    private Date dateFin;
}
}