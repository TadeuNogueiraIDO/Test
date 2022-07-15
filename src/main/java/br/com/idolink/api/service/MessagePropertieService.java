package br.com.idolink.api.service;

public interface MessagePropertieService {
	
	String getMessageAttribute(String propertie);	

	String getMessageAttributeByUser(String string, Long idUser);	
}
