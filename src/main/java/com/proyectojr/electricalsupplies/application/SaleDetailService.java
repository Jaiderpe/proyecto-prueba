package com.proyectojr.electricalsupplies.application;

import com.proyectojr.electricalsupplies.domain.model.SaleDetail;
import com.proyectojr.electricalsupplies.domain.repository.SaleDetailRepository;

import java.util.List;
import java.util.Optional;

public class SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    public List<SaleDetail> getAllDetailsBySaleId(int idSale) {
        return saleDetailRepository.findAllBySaleId(idSale);
    }

    public SaleDetail getDetailById(int idDetail) {
        Optional<SaleDetail> detail = saleDetailRepository.findById(idDetail);
        return detail.orElse(null);
    }

    public void addDetail(SaleDetail detail) {
        saleDetailRepository.save(detail);
    }

    public void updateDetail(SaleDetail detail) {
        saleDetailRepository.update(detail);
    }

    public void deleteDetail(int idDetail) {
        saleDetailRepository.delete(idDetail);
    }
}