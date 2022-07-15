package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.TypeActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {

	private TypeActivity typeActivity;

	private String segment;
	
	private boolean hasOtherSegment;
}
