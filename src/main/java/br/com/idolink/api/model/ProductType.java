package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.idolink.api.model.enums.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "product_type")
public class ProductType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4723206453627366859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "file_uiid")
	private String icon;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TypeProduct type;
	
	@Column(name = "description")
	private String description;
}
