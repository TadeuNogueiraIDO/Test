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
@Table(name = "embed_html")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE embed_html SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class EmbedHtml extends BaseModel<EmbedHtml> {

	private static final long serialVersionUID = 3437142282938795035L;

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

	@Column(name = "code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
}
