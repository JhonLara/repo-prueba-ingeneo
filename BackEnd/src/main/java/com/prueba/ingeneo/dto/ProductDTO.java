package com.prueba.ingeneo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

	private Long productId;
	private String productName;
	private String productType;
	private Long amount;

}
