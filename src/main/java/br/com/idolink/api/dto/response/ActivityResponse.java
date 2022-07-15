package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.TypeActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
	
	private Long id;
	
	private TypeActivity typeActivity;

	private String segment;
	
	private boolean hasOtherSegment;
}
