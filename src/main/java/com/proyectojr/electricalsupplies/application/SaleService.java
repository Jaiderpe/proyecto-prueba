package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.Sale;
import com.proyectojr.electricalsupplies.domain.repository.SaleRepository;

import java.util.List;
import java.util.Optional;

public class SaleService {
    private final SaleRepository saleRepository;

    // Constructor que acepta un SaleRepository
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(int idSale) {
        Optional<Sale> sale = saleRepository.findById(idSale);
        return sale.orElse(null);
    }

    public void addSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void updateSale(Sale sale) {
        saleRepository.update(sale);
    }

    public void deleteSale(int idSale) {
        saleRepository.delete(idSale);
    }
}