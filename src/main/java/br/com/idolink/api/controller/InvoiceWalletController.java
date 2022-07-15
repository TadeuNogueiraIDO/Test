package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.InvoiceWalletControllerSwagger;
import br.com.idolink.api.dto.response.InvoiceWalletResponse;
import br.com.idolink.api.dto.response.ido.PaymentInvoiceResponse;
import br.com.idolink.api.filter.InvoiceFilter;
import br.com.idolink.api.service.InvoiceWalletService;

@RestController
@RequestMapping("/invoice")
public class InvoiceWalletController implements InvoiceWalletControllerSwagger{

	@Autowired
	private InvoiceWalletService service;
	
	@Autowired
	private IdolinkSecurity security;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<InvoiceWalletResponse> find(InvoiceFilter filter) {
		return ResponseEntity.ok(service.find(security.getUsuarioId(), filter));
	}

	@Override
	@GetMapping("/{invoiceId}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PaymentInvoiceResponse> payInvoice(@PathVariable Long invoiceId) {
		return ResponseEntity.ok(service.payInvoice(security.getUsuarioId(), invoiceId));
	}

	
	
}
