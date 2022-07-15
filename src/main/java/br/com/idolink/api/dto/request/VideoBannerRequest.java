package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoBannerRequest {
	
	private String title;
	
	private String link;
	
	private String thumbnail;
		
}
