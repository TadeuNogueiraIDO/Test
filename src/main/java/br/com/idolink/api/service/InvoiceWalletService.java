package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.InvoiceWalletResponse;
import br.com.idolink.api.dto.response.ido.PaymentInvoiceResponse;
import br.com.idolink.api.filter.InvoiceFilter;
import br.com.idolink.api.model.Invoice;

public interface InvoiceWalletService {

	InvoiceWalletResponse find(Long userId, InvoiceFilter filter);
	
	PaymentInvoiceResponse payInvoice(Long userId,Long InvoiceId);
	
	Invoice createInvoice(Long userId);
}
