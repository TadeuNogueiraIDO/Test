package br.com.idolink.api.dto.request;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimezoneRequest {
	
	private String codName;
	
	private String description;
	
	private Time gmt;

}
