package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdoAnalyticsMarketingResponse {

	private Boolean isActive;

	private String pixelValue;

	private AnalyticsMarketingResponse analyticsMarketing;

	private Long idoId;
	
}
