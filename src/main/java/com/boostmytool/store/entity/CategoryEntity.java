package com.boostmytool.store.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")
public class CategoryEntity extends BaseEntity{
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductEntity> entities;

	@Column(name="category_name")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
