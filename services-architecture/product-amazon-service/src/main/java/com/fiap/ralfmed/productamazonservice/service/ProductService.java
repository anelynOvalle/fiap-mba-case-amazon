package com.fiap.ralfmed.productamazonservice.service;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(ProductDTO productDTO);

    Product findById(Long id);

    List<Product> findByGender(String genre);
}
