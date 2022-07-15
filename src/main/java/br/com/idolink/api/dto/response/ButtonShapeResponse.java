package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ButtonShapeResponse {

	private Long id;

	private String name;

	private Double borderWidth;

	private Double borderRadiusTopLeft;

	private Double borderRadiusTopRight;

	private Double borderRadiusBottomLeft;

	private Double borderRadiusBottomRight;
}
