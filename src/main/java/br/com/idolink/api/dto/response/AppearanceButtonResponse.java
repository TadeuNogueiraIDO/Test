package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppearanceButtonResponse {

	private Long id;
	
	private ButtonShapeResponse buttonShape;

	private String buttonColor;
	
	private String borderColor;

	private TextFontResponse textFont;

	private String fontColor;
}
