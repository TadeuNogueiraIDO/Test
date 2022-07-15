package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ImageBannerContactRequest {

	@NotNull
	private String number;

	@NotBlank
	private String dialCode;
}
