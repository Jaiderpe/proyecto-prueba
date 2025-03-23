package com.proyectojr.electricalsupplies.infrastructure.persistence;

import com.proyectojr.electricalsupplies.domain.model.Role;
import com.proyectojr.electricalsupplies.domain.repository.RoleRepository;
import com.proyectojr.electricalsupplies.infrastructure.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLRoleRepository implements RoleRepository {
    private final Connection connection;

    public MySQLRoleRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM roles";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setIdRole(resultSet.getInt("id_role"));
                role.setName(resultSet.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Optional<Role> findById(int roleId) {
        String query = "SELECT * FROM roles WHERE id_role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role();
                role.setIdRole(resultSet.getInt("id_role"));
                role.setName(resultSet.getString("name"));
                return Optional.of(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(String roleName) {
        String query = "SELECT * FROM roles WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roleName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role();
                role.setIdRole(resultSet.getInt("id_role"));
                role.setName(resultSet.getString("name"));
                return Optional.of(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Role role) {
        String query = "INSERT INTO roles (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE roles SET name = ? WHERE id_role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getIdRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int roleId) {
        String query = "DELETE FROM roles WHERE id_role = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}