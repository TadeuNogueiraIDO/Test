package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.Size;

import br.com.idolink.api.model.enums.ImageAspect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCarouselRequest {

	private String title;
		
	private Boolean showTitle;
	
	private ImageAspect imageformat;
	
	@Size(max = 10, message = "A quantidade de imagens do Carrosel não pode ser maior que 10.")
	private List<ImageCarouselItemRequest> itens;
}
