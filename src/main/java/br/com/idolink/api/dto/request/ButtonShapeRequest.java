package br.com.idolink.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonShapeRequest {

	private Long id;

	@JsonIgnore
	private String name;
	
	@JsonIgnore
	private Double borderWidth;
	
	@JsonIgnore
	private Double borderRadiusTopLeft;
	
	@JsonIgnore
	private Double borderRadiusTopRight;
	
	@JsonIgnore
	private Double borderRadiusBottomLeft;
	
	@JsonIgnore
	private Double borderRadiusBottomRight;
	
}
