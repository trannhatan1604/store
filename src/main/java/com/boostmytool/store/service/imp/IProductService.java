package com.boostmytool.store.service.imp;

import java.util.Date;
import java.util.List;

import com.boostmytool.store.dto.ProductDTO;
import com.boostmytool.store.entity.ProductEntity;

public interface IProductService {

	List<ProductEntity> listOfProduct();
	ProductEntity findById(Integer Id);
	void saveProduct(ProductDTO dto,String image, Date date);
	void deleteProduct(ProductEntity entity);
}
