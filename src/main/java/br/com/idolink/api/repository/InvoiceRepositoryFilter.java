package br.com.idolink.api.repository;

import java.time.LocalDate;

import br.com.idolink.api.filter.InvoiceFilter;
import br.com.idolink.api.model.Invoice;

public interface InvoiceRepositoryFilter {
	
	Invoice find(Long userId, InvoiceFilter filter, LocalDate date);
}
