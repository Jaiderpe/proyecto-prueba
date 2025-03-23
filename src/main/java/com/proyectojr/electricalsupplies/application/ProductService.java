package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.Product;
import com.proyectojr.electricalsupplies.domain.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

// Define los casos de uso relacionados con productos.
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Obtiene todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Busca un producto por su ID
    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    // Agrega un nuevo producto
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    // Actualiza un producto existente
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    // Elimina un producto por su ID
    public void deleteProduct(int productId) {
        productRepository.delete(productId);
    }
}