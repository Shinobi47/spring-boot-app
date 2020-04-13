
package com.benayed.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.benayed.app.dto.ProductDto;
import com.benayed.app.entity.UserProfileEntity;
import com.benayed.app.repository.UserProfileRepository;
import com.benayed.app.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class ApplicationController {

	private ProductService productService;
	
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	public ApplicationController(ProductService productService, UserProfileRepository userProfileRepository) {
		this.productService = productService;
		this.userProfileRepository = userProfileRepository;
		
	}
	
	@GetMapping(value = "/products")
	public ResponseEntity<?> getProducts() {
		
		log.info("receiving call on endpoint \"/products\"");
		
		List<ProductDto> notHiddenProducts = productService.getAllNotHiddenProducts();
		
		return notHiddenProducts.isEmpty() ? new ResponseEntity<>("No product Found !", HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(notHiddenProducts, HttpStatus.OK);

	}
	
	@GetMapping(value = "/log-message")
	public String triggerLog(@RequestParam String logLevel) {
		
		if("INFO".equalsIgnoreCase(logLevel)) {
			log.info("An info message as been logged");
		}
		else if ("DEBUG".equalsIgnoreCase(logLevel)) {
			log.debug("A debug message has been logged");
		}
		else if ("WARN".equalsIgnoreCase(logLevel)) {
			log.warn("A warn message has been logged");
		}
		
		else if ("ERROR".equalsIgnoreCase(logLevel)) {
			log.error("An error message has been logged");
		}
		
		return "Triggered log with loglevel : " + logLevel;

	}
	
	@GetMapping(value = "/log-random-number")
	public String randomNumber() {
		
		Integer random = Double.valueOf(Math.random() * 5).intValue();
		
		log.info("Random number generated : " + random);
		
		return "radom number generated and logged : " + random;
	}
	
	@PreAuthorize("hasRole('ROOT')")
	@GetMapping(value = "/users")
	public List<UserProfileEntity> repos() {
		return userProfileRepository.findAll();
	}
	
	@PreAuthorize("hasAuthority('ROLE_ROOT')")
	@GetMapping(value = "/principal")
	public Principal retrievePrincipal(Principal principal) {
		log.info(SecurityContextHolder.getContext().getAuthentication().getName());
		return principal;
	}
	
	@PreAuthorize("isTrue()")
	@GetMapping(value = "/true")
	public String accessible() {
		return "You can access this method";
	}
	
	@PreAuthorize("isFalse()")
	@GetMapping(value = "/false")
	public String inaccessible() {
		return "You cannot access this method, how the hell did you get here ?";

	}
	
}
