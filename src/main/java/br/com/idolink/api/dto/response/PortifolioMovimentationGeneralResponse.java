package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortifolioMovimentationGeneralResponse {
	
	private List<PortifolioMovimentationsReponse> portifolioMoviments;
	
	private BigDecimal total;
}
