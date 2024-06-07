package com.boostmytool.store.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boostmytool.store.dto.ProductDTO;
import com.boostmytool.store.entity.ProductEntity;
import com.boostmytool.store.service.imp.IcategoryService;

@Component
public class ProductConverter {

	@Autowired IcategoryService cateService;

	public ProductEntity dtoToEntityNew(ProductDTO dto, String image, Date createAt) {
        ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setCategory(cateService.findById(dto.getCategoryId()));
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setCreateAt(createAt);
        entity.setImageFileName(image);
        return entity;
    }
	public ProductEntity dtoToEntityOld(ProductEntity entity,ProductDTO dto, String image, Date createAt) {
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setCategory(cateService.findById(dto.getCategoryId()));
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setCreateAt(createAt);
        entity.setImageFileName(image);
        return entity;
    }

    public ProductDTO entityToDto(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setName(entity.getName());
        dto.setBrand(entity.getBrand());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImagePath(entity.getImageFileName());
        return dto;
    }
	
}
