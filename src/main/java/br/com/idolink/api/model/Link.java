package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "link")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE link SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Link extends BaseModel<Link> {

	private static final long serialVersionUID = 6837637313561038023L;

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

	@Column(name = "subtitle")
	private String subtitle;

	@Column(name = "icon")
	private Long icon;

	@Column(name = "url")
	private String url;
	
	@Column(name = "typeicon")
	private Typeicon typeicon;

	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "link_button_animation", joinColumns = {
	@JoinColumn(name = "link_id", referencedColumnName = "id") }, inverseJoinColumns = {
	@JoinColumn(name = "button_animation_id", referencedColumnName = "id") })
	private List<ButtonAnimation> buttonsAnimation;

}
