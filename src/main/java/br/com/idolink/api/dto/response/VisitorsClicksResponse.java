package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitorsClicksResponse {

	private Long totalVisitors;
	
	private Long distinctTotalVisitors;
	
	private Long clicks;
	
	private Float convertedClicks;
	
	private Float conversionTax;
	
	private List<ClicksToolsResponse> clickTools;
	
	private Boolean reportEmailWeek;
	
		
}
