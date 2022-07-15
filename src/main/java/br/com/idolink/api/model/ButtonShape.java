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
@Table(name = "button_shape")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ButtonShape implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779671439237531444L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "border_width")
	private Double borderWidth;
	
	@Column(name = "border_radius_top_left")
	private Double borderRadiusTopLeft;
	
	@Column(name = "border_radius_top_right")
	private Double borderRadiusTopRight;
	
	@Column(name = "border_radius_bottom_left")
	private Double borderRadiusBottomLeft;
	
	@Column(name = "border_radius_bottom_right")
	private Double borderRadiusBottomRight;
	
}