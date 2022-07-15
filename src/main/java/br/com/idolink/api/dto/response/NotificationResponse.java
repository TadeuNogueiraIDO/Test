package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
	
	private Long id;
	
	private String title;
	
	private String message;
	
	private Double value;
	
	private Boolean read;
	
	private Long userId;
	
	private NotificationType typeNotification;
	
	private BlobFileResponse icon;
	
	private String link;
	

}
