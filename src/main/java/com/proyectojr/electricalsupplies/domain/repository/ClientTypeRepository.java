package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.ClientType;
import java.util.Optional;
import java.util.List;

public interface ClientTypeRepository {
    List<ClientType> findAll(); // Obtener todos los tipos de cliente
    Optional<ClientType> findById(int clientTypeId); // Buscar un tipo de cliente por ID
    Optional<ClientType> findByName(String typeName); // Buscar un tipo de cliente por nombre
    void save(ClientType clientType); // Guardar un nuevo tipo de cliente
    void update(ClientType clientType); // Actualizar un tipo de cliente existente
    void delete(int clientTypeId); // Eliminar un tipo de cliente por ID
}