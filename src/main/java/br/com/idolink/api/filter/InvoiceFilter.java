package br.com.idolink.api.filter;

import java.time.LocalDate;

import br.com.idolink.api.model.enums.Month;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceFilter {
	
	@Builder.Default
	private Long year = Long.valueOf(LocalDate.now().getYear());
	
	private String idoIds;
	
	@Builder.Default
	private Month month = Month.getById(LocalDate.now().getMonthValue());
}
