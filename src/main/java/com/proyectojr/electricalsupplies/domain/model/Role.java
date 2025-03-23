package com.proyectojr.electricalsupplies.domain.model;

public class Role {
    private int idRole;
    private String name;

    // Constructor vacío
    public Role() {}

    // Constructor con parámetros
    public Role(int idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    // Getters y Setters
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}