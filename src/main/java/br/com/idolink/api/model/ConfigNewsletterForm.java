package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "config_newsletter_form")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE config_newsletter_form SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ConfigNewsletterForm extends BaseModel<ConfigNewsletterForm> {

	private static final long serialVersionUID = -2461932327926419369L;

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
	
	@Column(name = "datauser")
	private String datauser;
	
	@Column(name = "icon_file_id")
	private Long icon;
	
	@Column(name = "typeicon")
	private Typeicon typeicon;
	
	@Column(name = "animated")
	private Boolean animated;

	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@OneToMany(mappedBy = "configNewsletterForm", fetch = FetchType.LAZY)
	private List<NewsletterForm> newsletterForms;


}

