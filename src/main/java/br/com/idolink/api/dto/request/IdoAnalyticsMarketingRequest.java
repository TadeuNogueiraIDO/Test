package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdoAnalyticsMarketingRequest {

	private boolean isActive;

	private String pixelValue;

	private Long analyticsMarketingId;

}
