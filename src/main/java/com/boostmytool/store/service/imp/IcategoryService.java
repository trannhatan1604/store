package com.boostmytool.store.service.imp;

import java.util.List;

import com.boostmytool.store.entity.CategoryEntity;

public interface IcategoryService {

	List<CategoryEntity> listOfCategory();
	CategoryEntity findById(Integer Id);
}
