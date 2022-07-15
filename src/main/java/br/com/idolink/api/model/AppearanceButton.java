package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@Table(name = "appearance_button")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE appearance_button SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
@Builder
public class AppearanceButton extends BaseModel<AppearanceButton> {

	private static final long serialVersionUID = -3851906196398039484L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Ido ido;

	@OneToOne
	@JoinColumn(name = "button_shape_id", referencedColumnName = "id")
	private ButtonShape buttonShape;

	@Column(name="button_color")
	private String buttonColor;

	@Column(name="border_color")
	private String borderColor;

	@OneToOne
	@JoinColumn(name = "text_font_id", referencedColumnName = "id")
	private TextFont textFont;

	@Column(name="font_color")
	private String fontColor;

}
