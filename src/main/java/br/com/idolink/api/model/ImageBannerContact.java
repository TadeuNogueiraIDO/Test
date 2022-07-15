package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageBannerContact {

	@Column(name = "dial_code")
	private String dialCode;
	
	@Column(name = "number")
	private String number;
}
