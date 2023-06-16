package com.commerce.product.orm;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String pname;
	private Double price;
	private Double discount;
	private String discountDescription;
	private String currency;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category")
	private Category category;
	
	private String imageURL;
	private Double rating;
	
	@CreatedDate
	private long createDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedDate
	private long lastModifiedDate;
	@LastModifiedBy
	private String modifiedBy;

}
