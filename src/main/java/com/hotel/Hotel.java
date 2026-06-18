package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class Hotel {

    private int id;
    private String name;
    private String phone;
    private String mail;
    private Rate rate;
    private List<Room> room;

}
