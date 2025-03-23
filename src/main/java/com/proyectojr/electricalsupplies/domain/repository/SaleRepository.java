package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    // Obtener todas las ventas
    List<Sale> findAll();

    // Buscar una venta por su ID
    Optional<Sale> findById(int idSale);

    // Guardar una nueva venta
    void save(Sale sale);

    // Actualizar una venta existente
    void update(Sale sale);

    // Eliminar una venta por su ID
    void delete(int idSale);
}