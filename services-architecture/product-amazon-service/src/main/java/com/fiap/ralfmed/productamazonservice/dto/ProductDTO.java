package com.fiap.ralfmed.productamazonservice.dto;

import com.fiap.ralfmed.productamazonservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;

    private String category;

    private String genre;

    public static ProductDTO convertProductDto(Product product){
        ProductDTO productDTO = new ProductDTO(product.getName(), product.getCategory(), product.getGenre());
        return productDTO;
    }
}
