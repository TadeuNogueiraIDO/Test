package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "appearance_text")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE appearance_text SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class AppearanceText extends BaseModel<AppearanceText> {

	private static final long serialVersionUID = -3851906196398039484L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Ido ido;

	@Column(name="background_color")
	public String backgroundColor;

	@OneToOne
	@JoinColumn(name = "text_font_id", referencedColumnName = "id")
	public TextFont textFont;

	@Column(name="font_color")
	public String fontColor;

}
