package com.prueba.ingeneo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@Table(name = "shipment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shipment {

	@Id
	@Column(name = "shipment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long shipmentId;

	@Column(name = "number")
	private String number;

	@Column(name = "vehicle")
	private String vehicle;

	@Column(name = "shipment_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate shipmentDate;

	@Column(name = "delivery_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate deliveryDate;

	@Column(name = "state")
	private String state;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "amount")
	private Long amount;

	@Column(name = "amount_desc")
	private Long amountDesc;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "storage_id", referencedColumnName = "storage_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Storage storage;

}
