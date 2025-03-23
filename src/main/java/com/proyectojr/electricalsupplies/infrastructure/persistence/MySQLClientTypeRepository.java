package com.proyectojr.electricalsupplies.infrastructure.persistence;

import com.proyectojr.electricalsupplies.domain.model.ClientType;
import com.proyectojr.electricalsupplies.domain.repository.ClientTypeRepository;
import com.proyectojr.electricalsupplies.infrastructure.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLClientTypeRepository implements ClientTypeRepository {
    private final Connection connection;

    public MySQLClientTypeRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<ClientType> findAll() {
        List<ClientType> clientTypes = new ArrayList<>();
        String query = "SELECT * FROM client_types";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                ClientType clientType = new ClientType();
                clientType.setIdClientType(resultSet.getInt("id_client_type"));
                clientType.setName(resultSet.getString("name"));
                clientTypes.add(clientType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientTypes;
    }

    @Override
    public Optional<ClientType> findById(int clientTypeId) {
        String query = "SELECT * FROM client_types WHERE id_client_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientTypeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ClientType clientType = new ClientType();
                clientType.setIdClientType(resultSet.getInt("id_client_type"));
                clientType.setName(resultSet.getString("name"));
                return Optional.of(clientType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClientType> findByName(String typeName) {
        String query = "SELECT * FROM client_types WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, typeName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ClientType clientType = new ClientType();
                clientType.setIdClientType(resultSet.getInt("id_client_type"));
                clientType.setName(resultSet.getString("name"));
                return Optional.of(clientType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(ClientType clientType) {
        String query = "INSERT INTO client_types (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, clientType.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClientType clientType) {
        String query = "UPDATE client_types SET name = ? WHERE id_client_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, clientType.getName());
            statement.setInt(2, clientType.getIdClientType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int clientTypeId) {
        String query = "DELETE FROM client_types WHERE id_client_type = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientTypeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}