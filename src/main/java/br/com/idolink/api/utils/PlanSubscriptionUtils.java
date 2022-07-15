package br.com.idolink.api.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import br.com.idolink.api.model.enums.PlanSubscriptionTool;

public class PlanSubscriptionUtils {
	
	
	
	
	public static LocalDate generateExpiredDate(PlanSubscriptionTool subscription) {
		LocalDate date = LocalDate.now();
		
		switch (subscription) {
		case MONTHLY:
			date = date.plusYears(1).with(TemporalAdjusters.firstDayOfMonth());
			break;
		default:
			date = date.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
			break;
		}
		
		return date;
		
		
		
		
	}
}
