package com.proyectojr.electricalsupplies.infrastructure.persistence;

import com.proyectojr.electricalsupplies.domain.model.SaleDetail;
import com.proyectojr.electricalsupplies.domain.repository.SaleDetailRepository;
import com.proyectojr.electricalsupplies.infrastructure.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLSaleDetailRepository implements SaleDetailRepository {
    private final Connection connection;

    public MySQLSaleDetailRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<SaleDetail> findAllBySaleId(int idSale) {
        List<SaleDetail> saleDetails = new ArrayList<>();
        String query = "SELECT * FROM sale_details WHERE id_sale = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSale);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setIdDetail(resultSet.getInt("id_detail"));
                saleDetail.setIdSale(resultSet.getInt("id_sale"));
                saleDetail.setIdProduct(resultSet.getInt("id_product"));
                saleDetail.setQuantity(resultSet.getInt("quantity"));
                saleDetail.setUnitPrice(resultSet.getDouble("unit_price"));
                saleDetail.setSubtotal(resultSet.getDouble("subtotal"));
                saleDetails.add(saleDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saleDetails;
    }

    @Override
    public Optional<SaleDetail> findById(int idDetail) {
        String query = "SELECT * FROM sale_details WHERE id_detail = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setIdDetail(resultSet.getInt("id_detail"));
                saleDetail.setIdSale(resultSet.getInt("id_sale"));
                saleDetail.setIdProduct(resultSet.getInt("id_product"));
                saleDetail.setQuantity(resultSet.getInt("quantity"));
                saleDetail.setUnitPrice(resultSet.getDouble("unit_price"));
                saleDetail.setSubtotal(resultSet.getDouble("subtotal"));
                return Optional.of(saleDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(SaleDetail detail) {
        String query = "INSERT INTO sale_details (id_sale, id_product, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detail.getIdSale());
            statement.setInt(2, detail.getIdProduct());
            statement.setInt(3, detail.getQuantity());
            statement.setDouble(4, detail.getUnitPrice());
            statement.setDouble(5, detail.getSubtotal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SaleDetail detail) {
        String query = "UPDATE sale_details SET id_sale = ?, id_product = ?, quantity = ?, unit_price = ?, subtotal = ? WHERE id_detail = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, detail.getIdSale());
            statement.setInt(2, detail.getIdProduct());
            statement.setInt(3, detail.getQuantity());
            statement.setDouble(4, detail.getUnitPrice());
            statement.setDouble(5, detail.getSubtotal());
            statement.setInt(6, detail.getIdDetail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int idDetail) {
        String query = "DELETE FROM sale_details WHERE id_detail = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDetail);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}