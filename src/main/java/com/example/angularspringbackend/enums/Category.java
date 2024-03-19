package com.example.angularspringbackend.enums;

public enum Category {
    BACKEND(1),
    FRONTEND(2);

    private int valorOrdinal;

    Category(int valorOrdinal) {
        this.valorOrdinal = valorOrdinal;
    }

    public int getValue() {
        return valorOrdinal;
    }
 }
