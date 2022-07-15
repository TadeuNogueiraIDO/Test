package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NotificationSettingRequest {
	
	@NotNull
	private Boolean activeNotifications;
	
	@NotNull
	private Boolean systemWarnings;
	
	@NotNull
	private Boolean requests;
	
	@NotNull
	private Boolean wallet;
	
	@NotNull
	private Boolean form;
	
	@NotNull
	private Boolean commissions;
		
}