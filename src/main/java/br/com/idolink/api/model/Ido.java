package br.com.idolink.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.IdoStatus;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.model.enums.WallpaperType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ido")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE ido SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Ido extends BaseModel<Ido> {

	private static final long serialVersionUID = 6837637313561038023L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	private Country country;

	@Column(name = "domain")
	private String domain;

	@Column(name = "description")
	private String description;

	@Column(name = "position")
	private String position;

	@Column(name = "name_microservice")
	private String nameMicroservice;

	@Column(name = "status")
	private IdoStatus status;

	@Column(name = "currency")
	private String currency;

	@ManyToOne
	@JoinColumn(name = "business_id", referencedColumnName = "id")
	private Business business;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ido_category", joinColumns = {
			@JoinColumn(name = "ido_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "category_id", referencedColumnName = "id") })
	private List<Category> categories;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ido")
	private List<IdoTool> idoTools;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<IdoContact> contacts;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<IdoModelParam> idoModelParams;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<Link> links;

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private ConfigFaq configfaq;

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private AppearanceButton appearanceButton;

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private AppearanceCards appearanceCards;

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private AppearanceText appearanceText;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<ImageBanner> imageBanner;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<VideoBanner> videoBanner;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<AttachedPdf> attachedPdfs;

	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private ConfigNewsletterForm configNewsletterForm;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<ImageCarousel> imageCarousels;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private List<EmbedHtml> embedHtmls;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private LogoBio logoBio;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private Shop shop;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private MenuFooter menuFooter;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "ido", fetch = FetchType.LAZY)
	private BusinessHour businessHour;

	@Column(name = "wallpaper_type")
	@Enumerated(EnumType.ORDINAL)
	private WallpaperType wallpaperType;

	@Column(name = "wallpaper")
	private String wallpaper;

	@Column(name = "number_visitors")
	private Integer numberVisitors;

	@Column(name = "icon_file_id")
	private Long icon;

	@Column(name = "template")
	@Enumerated(EnumType.STRING)
	private PredefinedModelsTemplate template;

	@Column(name = "ido_published")
	private Long idoPublished;

	@Column(name = "hide_Idolink")
	private Boolean hideIdolink;

	@Column(name = "sensitive_content")
	private Boolean sensitiveContent;

	@Column(name = "sharing_thumb")
	private String sharingThumb;

	@Column(name = "update_last_domain")
	private LocalDateTime updateLastDomain;

	@ManyToOne
	@JoinColumn(name = "timezone_id", referencedColumnName = "id")
	private Timezone timezone;

	@Override
	public String toString() {
		return "Ido [id=" + id + ", name=" + name + ", domain=" + domain + ", description=" + description + ", status="
				+ status + "]";
	}

}
