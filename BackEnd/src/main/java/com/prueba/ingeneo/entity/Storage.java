package com.prueba.ingeneo.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "storage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Storage {

	@Id
	@Column(name = "storage_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
	private Long storageId;

	@Column(name = "storage_name")
	private String storageName;

	@Column(name = "storage_type")
	private String storageType;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate createDate;

	@OneToMany(mappedBy = "storage")
	private Set<Shipment> shipments;

}
