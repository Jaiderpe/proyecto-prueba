package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.Client;

import java.util.List;
import java.util.Optional;

// Define los métodos necesarios para acceder a los datos de clientes.
public interface ClientRepository {
    List<Client> findAll();                      // Obtener todos los clientes
    Optional<Client> findById(int clientId);     // Buscar un cliente por ID
    Optional<Client> findByEmail(String email);  // Buscar un cliente por correo electrónico
    void save(Client client);                    // Guardar un nuevo cliente
    void update(Client client);                  // Actualizar un cliente existente
    void delete(int clientId);                   // Eliminar un cliente por ID
}