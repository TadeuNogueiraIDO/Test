package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlanSubscriptionTool {
	MONTHLY(1L),
	YEARLY(2L);
	
	private Long id;
}
