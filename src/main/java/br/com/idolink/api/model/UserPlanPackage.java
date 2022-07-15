package br.com.idolink.api.model;

import java.time.LocalDate;

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
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.PlanSubscriptionTool;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_plan_package")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@Builder
@SQLDelete(sql = "UPDATE user_plan_package SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class UserPlanPackage extends BaseModel<UserPlanPackage>{
	
	private static final long serialVersionUID = -8292101103940434895L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	
	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PlanPackagePaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tool_type")
	private ToolPlanPackageType toolType;
	
	@Column(name = "plan_subscription")
	@Enumerated(EnumType.STRING)
	private PlanSubscriptionTool planSubscription;
	
	@ManyToOne
	@JoinColumn(name = "tool_plan_package_id", referencedColumnName = "id")
	private ToolPlanPackage toolPlanPackage;
	
	
}
