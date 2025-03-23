package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.User;

import java.util.List;
import java.util.Optional;

// Define los métodos necesarios para acceder a los datos de usuarios.
public interface UserRepository {
    List<User> findAll();                      // Obtener todos los usuarios
    Optional<User> findById(int userId);       // Buscar un usuario por ID
    Optional<User> findByEmail(String email);  // Buscar un usuario por correo electrónico
    void save(User user);                      // Guardar un nuevo usuario
    void update(User user);                    // Actualizar un usuario existente
    void delete(int userId);                   // Eliminar un usuario por ID
}