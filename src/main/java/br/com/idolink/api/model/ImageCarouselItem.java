package br.com.idolink.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.idolink.api.model.enums.ImageBannerAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image_carousel_item")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ImageCarouselItem {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "image_id")
	private Long imageId;
	
	@Column(name = "action")
	private ImageBannerAction action;
	
	@Column(name = "action_field")
	private String actionField;
	
	@Column(name = "dial_code")
	private String dialCode;
	
	@Column(name = "add_detail")
	private Boolean addDetail;
		
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "image_carousel_item_detail_id", referencedColumnName = "id", nullable = false)
	private ImageCarouselItemDetail detail;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "image_carousel_id", referencedColumnName = "id",nullable = false, insertable = true, updatable = false)
	private ImageCarousel imageCarousel;

	
}
