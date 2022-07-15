package br.com.idolink.api.dto.response;



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
public class WalletWithdrawFrequencyResponse {
	
	private Long id;

	private PeriodFrequency periodFrequency;

	private WeekDay weekDay;

	private Long day;
}
