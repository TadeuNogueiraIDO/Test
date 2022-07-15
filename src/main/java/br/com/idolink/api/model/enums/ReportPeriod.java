package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportPeriod {
	MONTH(1),
	YEAR(2),
	WEEK(3);
	private Integer id;
	
}
