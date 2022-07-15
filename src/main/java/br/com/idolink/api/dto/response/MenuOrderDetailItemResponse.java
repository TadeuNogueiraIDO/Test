package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderDetailItemResponse {
	
	private Long orderId;
	
	private LocalDateTime date;
	
	List<MenuOrderItem> itens;
	
	private BigDecimal additionalValue;
	
	private BigDecimal discountValue;
	
	private BigDecimal sendValue;
	
	private BigDecimal totalValue;
	
	private BigDecimal subtotal;
	
	private String orderNumber; 

}
