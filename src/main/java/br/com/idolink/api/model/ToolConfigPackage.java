package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "tool_package")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE tool_package SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ToolConfigPackage extends BaseModel<ToolConfigPackage>{

	private static final long serialVersionUID = 4375503591812841610L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "tool_id", referencedColumnName = "id")
	private Tool tool;
	
	@Column
	private Integer addition;
	
	private String description;
	
	@Column(name = "resource_limitation")
	private Integer resourceLimitation;
	
	@ManyToMany(mappedBy = "toolPackage", cascade = CascadeType.ALL)
	private List<ToolPlanPackage> packages;
}
