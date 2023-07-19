package com.prueba.ingeneo.service;

import java.util.List;
import java.util.Optional;

import com.prueba.ingeneo.entity.Product;
import com.prueba.ingeneo.entity.Shipment;
import com.prueba.ingeneo.entity.Storage;

public interface ShipmentService {

	void updateShipment(Shipment shipment);

	Long createShipment(Shipment shipment);

	void deleteShipment(Long shipmentId);

	List<Shipment> getShipmentList();

	Optional<Shipment> findByShipmentId(Long shipmentId);

	void updateStorage(Storage storage);

	Long createStorage(Storage storage);

	void deleteStorage(Long storageId);

	List<Storage> getStorageList();

	Optional<Storage> findByStorageId(Long storageId);

	void updateProduct(Product product);

	Long createProduct(Product product);

	void deleteProduct(Long productId);

	List<Product> getProductList();

	Optional<Product> findByProductId(Long productId);
}
