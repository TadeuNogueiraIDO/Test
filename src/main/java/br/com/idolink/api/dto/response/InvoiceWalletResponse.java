package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.idolink.api.model.enums.InvoicePaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceWalletResponse {
	
	private BigDecimal invoice;
	
	private List<InvoiceItens> itens;
	
	private InvoicePaymentStatus paymentStatus;
	
	private LocalDate dueDate;
	
	private List<Long> years;
}
