package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "config_faq")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE config_faq SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ConfigFaq extends BaseModel<ConfigFaq>  {

	
	private static final long serialVersionUID = 2685956452176030032L;

	@Id
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
	
	@Column(name = "icon_id")
	private Long iconId;
		
	@Column(name = "type_icon")
	@Enumerated(EnumType.STRING)
	private Typeicon typeIcon;
	
	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;

	@OneToMany(mappedBy = "configFaq", fetch = FetchType.LAZY)
	private List<Faq> faqs;
	
	
}
