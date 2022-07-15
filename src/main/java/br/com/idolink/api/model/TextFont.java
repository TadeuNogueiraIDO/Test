package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "text_font")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TextFont implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3966644221635469618L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Column(name = "style")
	private String style;
	
	@Column(name = "size")
	private Double size;
	
	@Column(name = "align")
	private String align;
	
	@Column(name = "line_height")
	private Double lineHeight;
	
	@Column(name = "font_family_name")
	private String fontFamily;

}
