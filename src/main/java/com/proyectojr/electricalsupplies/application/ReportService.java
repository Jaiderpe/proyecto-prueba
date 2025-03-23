package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.Report;
import com.proyectojr.electricalsupplies.infrastructure.persistence.MySQLReportRepository;

import java.util.List;

public class ReportService {
    private final MySQLReportRepository reportRepository;

    public ReportService(MySQLReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Generar reporte de ventas totales
    public List<Report> generateTotalSalesReport(String startDate, String endDate) {
        return reportRepository.getTotalSalesByPeriod(startDate, endDate);
    }

    // Generar reporte de productos más vendidos
    public List<Report> generateTopSellingProductsReport() {
        return reportRepository.getTopSellingProducts();
    }

    // Generar reporte de clientes frecuentes
    public List<Report> generateFrequentClientsReport() {
        return reportRepository.getFrequentClients();
    }
}