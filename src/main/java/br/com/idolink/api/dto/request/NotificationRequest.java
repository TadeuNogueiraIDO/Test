package br.com.idolink.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
		
	
	private String title;
	
	private String message;
	
	private Double value;
	
	private Long icon;
	
	

}
