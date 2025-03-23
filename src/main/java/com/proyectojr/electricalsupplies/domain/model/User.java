package com.proyectojr.electricalsupplies.domain.model;

// Representa un usuario del sistema.
public class User {
    private int idUser;    // Identificador único del usuario
    private String name;   // Nombre del usuario
    private String email;  // Correo electrónico del usuario
    private String password; // Contraseña del usuario

    // Getters and Setters
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}