package com.proyectojr.electricalsupplies.domain.model;

public class Report {
    private String description; // Descripción del reporte
    private double value;      // Valor asociado al reporte (por ejemplo, total de ventas)
    private int count;         // Cantidad asociada al reporte (por ejemplo, número de ventas)

    // Constructor
    public Report(String description, double value, int count) {
        this.description = description;
        this.value = value;
        this.count = count;
    }

    // Getters y Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}