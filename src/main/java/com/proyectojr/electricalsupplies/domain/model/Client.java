package com.proyectojr.electricalsupplies.domain.model;

public class Client {
    private int idClient;
    private String name;
    private String email;
    private String phone;
    private int clientIdType;

    // Constructor vacío
    public Client() {}

    // Constructor con parámetros
    public Client(int idClient, String name, String email, String phone, int clientIdType) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clientIdType = clientIdType;
    }

    // Getters y Setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getClientIdType() {
        return clientIdType;
    }

    public void setClientIdType(int clientIdType) {
        this.clientIdType = clientIdType;
    }
}