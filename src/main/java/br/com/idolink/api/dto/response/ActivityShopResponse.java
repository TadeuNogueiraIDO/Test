package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityShopResponse {
	
	private Long id;
	
	private ActivityResponse typeActivity;
	
	private String otherSegment;
	
}
