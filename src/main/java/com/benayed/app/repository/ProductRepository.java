package com.benayed.app.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.benayed.app.dto.ProductDto;
import com.benayed.app.enums.ProductType;

@Repository
public class ProductRepository {

	public List<ProductDto> findAll(){
		
		ProductDto p1 = ProductDto.builder().id(1L).name("Bread").price(BigDecimal.ONE).type(ProductType.EDIBLE).isHidden(false).build();
		ProductDto p2 = ProductDto.builder().id(2L).name("Hoodie").price(BigDecimal.valueOf(19L)).type(ProductType.CLOTHES).isHidden(false).build();
		ProductDto p3 = ProductDto.builder().id(3L).name("Headset").price(BigDecimal.valueOf(20L)).type(ProductType.ELECTRONIC).isHidden(false).build();
		ProductDto p4 = ProductDto.builder().id(3L).name("Unknown").price(BigDecimal.valueOf(20L)).type(ProductType.ELECTRONIC).isHidden(true).build();
		
		return Arrays.asList(p1,p2,p3,p4);
	}

}
