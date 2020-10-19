package com.fiap.ralfmed.productamazonservice.controller;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;
import com.fiap.ralfmed.productamazonservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findById(@RequestParam Long id){
        return productService.findById(id);
    }

    @GetMapping("/findByGenre")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findByGenre(@RequestParam String genre){
        return productService.findByGender(genre);
    }

    @GetMapping("/findByKeyWord")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findByKeyWord(@RequestParam String keyword){
        return productService.listProductContainsName(keyword);
    }

    @GetMapping("/findByFavorite")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findByFavorite(@RequestParam Long number){
        return productService.findByFavorite(number);
    }
}
