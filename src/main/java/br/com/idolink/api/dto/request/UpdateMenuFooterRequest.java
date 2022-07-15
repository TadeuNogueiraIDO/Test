package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMenuFooterRequest {

	private Long logo;
	
	private Boolean unpinMenu;
	
	private Boolean activateFooter;
}
