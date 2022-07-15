package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextFontResponse {

	private Long id;

	private String name;
		
	private String style;
	
	private Double size;
	
	private Double lineHeight;

	private String align;
	
	private String fontFamily;
	
}
