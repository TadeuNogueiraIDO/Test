package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wallpaper_color_gradient_step")
@AllArgsConstructor
@RequiredArgsConstructor
public class WallpaperGradientStep {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "color")
	private String color;

}
