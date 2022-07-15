package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ClientQuickPay implements Serializable{
	
	private static final long serialVersionUID = 5584313284989640818L;

	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "client_email")
	private String clientEmail;
	
	@Column(name = "client_phone")
	private String clientPhone;
	
	@Column(name = "client_document")
	private String clientDocument;
	
	@Column(name = "client_address")
	private String clientAddress;
		
}
