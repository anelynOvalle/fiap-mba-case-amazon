package com.fiap.ralfmed.productamazonservice.controller;

import java.util.List;

import com.fiap.ralfmed.productamazonservice.dto.ProductDTO;
import com.fiap.ralfmed.productamazonservice.entity.Product;
import com.fiap.ralfmed.productamazonservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @HystrixCommand(fallbackMethod  = "singleProductFallback",
            commandProperties=
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds",value="20000")})
    public Product createProduct(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "singleProductFallback",
            commandProperties=
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds",value="20000")})
    public ProductDTO findById(@PathVariable(name = "id") Long id){
        return productService.findById(id);
    }

    @GetMapping("/findByGenre")
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "listFallbackReturn")
    public List<ProductDTO> findByGenre(@RequestParam String genre){
        return productService.findByGender(genre);
    }

    @GetMapping("/findByKeyWord")
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "listFallbackReturn")
    public List<ProductDTO> findByKeyWord(@RequestParam String keyword){
        return productService.listProductContainsName(keyword);
    }

    @GetMapping("/findByFavorite")
    @ResponseStatus(HttpStatus.OK)
    @HystrixCommand(fallbackMethod  = "listFallbackReturn")
    public List<ProductDTO> findByFavorite(@RequestParam Long number){
        return productService.findByFavorite(number);
    }

    public Product singleProductFallback(ProductDTO productDTO) {
        Product product = new Product();
        product.name = "Erro ao cadastrar.";
        product.category = "Inválido";
        product.genre = "Tente novamente mais tarde ou contate o administrador do sistema.";
        return product;
    }

    public List<ProductDTO> listFallbackReturn(String str){
        List<ProductDTO> productDTOList = null;

        ProductDTO productDTO = new ProductDTO();
        productDTO.name = "Erro ao solicitar informação.";
        productDTO.category = "Inválido";
        productDTO.genre = "Tente novamente mais tarde ou contate o administrador do sistema.";

        productDTOList.add(productDTO);
        return productDTOList;
    }
}
