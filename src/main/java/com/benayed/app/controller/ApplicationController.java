
package com.benayed.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benayed.app.dto.ProductDto;
import com.benayed.app.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class ApplicationController {

	private ProductService productService;

	public ApplicationController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<?> getProducts() {
		
		log.info("receiving call on endpoint \"/products\"");
		
		List<ProductDto> notHiddenProducts = productService.getAllNotHiddenProducts();
		
		return notHiddenProducts.isEmpty() ? new ResponseEntity<>("No product Found !", HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(notHiddenProducts, HttpStatus.OK);

	}
}
