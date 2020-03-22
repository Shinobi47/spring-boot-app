package com.benayed.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.benayed.app.dto.ProductDto;
import com.benayed.app.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	

	public List<ProductDto> getAllNotHiddenProducts(){
	
		log.info("Fetching products and filtering hidden products ...");
		List<ProductDto> products = productRepository.findAll().stream().filter(this::isNotHidden).collect(Collectors.toList());
		log.info("End of fetching, products found : {}", products.size());
		return products;
	}


	private boolean isNotHidden(ProductDto p) {
		return !p.isHidden();
	}
	
}
