package br.com.idolink.api.model;

import java.math.BigDecimal;

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
import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tool_plan_price")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE tool_plan_price SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class ToolPlanPrice extends BaseModel<ToolPlanPrice>{
	
	private static final long serialVersionUID = 2165924158182594053L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "plan_subscription")
	@Enumerated(EnumType.STRING)
	private PlanSubscriptionTool planSubscription;
	
	@Column
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "tool_plan_package_id", referencedColumnName = "id")
	private ToolPlanPackage toolPlanPackage;
}
