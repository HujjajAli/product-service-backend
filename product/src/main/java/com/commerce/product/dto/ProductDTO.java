package com.commerce.product.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
	
	private Long pid;
	private String pname;
	private Double price;
	private Double discount;
	private String discountDescription;
	private String currency;
	private CategoryDTO category;
	
	private String imageURL;
	private Double rating;
	private List<OriginDTO> origins;
	
	private long createDate;
	private String createdBy;
	private long lastModifiedDate;
	private String modifiedBy;

}
