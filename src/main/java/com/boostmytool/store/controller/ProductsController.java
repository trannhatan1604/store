package com.boostmytool.store.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boostmytool.store.converter.ProductConverter;
import com.boostmytool.store.dto.ProductDTO;
import com.boostmytool.store.entity.CategoryEntity;
import com.boostmytool.store.entity.ProductEntity;
import com.boostmytool.store.service.imp.IProductService;
import com.boostmytool.store.service.imp.IcategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private IProductService proService;

	@Autowired
	private IcategoryService cateService;
	
	@Autowired
	private ProductConverter proConverter;

	@GetMapping({ "", "/" })
	public String showProductList(Model model) {
		List<ProductEntity> product = proService.listOfProduct();
		model.addAttribute("products", product);
		return "products/index";
	}

	// Khác với Spring mvc trả về ModelAndView thì SpringBoot trả về String để
	// restful API thôi

	// Get đẩy lên form
	@GetMapping({ "/create" })
	public String showCreateProduct(Model model, @RequestParam(name = "id",required = false) Integer id) {
		ProductDTO dto = new ProductDTO();
		if(id!=null)
		{
			dto = proConverter.entityToDto(proService.findById(id));
		}
		model.addAttribute("productDTO", dto);
		return "products/create";
	}

	// Post nhận về sử lý khi onClick button
	// vẫn trả về object th:object nên dùng ModelAttribute
	@PostMapping("/create")
	public String createProduct(@Valid @ModelAttribute ProductDTO dto, BindingResult result) {
		System.out.println("Category Image: " + dto.getImagePath());
		System.out.println(result.hasErrors());
		
		if (result.hasErrors() || dto.getImageFileName().isEmpty()) {
			if (dto.getImageFileName().isEmpty())
				result.rejectValue("imageFileName", "notEmpty", "The image file is empty");

			return "products/create";
		}
		//Save img
		MultipartFile image = dto.getImageFileName();
		Date createAt = new Date();
		String storageFileName = createAt.getTime() + "_"+image.getOriginalFilename();
		//try-catch để tạo path
		try {
			//đầu chứa file
			String uploadDir = "public/images/";
			//đường dẫn nơi chứa file
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try(InputStream inputStream = image.getInputStream()){
				//lưu trữ ảnh tại đây
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
							StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());		}
		
		
		proService.saveProduct(dto,storageFileName,createAt);
		return "redirect:/products";
	}

	//delete
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam(name = "id") Integer id)
	{
		//Xoá ảnh trong folder chứa ảnh
		try {
			ProductEntity entity = proService.findById(id);
			
			//dẫn đến kho chưa file
			String uploadDir = "public/images/";
			//lấy ra ảnh
			Path imagePath = Paths.get(uploadDir + entity.getImageFileName());
			try{
				//xoá ảnh
				Files.delete(imagePath);
			}catch (Exception e) {
				// TODO: handle exception
			}
			//xoá product
			proService.deleteProduct(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/products";
	}
	
	@ModelAttribute("categories")
	public List<CategoryEntity> categories() {
		return cateService.listOfCategory();
	}
}
