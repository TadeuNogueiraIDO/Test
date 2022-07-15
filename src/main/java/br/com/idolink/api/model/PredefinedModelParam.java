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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pre_def_model_param")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE pre_def_model_param SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class PredefinedModelParam extends BaseModel<PredefinedModelParam> {
	
	private static final long serialVersionUID = -6654449536547893965L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pre_def_model_id", referencedColumnName = "id")
	private PredefinedModel predefinedModel;
	
	@OneToOne
	@JoinColumn(name = "model_param_id", referencedColumnName = "id")
	private ModelParam modelParam;
	
	@Column(name = "value")
	private String value;

}
