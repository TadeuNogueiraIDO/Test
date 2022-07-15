package br.com.idolink.api.filter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.idolink.api.model.enums.MenuOrderDate;
import br.com.idolink.api.model.enums.OrderTypeStatus;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeSale;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderFilter {

	private String idoId;
	
	private String orderNumber;
	
	private QuickPayPaymentStatus statusPayment;
	
	private TypeSale typeSale;
	
	private QuickPaySedingStatus statusSeding;
	
	@ApiModelProperty(example = "OPEN")
	private OrderTypeStatus orderTypeStatus;
	
	private String nameIdo;
	
	private MenuOrderDate period;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime initDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime endDate;
	
	
	
}
