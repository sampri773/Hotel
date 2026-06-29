package com.hotel;

public class Bed {
    private  int id;
    private BedTypes bedTypes;

    public Bed(int id, BedTypes bedTypes) {
        this.id = id;
        this.bedTypes = bedTypes;
    }

    public int getId() {
        return id;
    }

    public BedTypes getBedTypes() {
        return bedTypes;
    }
}

