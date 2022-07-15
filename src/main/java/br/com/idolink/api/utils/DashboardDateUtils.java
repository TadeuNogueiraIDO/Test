package br.com.idolink.api.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.enums.ShopDashboardDate;

public class DashboardDateUtils {
	
	public static List<LocalDateTime> convertEnumToDate(ShopDashboardDate date){
		List<LocalDateTime> dates = new ArrayList<>();
		
		switch(date) {
			case SIX_MONTH:
				dates.add(LocalDate.now().atStartOfDay().minusMonths(6));
				dates.add(LocalDate.now().atTime(LocalTime.MAX));
				break;
			case YEAR:
				dates.add(LocalDate.now().atStartOfDay().minusYears(1));
				dates.add(LocalDate.now().atTime(LocalTime.MAX));
				break;
			case MONTH:
				dates.add(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay());
				dates.add(LocalDate.now().atTime(LocalTime.MAX));
				break;
			case THREE_MONTH:
				dates.add(LocalDate.now().minusMonths(3).atStartOfDay());
				dates.add(LocalDate.now().atTime(LocalTime.MAX));
				break;
			case WEEK:
				dates.add(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).atStartOfDay());
				dates.add(LocalDate.now().atTime(LocalTime.MAX));
				break;
			default:
				throw new BaseFullException(HttpStatus.BAD_REQUEST,"period", "api.error.date.not.found");
		}
		return dates;
	}
}
