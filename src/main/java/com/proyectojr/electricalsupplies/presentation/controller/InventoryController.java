package com.proyectojr.electricalsupplies.presentation.controller;

import com.proyectojr.electricalsupplies.application.ProductService;
import com.proyectojr.electricalsupplies.domain.model.Product;

import java.util.List;

// Coordina las solicitudes del usuario con los casos de uso.
public class InventoryController {
    private final ProductService productService;

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }

    // Muestra todos los productos
    public void displayAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("Lista de productos:");
        for (Product product : products) {
            System.out.printf("ID: %d, Nombre: %s, Descripción: %s, Stock: %d%n",
                    product.getIdProduct(), product.getName(), product.getDescription(), product.getStock());
        }
    }
}