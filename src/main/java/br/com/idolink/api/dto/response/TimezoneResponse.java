package br.com.idolink.api.dto.response;

import java.sql.Time;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimezoneResponse{
	
	private Long id;
	
	private String codName;
	
	@JsonIgnore
	private String description;
	
	@JsonIgnore
	private Time gmt;
	
	@JsonIgnore
	private Integer value;
	
	public String getFullDescription(){
	
		StringBuilder gmt = new StringBuilder();
		gmt.append(getDescription());
		gmt.append(" (");
				
		if(value < 0) {
		  gmt.append("-"); 
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		gmt.append(sdf.format(getGmt()));
			
		return gmt.append(")").toString();
	}
}
