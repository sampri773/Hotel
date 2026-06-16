package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Room {
    private int id;
    private Client client;
    private Lit lit;
}
