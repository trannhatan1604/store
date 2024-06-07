package com.boostmytool.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boostmytool.store.converter.ProductConverter;
import com.boostmytool.store.dto.ProductDTO;
import com.boostmytool.store.entity.ProductEntity;
import com.boostmytool.store.repository.ProductsRepository;
import com.boostmytool.store.service.imp.IProductService;

@Service
public class ProductService implements IProductService{

	@Autowired
	private ProductsRepository proRepository;
	@Autowired
	private ProductConverter proConverter;
	
	@Override
	public List<ProductEntity> listOfProduct() {
		// TODO Auto-generated method stub
		return proRepository.findAll();
	}

	@Override
	public void saveProduct(ProductDTO dto, String image, Date date) {
		ProductEntity entity = new ProductEntity();
		System.out.println(dto.getId());
		if(dto.getId()!=null) {
			entity = proRepository.findById(dto.getId()).get();
			
		}
		//tìm nếu đã có sẵn entity đó thì cập nhật lại dto sang cho entity
		if(entity!=null) {
			entity = proConverter.dtoToEntityOld(entity, dto, image, date);
		}
		else {
			entity = proConverter.dtoToEntityNew(dto, image, date);
		}
		proRepository.save(entity);
	}

	@Override
	public ProductEntity findById(Integer Id) {
		// TODO Auto-generated method stub
		return proRepository.findById(Id).orElseThrow();
	}

	@Override
	public void deleteProduct(ProductEntity entity) {
		proRepository.delete(entity);
		
	}

}
