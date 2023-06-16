package com.commerce.product.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	private Long cid;
	private String name;
	private String brand;
	
	private long createDate;
	private String createdBy;
	private long lastModifiedDate;
	private String modifiedBy;
}
