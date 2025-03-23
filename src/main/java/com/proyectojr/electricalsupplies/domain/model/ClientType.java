package com.proyectojr.electricalsupplies.domain.model;

public class ClientType {
    private int idClientType;
    private String name;

    // Constructor vacío
    public ClientType() {}

    // Constructor con parámetros
    public ClientType(int idClientType, String name) {
        this.idClientType = idClientType;
        this.name = name;
    }

    // Getters y Setters
    public int getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(int idClientType) {
        this.idClientType = idClientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}