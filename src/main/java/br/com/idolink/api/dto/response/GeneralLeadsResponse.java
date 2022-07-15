package br.com.idolink.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralLeadsResponse {

	private Long id;
	
	private String tools;
		
	private String statusLeads;
	
	private String titleTool;
	
	private String phone;
	
	private String message;
	
	private String email;

	private LocalDateTime creationDate;
	
	
}
