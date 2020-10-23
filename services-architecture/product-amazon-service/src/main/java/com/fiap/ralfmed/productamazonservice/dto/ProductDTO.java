package com.fiap.ralfmed.productamazonservice.dto;

import com.fiap.ralfmed.productamazonservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    public String name;

    public String category;

    public String genre;

    public Float price;

    public String description;

    public static ProductDTO convertProductDto(Product product){
        ProductDTO productDTO = new ProductDTO(product.getName(), product.getCategory(), product.getGenre(), product.getPrice(), product.getDescription());
        return productDTO;
    }
}
