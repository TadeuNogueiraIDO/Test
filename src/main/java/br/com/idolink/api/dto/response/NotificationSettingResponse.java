package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationSettingResponse {
	
	private Long id;
	private Boolean activeNotifications;
	private Boolean systemWarnings;
	private Boolean requests;
	private Boolean wallet;
	private Boolean form;
	private Boolean commissions;
	
}