package com.fiap.ralfmed.productamazonservice.service.impl;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;
import com.fiap.ralfmed.productamazonservice.repository.ProductRepository;
import com.fiap.ralfmed.productamazonservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ProductDTO findById(Long id) {
        Product product = (Product) productRepository.findById(id).get();
        product.setFavorite(product.getFavorite()+1L);
        productRepository.save(product);
        return ProductDTO.convertProductDto(product);
    }

    @Override
    public List<ProductDTO> findByGender(String genre) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productRepository.findByGenre(genre)){
            returnListProductDto(product, productDTOList);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> listProductContainsName(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productRepository.findByNameContains(name)){
            returnListProdutDto(product, productDTOList);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByFavorite(Long number) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productRepository.findByFavoriteGreaterThan(number)){
            returnListProductDto(product, productDTOList);
        }
        return productDTOList;
    }

    private List<ProductDTO> returnListProductDto(Product product, List<ProductDTO> productDTOList){
        ProductDTO productDTO = new ProductDTO(product.getName(), product.getCategory(), product.getGenre(), product.getPrice(), product.getDescription());
        productDTOList.add(productDTO);
        return productDTOList;
    }
}
