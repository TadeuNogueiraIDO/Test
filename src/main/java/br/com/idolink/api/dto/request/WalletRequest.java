package br.com.idolink.api.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.idolink.api.dto.request.common.GenericRequest;
import br.com.idolink.api.dto.request.ido.WalletBankRequest;
import br.com.idolink.api.model.enums.DocumentType;
import br.com.idolink.api.model.enums.PeriodFrequency;
import br.com.idolink.api.model.enums.TypeWallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WalletRequest {
	
	@NotBlank
	private String walletNickname;
	
	@NotNull
	private DocumentType documentType;
	
	@NotBlank
	private String nameReason;
	
	@NotBlank
	private String document;
	
	@NotNull
	private TypeWallet typeWallet;
	
	@NotBlank
	@Size(max = 13, min = 13)
	private String telephone;
	
	@NotNull
	private GenericRequest bank;
	
	@Valid
	private WalletWithsrawFrequencyRequest withdrawFrequency;

	@NotNull
	private PeriodFrequency transfer_interval;
	
	@NotNull
	private Integer transfer_day;
	
	@NotNull
	private Boolean transfer_enabled;
	
	@NotNull
	private WalletBankRequest bank_account;
	
	
	
}
