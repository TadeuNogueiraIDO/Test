package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import br.com.idolink.api.model.enums.TypeMenuOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortifolioMovimentationItem {
	
	private Long id;
	
	private BigDecimal price;
	
	private String title;
	
	private String name;
	
	private String orderNumber;
	
	private TypeMenuOrder orderType;
}
