package com.proyectojr.electricalsupplies.domain.service;

import com.proyectojr.electricalsupplies.domain.model.ClientType;
import com.proyectojr.electricalsupplies.domain.repository.ClientTypeRepository;

import java.util.List;
import java.util.Optional;

// Contiene la lógica de negocio relacionada con tipos de cliente.
public class ClientTypeDomainService {
    private final ClientTypeRepository clientTypeRepository;

    public ClientTypeDomainService(ClientTypeRepository clientTypeRepository) {
        this.clientTypeRepository = clientTypeRepository;
    }

    // Obtiene todos los tipos de cliente
    public List<ClientType> getAllClientTypes() {
        return clientTypeRepository.findAll();
    }

    // Busca un tipo de cliente por su ID
    public Optional<ClientType> getClientTypeById(int clientTypeId) {
        return clientTypeRepository.findById(clientTypeId);
    }

    // Busca un tipo de cliente por su nombre
    public Optional<ClientType> getClientTypeByName(String typeName) {
        return clientTypeRepository.findByName(typeName);
    }

    // Agrega un nuevo tipo de cliente
    public void addClientType(ClientType clientType) {
        clientTypeRepository.save(clientType);
    }

    // Actualiza un tipo de cliente existente
    public void updateClientType(ClientType clientType) {
        clientTypeRepository.update(clientType);
    }

    // Elimina un tipo de cliente por su ID
    public void deleteClientType(int clientTypeId) {
        clientTypeRepository.delete(clientTypeId);
    }
}