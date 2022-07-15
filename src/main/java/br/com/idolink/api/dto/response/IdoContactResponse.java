package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdoContactResponse extends BaseToolResponse{
	
	private BlobFileResponse iconTool;
	
	private String nameTool;
	
	private Boolean activated;

	private IdoContactDialCodeResponse whatsapp;
	
	private IdoContactDialCodeResponse phone;
	
	private IdoContactDialCodeResponse sms;
	
	private IdoContactDialCodeResponse telegram;
	
	private IdoContactValueResponse mail;
	
	private List<IdoContactValueResponse> addresses;
	
	private IdoContactValueResponse instagram;

	private IdoContactValueResponse facebook;
	
	private IdoContactValueResponse linkedin;
	
	private IdoContactValueResponse youtube;
	
	private IdoContactValueResponse tiktok;
	
	private IdoContactValueResponse site;
	
	
}
