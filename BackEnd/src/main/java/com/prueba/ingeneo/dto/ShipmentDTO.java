package com.prueba.ingeneo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO implements Serializable {

	private Long shipmentId;
	private String number;
	private String vehicle;
	private LocalDate shipmentDate;
	private LocalDate deliveryDate;
	private String state;
	private ProductDTO productName;
	private Long quantity;
	private Long amount;
	private Long amountDesc;
	private StorageDTO storage;

}
