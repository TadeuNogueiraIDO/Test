package br.com.idolink.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppearanceCardsResponse {

	private Long id;
	
	@JsonProperty("formatCard")
	private ButtonShapeResponse buttonShape;

	@JsonProperty("colorCard")
	private String cardColor;
	
	private String borderColor;

	private TextFontResponse textFont;

	private String fontColor;
}
