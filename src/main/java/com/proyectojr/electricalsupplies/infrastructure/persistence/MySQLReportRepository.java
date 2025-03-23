package com.proyectojr.electricalsupplies.infrastructure.persistence;

import com.proyectojr.electricalsupplies.domain.model.Report;
import com.proyectojr.electricalsupplies.infrastructure.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLReportRepository {
    private final Connection connection;

    public MySQLReportRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Obtener el total de ventas en un período específico
    public List<Report> getTotalSalesByPeriod(String startDate, String endDate) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT SUM(total) AS total_sales, COUNT(*) AS sale_count FROM sales WHERE date BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double totalSales = resultSet.getDouble("total_sales");
                int saleCount = resultSet.getInt("sale_count");
                reports.add(new Report("Ventas totales", totalSales, saleCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Obtener los productos más vendidos
    public List<Report> getTopSellingProducts() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT p.name AS product_name, SUM(sd.quantity) AS total_quantity " +
                       "FROM sale_details sd " +
                       "JOIN products p ON sd.id_product = p.id_product " +
                       "GROUP BY p.id_product " +
                       "ORDER BY total_quantity DESC LIMIT 5";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                int totalQuantity = resultSet.getInt("total_quantity");
                reports.add(new Report(productName, totalQuantity, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Obtener los clientes más frecuentes
    public List<Report> getFrequentClients() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT c.name AS client_name, COUNT(*) AS purchase_count " +
                       "FROM sales s " +
                       "JOIN clients c ON s.id_client = c.id_client " +
                       "GROUP BY c.id_client " +
                       "ORDER BY purchase_count DESC LIMIT 5";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String clientName = resultSet.getString("client_name");
                int purchaseCount = resultSet.getInt("purchase_count");
                reports.add(new Report(clientName, purchaseCount, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}