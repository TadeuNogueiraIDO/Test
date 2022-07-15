package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextFontRequest {

	private Long id;

	@JsonIgnore
	private String name;

	@JsonIgnore
	private String style;

	@JsonIgnore
	private Double size;

	@JsonIgnore
	private Double lineHeight;

	@JsonIgnore
	private String align;
	
	@JsonIgnore
	private String fontFamily;

}
