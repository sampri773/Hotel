package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class Hotel {
    private int id;
    private String name;
    private String numTelephone;
    private String mail;
    private String address;
    private Rate rate;
    private List<Room> rooms;

}
