package com.benayed.app.dto;

import java.math.BigDecimal;

import com.benayed.app.enums.ProductType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

	private Long id;
	private String name;
	private ProductType type;
	private BigDecimal price;
	private boolean isHidden;
}
