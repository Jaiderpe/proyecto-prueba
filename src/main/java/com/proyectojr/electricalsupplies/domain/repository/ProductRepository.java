package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.Product;

import java.util.List;
import java.util.Optional;

// Define los métodos necesarios para acceder a los datos de productos.
public interface ProductRepository {
    List<Product> findAll();                      // Obtener todos los productos
    Optional<Product> findById(int productId);    // Buscar un producto por ID
    void save(Product product);                   // Guardar un nuevo producto
    void update(Product product);                 // Actualizar un producto existente
    void delete(int productId);                   // Eliminar un producto por ID
}