package br.com.idolink.api.model;

import java.math.BigDecimal;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.ProductFilter;
import br.com.idolink.api.model.enums.UnitOfMeasure;
import br.com.idolink.api.model.enums.UnitOfWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_variation")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE product_variation SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ShopProductVariation extends BaseModel<ShopProductVariation>{
	
	private static final long serialVersionUID = 8489736274493508350L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "promo_price")
	private BigDecimal promoPrice;
	
	@Column(name = "digital_url")
	private String digitalUrl;
			
	@Column(name = "digital_orientation")
	private String digitalOrientation;
	
	@Column(name = "control_stock")
	private Boolean hasStockControl;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "disable_out_of_stock")
	private Boolean disableOutOfStock;
	
	@ManyToOne
	@JoinColumn(name = "shop_product_id", referencedColumnName = "id")
	public ShopProduct shopProduct;
	
	@Column(name = "unit_of_measure")
	@Enumerated(EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	@Column(name = "length")
	private Double length;
	
	@Column(name = "width")
	private Double width;
	
	@Column(name = "height")
	private Double height;
	
	@Column(name = "unit_of_weight")
	@Enumerated(EnumType.STRING)
	private UnitOfWeight UnitOfWeight;
	
	@Column(name = "weight")
	private Double weight;

	@Column(name = "filter_type")
	@Enumerated(EnumType.STRING)
	public ProductFilter filterType;
	
	@Column(name = "filter_value")
	private String filterValue;
	
	@Column(name="sku")
	private String sku;
			
}
