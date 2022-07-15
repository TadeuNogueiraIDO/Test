package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.model.enums.TypeTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pre_def_model")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE pre_def_model SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class PredefinedModel  extends BaseModel<PredefinedModel> {
	
	private static final long serialVersionUID = 6837637313561038023L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	@Enumerated(EnumType.STRING)			
	private PredefinedModelsTemplate name;
	
	@Column(name = "file_id")
	private Long image;
	
	@Column(name = "file_uuid")
	private String fileUiid;
	
	@Column(name = "showcase")
	private Boolean showcase;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TypeTemplate type;
	
	@OneToOne(mappedBy = "predefinedModel", fetch = FetchType.EAGER)
	private PredefinedModelParam predefModelParam;
	
	@OneToOne
	@JoinColumn(name = "appearance_button_id", referencedColumnName = "id")
	private AppearanceButton appearanceButton;
	
	@OneToOne
	@JoinColumn(name = "appearance_cards_id", referencedColumnName = "id")
	private AppearanceCards appearanceCards;
	
	@OneToOne
	@JoinColumn(name = "appearance_text_id", referencedColumnName = "id")
	private AppearanceText appearanceText;
	
	@Column(name = "classification")
	private Long classification;
	
}
