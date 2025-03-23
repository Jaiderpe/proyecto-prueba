package com.proyectojr.electricalsupplies.domain.service;

import com.proyectojr.electricalsupplies.domain.model.User;
import com.proyectojr.electricalsupplies.domain.repository.UserRepository;

import java.util.List;
import java.util.Optional;

// Contiene la lógica de negocio relacionada con usuarios.
public class UserDomainService {
    private final UserRepository userRepository;

    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtiene todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Busca un usuario por su ID
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    // Busca un usuario por su correo electrónico
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Agrega un nuevo usuario
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Actualiza un usuario existente
    public void updateUser(User user) {
        userRepository.update(user);
    }

    // Elimina un usuario por su ID
    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }
}