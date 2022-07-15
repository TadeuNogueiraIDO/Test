package br.com.idolink.api.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdoContactRequest {

	private IdoContactDialCodeRequest sms;
	
	private IdoContactDialCodeRequest phone;
	
	private IdoContactDialCodeRequest whatsapp;
	
	private IdoContactValueRequest mail;
	
	private IdoContactDialCodeRequest telegram;
	 
	private List<IdoContactValueRequest> addresses;

	private IdoContactValueRequest instagram;
	
	private IdoContactValueRequest facebook;
	
	private IdoContactValueRequest linkedin;

	private IdoContactValueRequest youtube;

	private IdoContactValueRequest tiktok;
	
	private IdoContactValueRequest site;
	
}
