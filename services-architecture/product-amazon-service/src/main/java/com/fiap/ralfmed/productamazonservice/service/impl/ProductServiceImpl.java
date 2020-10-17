package com.fiap.ralfmed.productamazonservice.service.impl;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;
import com.fiap.ralfmed.productamazonservice.repository.ProductRepository;
import com.fiap.ralfmed.productamazonservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        return productRepository.save(Product.convertToProduct(productDTO));
    }

    @Override
    public Product findById(Long id) {
        return (Product) productRepository.findById(id).get();
    }

    @Override
    public List<Product> findByGender(String genre) {
        return productRepository.findByGenre(genre);
    }
}
