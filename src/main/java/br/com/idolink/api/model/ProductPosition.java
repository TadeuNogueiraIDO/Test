package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_position")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ProductPosition implements Serializable{
	
	private static final long serialVersionUID = 7991237673877932376L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name="position")
	private Long position;
	
	@ManyToOne
	@JoinColumn(name = "shop_product_id", referencedColumnName = "id")
	private ShopProduct shopProduct;
		
	@ManyToOne
	@JoinColumn(name = "shop_category_id", referencedColumnName = "id")
	private ShopCategory shopCategory;

}
