package com.boostmytool.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boostmytool.store.entity.ProductEntity;

public interface ProductsRepository extends JpaRepository<ProductEntity, Integer>{

}
