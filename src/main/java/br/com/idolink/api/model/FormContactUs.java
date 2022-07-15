package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.StatusLeads;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_us")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE contact_us SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class FormContactUs extends BaseModel<FormContactUs> {

	private static final long serialVersionUID = -280530739862396736L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "title")
	private String title;
	
	@Column(name = "text_box")
	private String textBox;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusLeads statusLeads;

	@ManyToOne
	@JoinColumn(name = "config_contact_us_id", referencedColumnName = "id")
	private ConfigContactUs configContactUs;

}
