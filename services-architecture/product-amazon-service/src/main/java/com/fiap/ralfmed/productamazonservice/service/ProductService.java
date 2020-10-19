package com.fiap.ralfmed.productamazonservice.service;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(ProductDTO productDTO);

    ProductDTO findById(Long id);

    List<ProductDTO> findByGender(String genre);

    List<ProductDTO> listProductContainsName(String name);

    List<ProductDTO> findByFavorite(Long numero);
}
