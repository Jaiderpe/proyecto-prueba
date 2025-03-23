package com.proyectojr.electricalsupplies.domain.service;

import com.proyectojr.electricalsupplies.domain.model.Client;
import com.proyectojr.electricalsupplies.domain.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

// Contiene la lógica de negocio relacionada con clientes.
public class ClientDomainService {
    private final ClientRepository clientRepository;

    public ClientDomainService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Obtiene todos los clientes
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Busca un cliente por su ID
    public Optional<Client> getClientById(int clientId) {
        return clientRepository.findById(clientId);
    }

    // Busca un cliente por su correo electrónico
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    // Agrega un nuevo cliente
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    // Actualiza un cliente existente
    public void updateClient(Client client) {
        clientRepository.update(client);
    }

    // Elimina un cliente por su ID
    public void deleteClient(int clientId) {
        clientRepository.delete(clientId);
    }
}