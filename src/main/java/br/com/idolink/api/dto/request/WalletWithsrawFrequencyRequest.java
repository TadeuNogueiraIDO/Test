package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.PeriodFrequency;
import br.com.idolink.api.model.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletWithsrawFrequencyRequest {

	@NotNull
	private PeriodFrequency periodFrequency;
	
	private WeekDay weekDay;
	
	private Long day;
}
