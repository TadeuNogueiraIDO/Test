package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorsRequest {

	private Long id;
	
	@JsonIgnore
	private String name;
	
	@JsonIgnore
	private String hexadecimalCode;
}
