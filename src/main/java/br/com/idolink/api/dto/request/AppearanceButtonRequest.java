package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppearanceButtonRequest {
	
	private ButtonShapeRequest buttonShape;

	private String buttonColor;
	
	private String borderColor;

	private TextFontRequest textFont;
	
	private String fontColor;

}
