package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.ClientType;
import com.proyectojr.electricalsupplies.domain.repository.ClientTypeRepository;

import java.util.List;
import java.util.Optional;

public class ClientTypeService {
    private final ClientTypeRepository clientTypeRepository;

    public ClientTypeService(ClientTypeRepository clientTypeRepository) {
        this.clientTypeRepository = clientTypeRepository;
    }

    public List<ClientType> getAllClientTypes() {
        return clientTypeRepository.findAll();
    }

    public void addClientType(ClientType clientType) {
        clientTypeRepository.save(clientType);
    }

    public void updateClientType(ClientType clientType) {
        clientTypeRepository.update(clientType);
    }

    public void deleteClientType(int clientTypeId) {
        clientTypeRepository.delete(clientTypeId);
    }

    // Busca un tipo de cliente por su ID
    public ClientType getClientTypeById(int clientTypeId) {
        Optional<ClientType> clientType = clientTypeRepository.findById(clientTypeId);
        return clientType.orElse(null); // Devuelve null si no se encuentra
    }

    // Busca un tipo de cliente por su nombre
    public ClientType getClientTypeByName(String typeName) {
        Optional<ClientType> clientType = clientTypeRepository.findByName(typeName);
        return clientType.orElse(null); // Devuelve null si no se encuentra
    }
}