package br.com.idolink.api.dto.request;



import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.TypeSale;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOrderFilterGenericRequest {

	@NotNull
	private Long idoId;
	
	@NotNull
	private TypeSale typeSale;
	
	@NotNull
	private LocalDate date;
	
	
}

