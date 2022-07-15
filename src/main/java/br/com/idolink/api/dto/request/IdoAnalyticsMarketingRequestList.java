package br.com.idolink.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdoAnalyticsMarketingRequestList {

	List<IdoAnalyticsMarketingRequest> analyticsMarketingRequests;

}
