package com.prueba.ingeneo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.ingeneo.entity.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

}
