package com.proyectojr.electricalsupplies.domain.model;

// Representa una entidad de producto en el sistema.
public class Product {
    private int idProduct;       // Identificador único del producto
    private String name;         // Nombre del producto
    private String description;  // Descripción del producto
    private double price;        // Precio del producto
    private int stock;           // Cantidad disponible en inventario
    private int threshold;       // Umbral mínimo de stock para alertas

    // Getters and Setters
    public int getIdProduct() { return idProduct; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getThreshold() { return threshold; }
    public void setThreshold(int threshold) { this.threshold = threshold; }
}