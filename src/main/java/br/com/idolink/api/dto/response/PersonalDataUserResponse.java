package br.com.idolink.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDataUserResponse {
	
	private String name;
	
	private LocalDate birthData;
	
	private String dialCode;

	private String number;

}
