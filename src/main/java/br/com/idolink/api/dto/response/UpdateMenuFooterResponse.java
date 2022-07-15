package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuFooterResponse {

	private Long logo;
	
	private Boolean unpinMenu;
	
	private Boolean activateFooter;
}
