package com.fiap.ralfmed.orderamazonservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@JsonProperty("id")
	private Integer id;

	private double price;

	private String description;

	private int quantity;

	public Integer getId() {
		return id;
	}

}

