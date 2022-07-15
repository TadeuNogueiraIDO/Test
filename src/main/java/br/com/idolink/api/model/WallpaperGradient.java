package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.idolink.api.model.enums.WallpaperAligment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wallpaper_color_gradient")
@AllArgsConstructor
@RequiredArgsConstructor
public class WallpaperGradient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "alignment_start")
	private WallpaperAligment start;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "alignment_end")
	private WallpaperAligment end;
	
	@OneToMany
	@JoinColumn(name = "wallpaper_color_gradient_id", referencedColumnName = "id")
	@OrderBy("id")
	private List<WallpaperGradientStep> steps;
}
