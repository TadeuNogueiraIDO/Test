package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NotificationType {


	REQUEST(1L,"request"),
	GAMEFICATION(2L, "Gamefication"), // Não existe
	SYSTEMNOTIFICATION(3L,"System Notification"),
	FORMSNEWSLETTER(4L,"Forms Newslltter"),
	FORMSCONTACT(5L,"Forms Contact"),
	OVERDUEINVOICE(6L,"Overdue Invoice"), // Não existe
	COMISSION(7L,"Comission"), //Não existe
	PDFLEAD(8L,"PDF Lead"),
	WALLET(9L,"Wallet");
	
    private Long id;
    private String title;
    
	
	
}
