package com.proyectojr.electricalsupplies.domain.service;

import com.proyectojr.electricalsupplies.domain.model.Product;
import com.proyectojr.electricalsupplies.domain.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

// Contiene la lógica de negocio relacionada con el inventario.
public class InventoryDomainService {
    private final ProductRepository productRepository;

    public InventoryDomainService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Encuentra productos con stock bajo (stock < umbral)
    public List<Product> findLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStock() < product.getThreshold())
                .collect(Collectors.toList());
    }
}