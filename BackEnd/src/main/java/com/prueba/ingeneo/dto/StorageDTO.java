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
public class StorageDTO implements Serializable {

	private Long storageId;
	private String storageName;
	private String storageType;
	private LocalDate createDate;

}
