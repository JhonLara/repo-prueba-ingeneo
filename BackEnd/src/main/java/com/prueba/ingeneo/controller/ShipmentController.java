package com.prueba.ingeneo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.ingeneo.entity.Product;
import com.prueba.ingeneo.entity.Shipment;
import com.prueba.ingeneo.entity.Storage;
import com.prueba.ingeneo.service.ShipmentService;

@RestController
@RequestMapping(value = "/api/v1/auth/shipments")
public class ShipmentController {

	@Autowired
	private ShipmentService shipmentService;

	@GetMapping()
	public List<Shipment> getShipmentList() {
		return shipmentService.getShipmentList();
	}

	@GetMapping("/{shipmentId}")
	public ResponseEntity<Shipment> getShipment(@PathVariable long shipmentId) {
		Optional<Shipment> shipmentFound = shipmentService.findByShipmentId(shipmentId);
		return shipmentFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping()
	public Long createShipment(@RequestParam("shipment") String shipment)
			throws JsonMappingException, JsonProcessingException {
		Shipment shipmentNew = new ObjectMapper().readValue(shipment, Shipment.class);
		return shipmentService.createShipment(shipmentNew);
	}

	@DeleteMapping("/{shipmentId}")
	public void deleteShipment(@PathVariable Long shipmentId) {
		shipmentService.deleteShipment(shipmentId);
	}

	@GetMapping("/storage")
	public List<Storage> getStorageList() {
		return shipmentService.getStorageList();
	}

	@GetMapping("/storage/{storageId}")
	public ResponseEntity<Storage> getStorage(@PathVariable long storageId) {
		Optional<Storage> shipmentFound = shipmentService.findByStorageId(storageId);
		return shipmentFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/storage")
	public Long createStorage(@RequestParam("storage") String storage)
			throws JsonMappingException, JsonProcessingException {
		Storage storageNew = new ObjectMapper().readValue(storage, Storage.class);
		return shipmentService.createStorage(storageNew);
	}

	@DeleteMapping("/storage/{storageId}")
	public void deleteStorage(@PathVariable Long storageId) {
		shipmentService.deleteStorage(storageId);
	}

	@GetMapping("/product")
	public List<Product> getProductList() {
		return shipmentService.getProductList();
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable long productId) {
		Optional<Product> productFound = shipmentService.findByProductId(productId);
		return productFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/product")
	public Long createProduct(@RequestParam("product") String product)
			throws JsonMappingException, JsonProcessingException {
		Product productNew = new ObjectMapper().readValue(product, Product.class);
		return shipmentService.createProduct(productNew);
	}

	@DeleteMapping("/product/{productId}")
	public void deleteProduct(@PathVariable Long productId) {
		shipmentService.deleteProduct(productId);
	}

}
