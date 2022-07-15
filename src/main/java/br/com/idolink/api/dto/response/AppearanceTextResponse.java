package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppearanceTextResponse {

	private Long id;
	
	private String backgroundColor;

	private TextFontResponse textFont;

	private String fontColor;
}
