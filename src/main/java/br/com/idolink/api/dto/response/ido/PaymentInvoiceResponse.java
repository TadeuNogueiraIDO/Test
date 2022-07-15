package br.com.idolink.api.dto.response.ido;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInvoiceResponse {
	private String code;
	private LocalDateTime dueDate;
}
