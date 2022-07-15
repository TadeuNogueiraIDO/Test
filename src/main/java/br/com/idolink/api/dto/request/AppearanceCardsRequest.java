package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppearanceCardsRequest {
	
	@JsonProperty("formatCard")
	private ButtonShapeRequest buttonShape;

	@JsonProperty("colorCard")
	private String cardColor;
	
	private String borderColor;

	private TextFontRequest textFont;
	
	private String fontColor;

}
