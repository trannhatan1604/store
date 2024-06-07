package com.boostmytool.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boostmytool.store.dto.CategoryDTO;
import com.boostmytool.store.entity.CategoryEntity;
import com.boostmytool.store.repository.CategoryRepository;
import com.boostmytool.store.service.imp.IcategoryService;

@Service
public class CategoryService implements IcategoryService{

	@Autowired
	private CategoryRepository cateRepository;
	
	@Override
	public List<CategoryEntity> listOfCategory() {
		// TODO Auto-generated method stub
		return cateRepository.findAll();
	}

	@Override
	public CategoryEntity findById(Integer Id) {
		
		CategoryEntity entity = cateRepository.findById(Id).orElseThrow(() -> new RuntimeException("Category not found with id "));
		return entity;
	}

}
