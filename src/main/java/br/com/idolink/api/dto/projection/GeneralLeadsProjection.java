package br.com.idolink.api.dto.projection;

import java.time.LocalDateTime;


public interface GeneralLeadsProjection {
	
	Long getId();
	
	String getTools();
	
	String getTitle_tool();
	
	String getEmail();
	
	String getPhone();
	
	String getStatus_leads();
	
	String getText_box();
	
	LocalDateTime getDt_created();
}
