package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Builder
@AllArgsConstructor
@Table(name = "shop_product")
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE shop_product SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ShopProduct extends BaseModel<ShopProduct> {

	private static final long serialVersionUID = -7945610540297238586L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "shop_category_id", referencedColumnName = "id")
	private ShopCategory shopCategory;
		
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "main_image_file_id")
	private Long MainImage;
	
	@Column(name = "additional_images_file_id")
	private String additionalImages;
		
	@Column(name = "is_vitrine_show")
	private Boolean isVitrineShow;
		
	@OneToOne
	@JoinColumn(name = "product_type_id", referencedColumnName = "id")
	private ProductType typeProduct;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "shopProduct", fetch = FetchType.LAZY)
	private List<ShopProductVariation> variations;
	
	@Column(name = "has_variation")
	private Boolean hasVariation;
	
	@Column(name = "enable_disable")
	@Builder.Default
    @ColumnDefault("true")
	private Boolean enableDisable = true;
	


		
}
