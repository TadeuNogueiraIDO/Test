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
@Table(name = "newsletter_form")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE newsletter_form SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class NewsletterForm extends BaseModel<NewsletterForm> {

	private static final long serialVersionUID = -3446134421066534081L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusLeads statusLeads;
	
	@ManyToOne
	@JoinColumn(name = "config_newsletter_form_id", referencedColumnName = "id")
	private ConfigNewsletterForm configNewsletterForm;

}
