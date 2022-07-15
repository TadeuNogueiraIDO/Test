package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushNotificationTypeRequest {

	
	private String title;
	
	private String message;
	
	private Long icon;
	
}
