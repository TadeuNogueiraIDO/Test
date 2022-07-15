package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.idolink.api.model.enums.Fee;
import br.com.idolink.api.model.enums.InvoiceType;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.SubscriptionType;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(value = Include.NON_NULL)
public class InvoiceItem {
	
	
	private Long id;
	
	private String nameIdo;
	
	private InvoiceType type;	
	
	private SubscriptionType subcriptionType;
	
	private Fee fee;
	
	private BigDecimal price;
	
	private String order;
	
	private String name;
	
	private ToolPlanPackageType typePackage;
	
	private PlanPackagePaymentStatus paymentStatus;
	
	private String domainIdo;
	
}
