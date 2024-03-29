package com.serverSide.javaSpringBoot.services;

import com.serverSide.javaSpringBoot.model.ProductForSale;

import java.util.List;
import java.util.Optional;

public interface ProductForSaleService {

     ProductForSale createAndUpdate(ProductForSale productForSale);

    List<ProductForSale> saveAll(List<ProductForSale> productForSales);

     ProductForSale update(ProductForSale productForSale);

     Optional<ProductForSale> findById(long id);

     List<ProductForSale> findAll();

     List<ProductForSale> findAllByIds(List<Long>ids);

      void delete(long id);
}
