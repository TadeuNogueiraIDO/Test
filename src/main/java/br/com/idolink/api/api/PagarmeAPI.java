package br.com.idolink.api.api;

import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.request.ido.GatewayIdoToWithdraw;
import br.com.idolink.api.dto.response.WalletRecipientResponse;
import br.com.idolink.api.dto.response.ido.GatewayIdoGeneralBalance;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PagarmeAPI {
	
    @RequestLine("POST /1/recipients")
    @Headers({ "Content-Type: application/json"})
    public WalletRecipientResponse createRecipient(WalletRequest request);
    
    @RequestLine("GET /1/recipients/{recipientId}/balance")
    @Headers({"Content-Type: application/json"})
    public GatewayIdoGeneralBalance findGeneralBalance(@Param("recipientId") String token);
    
    @RequestLine("POST /1/transfers")
    @Headers({"Content-Type: application/json"})
    public Object toWithdraw( GatewayIdoToWithdraw request);
	
}
	