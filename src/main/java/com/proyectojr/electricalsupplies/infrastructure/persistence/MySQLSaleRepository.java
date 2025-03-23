package com.proyectojr.electricalsupplies.infrastructure.persistence;

import com.proyectojr.electricalsupplies.domain.model.Sale;
import com.proyectojr.electricalsupplies.domain.repository.SaleRepository;
import com.proyectojr.electricalsupplies.infrastructure.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLSaleRepository implements SaleRepository {
    private final Connection connection;

    public MySQLSaleRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Sale sale = new Sale();
                sale.setIdSale(resultSet.getInt("id_sale"));
                sale.setIdClient(resultSet.getInt("id_client"));
                sale.setIdUser(resultSet.getInt("id_user"));
                sale.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                sale.setTotal(resultSet.getDouble("total"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public Optional<Sale> findById(int idSale) {
        String query = "SELECT * FROM sales WHERE id_sale = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSale);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Sale sale = new Sale();
                sale.setIdSale(resultSet.getInt("id_sale"));
                sale.setIdClient(resultSet.getInt("id_client"));
                sale.setIdUser(resultSet.getInt("id_user"));
                sale.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                sale.setTotal(resultSet.getDouble("total"));
                return Optional.of(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Sale sale) {
        String query = "INSERT INTO sales (id_client, id_user, total) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getIdClient());
            statement.setInt(2, sale.getIdUser());
            statement.setDouble(3, sale.getTotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Sale sale) {
        String query = "UPDATE sales SET id_client = ?, id_user = ?, total = ? WHERE id_sale = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getIdClient());
            statement.setInt(2, sale.getIdUser());
            statement.setDouble(3, sale.getTotal());
            statement.setInt(4, sale.getIdSale());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idSale) {
        String query = "DELETE FROM sales WHERE id_sale = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSale);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}