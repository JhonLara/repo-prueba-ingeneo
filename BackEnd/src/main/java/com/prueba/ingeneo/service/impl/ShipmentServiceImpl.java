package com.prueba.ingeneo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.ingeneo.entity.Product;
import com.prueba.ingeneo.entity.Shipment;
import com.prueba.ingeneo.entity.Storage;
import com.prueba.ingeneo.repository.ProductRepository;
import com.prueba.ingeneo.repository.ShipmentRepository;
import com.prueba.ingeneo.repository.StorageRepository;
import com.prueba.ingeneo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService {

	@Autowired
	private ShipmentRepository shipmentRepository;

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateShipment(Shipment shipment) {
		Optional<Shipment> shipmentFound = shipmentRepository.findById(shipment.getShipmentId());
		if (shipmentFound.isPresent()) {
			shipmentRepository.save(shipment);
		}
	}

	@Override
	public Long createShipment(Shipment shipment) {
		return shipmentRepository.save(shipment).getShipmentId();
	}

	@Override
	public void deleteShipment(Long shipmentId) {
		Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
		shipment.ifPresent(shipmentRepository::delete);
	}

	@Override
	public List<Shipment> getShipmentList() {
		return shipmentRepository.findAll();
	}

	@Override
	public Optional<Shipment> findByShipmentId(Long shipmentId) {
		return shipmentRepository.findById(shipmentId);
	}

	@Override
	public void updateStorage(Storage storage) {
		Optional<Storage> storageFound = storageRepository.findById(storage.getStorageId());
		if (storageFound.isPresent()) {
			storageRepository.save(storage);
		}
	}

	@Override
	public Long createStorage(Storage storage) {
		storage.setCreateDate(LocalDate.now());
		return storageRepository.save(storage).getStorageId();
	}

	@Override
	public void deleteStorage(Long storageId) {
		Optional<Storage> storage = storageRepository.findById(storageId);
		storage.ifPresent(storageRepository::delete);
	}

	@Override
	public List<Storage> getStorageList() {
		return storageRepository.findAll();
	}

	@Override
	public Optional<Storage> findByStorageId(Long storageId) {
		return storageRepository.findById(storageId);
	}

	@Override
	public void updateProduct(Product product) {
		Optional<Product> productFound = productRepository.findById(product.getProductId());
		if (productFound.isPresent()) {
			productRepository.save(product);
		}
	}

	@Override
	public Long createProduct(Product product) {
		return productRepository.save(product).getProductId();
	}

	@Override
	public void deleteProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		product.ifPresent(productRepository::delete);
	}

	@Override
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findByProductId(Long productId) {
		return productRepository.findById(productId);
	}

}
