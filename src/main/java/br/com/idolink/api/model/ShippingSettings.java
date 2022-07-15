package br.com.idolink.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_settings")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE shipping_settings SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class ShippingSettings extends BaseModel<ShippingSettings>{

	private static final long serialVersionUID = -6302291511699175464L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "type_shipping")
	private String typeShipping;
			
	@Column(name = "devolution")
	private String devolution;
	
	@Column(name = "free_shipping")
	private Boolean freeShipping;

	@Column(name = "minimum_value")
	private BigDecimal minimumValue;

	@Column(name = "decripiton")
	private String decripiton;
	
	@Column(name = "display_warning")
	private Boolean displayWarning;

	@Column(name = "custom_text")
	private String customText;
	
	@OneToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id")
	private Shop shop;
	
	@OneToOne(mappedBy = "shippingSettings", fetch = FetchType.LAZY)
	private OwnShipping ownShipping;
	
	@OneToOne(mappedBy = "shippingSettings", fetch = FetchType.LAZY)
	private PostOfficeShipping postOfficeShipping;
		
	@OneToMany(mappedBy = "shippingSettings", fetch = FetchType.LAZY)
	private List<LocalPickup> locaPickups;
	
	@OneToOne(mappedBy = "shippingSettings", fetch = FetchType.EAGER)
	private DigitalDelivery digitalDelivery;
	
	@Override
	public String toString() {
		return "ShippingSettings [id=" + id + ", typeShipping=" + typeShipping + ", devolution=" + devolution + "]";
	}
			
}
