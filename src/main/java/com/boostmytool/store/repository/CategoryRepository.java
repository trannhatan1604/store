package com.boostmytool.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boostmytool.store.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{

}
