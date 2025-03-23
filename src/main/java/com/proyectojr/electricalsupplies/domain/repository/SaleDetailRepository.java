package com.proyectojr.electricalsupplies.domain.repository;

import com.proyectojr.electricalsupplies.domain.model.SaleDetail;

import java.util.List;
import java.util.Optional;

public interface SaleDetailRepository {
    // Obtener todos los detalles de venta asociados a una venta específica
    List<SaleDetail> findAllBySaleId(int idSale);

    // Buscar un detalle de venta por su ID
    Optional<SaleDetail> findById(int idDetail);

    // Guardar un nuevo detalle de venta
    void save(SaleDetail detail);

    // Actualizar un detalle de venta existente
    void update(SaleDetail detail);

    // Eliminar un detalle de venta por su ID
    void delete(int idDetail);
}