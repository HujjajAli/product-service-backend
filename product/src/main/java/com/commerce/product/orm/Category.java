package com.commerce.product.orm;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	private String name;
	private String brand;
	
	@CreatedDate
	private long createDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedDate
	private long lastModifiedDate;
	@LastModifiedBy
	private String modifiedBy;

}
