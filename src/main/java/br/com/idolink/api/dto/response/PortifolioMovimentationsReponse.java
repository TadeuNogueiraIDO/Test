package br.com.idolink.api.dto.response;


import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortifolioMovimentationsReponse {
	private LocalDate date;
	
	private List<PortifolioMovimentationItem> itens;
}
