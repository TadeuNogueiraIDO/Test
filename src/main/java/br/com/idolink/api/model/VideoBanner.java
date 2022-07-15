package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "video_banner")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE video_banner SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class VideoBanner extends BaseModel<VideoBanner>{

	private static final long serialVersionUID = -1148898807803633728L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "thumbnail")
	private String thumbnail;	
	
	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
}
