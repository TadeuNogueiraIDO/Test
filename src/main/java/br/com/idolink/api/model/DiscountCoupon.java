package br.com.idolink.api.model;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "discount_coupon")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE discount_coupon SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class DiscountCoupon extends BaseModel<Address> {

	private static final long serialVersionUID = 2492823523843351786L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "coupon_code")
	private String couponCode;
	
	@Column(name = "date_expiration_date")
	private LocalDate datexpirationDate;
	
	@Column(name = "discount_coupon")
	@Enumerated(EnumType.STRING)
	private Discount discountCoupon; 
	
	@Column(name = "minimum_value_condition")
	private Boolean minimumValueCondition; 
	
	@Column(name = "minimum_value")
	private Double minimumValue;
	
	@Column(name = "value_discount")
	private Double valueDiscount;
	
	@Column(name = "active")
	@ColumnDefault("true")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;
	
		
	}


