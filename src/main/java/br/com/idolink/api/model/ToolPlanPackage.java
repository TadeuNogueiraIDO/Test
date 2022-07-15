package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tool_plan_package")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE tool_plan_package SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ToolPlanPackage extends BaseModel<ToolPlanPackage>{
	
	private static final long serialVersionUID = -3436878955290301387L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tool_type")
	private ToolPlanPackageType toolType;
	
	@Column
	private Boolean active;

	@OneToMany(mappedBy = "toolPlanPackage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ToolPlanPrice> toolPlanPrice; 
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tool_plan_tool_package", joinColumns = {@JoinColumn(name = "tool_plan_package_id", referencedColumnName = "id")},
				inverseJoinColumns = @JoinColumn(name = "tool_package_id", referencedColumnName = "id"))
	private List<ToolConfigPackage> toolPackage;
	
	@OneToMany(mappedBy = "toolPlanPackage")
	private List<UserPlanPackage> userPlanPackages; 
	
}
