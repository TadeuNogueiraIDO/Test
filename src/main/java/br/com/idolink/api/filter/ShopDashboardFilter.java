package br.com.idolink.api.filter;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.ShopDashboardDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopDashboardFilter {
	private ShopDashboardDate period;
	
	@NotNull
	private Long shopId;
}
