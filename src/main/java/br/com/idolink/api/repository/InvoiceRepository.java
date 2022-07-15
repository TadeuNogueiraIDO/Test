package br.com.idolink.api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Invoice;
import br.com.idolink.api.model.enums.Month;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, InvoiceRepositoryFilter{
	
	Boolean existsByMonthAndYearAndUserId(Month month, Long year, Long userId);
	
	@Query("FROM Invoice i WHERE i.price = null AND month = :month AND year = :year")
	List<Invoice> findInvoice(Month month, Long year);
	
	@Query("FROM Invoice i WHERE i.paymentStatus = 'WAITINGPAYMENT'")
	List<Invoice> findInvoiceWaitingPyment();
	
	@Query("FROM Invoice i WHERE i.paymentStatus = 'OVERDUE'")
	List<Invoice> findInvoiceOverdue();
	
	@Query("FROM Invoice i WHERE i.user.id = :userId AND i.id = :invoiceId AND i.paymentStatus = WAITINGPAYMENT")
	Invoice findInvoiceToPay(Long invoiceId, Long userId);
	
	@Query( value = "FROM Invoice i WHERE i.user.id = :userId AND i.price != NULL")
	List<Invoice> findInvoicesByUser(Long userId);
	
	@Query( value = "FROM Invoice i WHERE i.user.id = :userId AND i.paymentStatus = 'OVERDUE'")
	List<Invoice> findInvoicesPymentByUser(Long userId);
	
	@Query(value = "select sum(cast(price as float8)) as price from invoice i where i.user_id = ?1 and i.price is not null group by i.user_id;", nativeQuery = true)
	BigDecimal getTotalPriceInvoices(Long userId);
}
