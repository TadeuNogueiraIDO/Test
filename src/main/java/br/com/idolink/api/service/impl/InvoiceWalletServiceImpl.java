package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.response.InvoiceWalletResponse;
import br.com.idolink.api.dto.response.ido.PaymentInvoiceResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.InvoiceFilter;
import br.com.idolink.api.mapper.InvoiceWalletMapper;
import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.Invoice;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.InvoicePaymentStatus;
import br.com.idolink.api.model.enums.Month;
import br.com.idolink.api.repository.IdoToolRepository;
import br.com.idolink.api.repository.InvoiceRepository;
import br.com.idolink.api.repository.ToolFilterRepository;
import br.com.idolink.api.repository.UserPlanPackageRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.InvoiceWalletService;
import br.com.idolink.api.service.MenuOrderService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class InvoiceWalletServiceImpl implements InvoiceWalletService{

	@Autowired
	private InvoiceRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvoiceWalletMapper mapper;
	
	@Autowired
	private ToolFilterRepository toolRepository;
	
	@Autowired
	private MenuOrderService orderService;
	
	@Autowired
	private UserPlanPackageRepository idoPlanRepository;

	
	@Autowired
	private IdoToolRepository idoToolRepository;
	
	
	@Override
	public InvoiceWalletResponse find(Long userId, InvoiceFilter filter) {
		LocalDate date = LocalDate.now().withMonth(filter.getMonth().getId()).withYear(filter.getYear().intValue());
		
		if(!verifyInvoiceMounth(date, userId)) {
			if(date.compareTo(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())) >= 0) {				
				createInvoice(userId);
			}
		}

		List<UserPlanPackage> idos = null;
		
		List<Long> idoIds = null;
		
		if(!StringUtils.isBlank(filter.getIdoIds())) {
			idoIds = IdoStringUtils.convertStringLong(filter.getIdoIds());
		}
		
		ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> orderInvoices = orderService.findOrderInvoice(date, idoIds, userId);
		
		Invoice invoice = repository.find(userId, filter, date);
		
	
		idos = toolRepository.findToolInvoice(date, userId, idoIds);
			
		if(Objects.nonNull(invoice)) {
			return mapper.modelToResponse(invoice, orderInvoices.right, orderInvoices.left, idos);
		} 
		InvoiceWalletResponse respDafault = new InvoiceWalletResponse();
		List<Long> yearss = new ArrayList<>();
		yearss.add(filter.getYear()+1);
		yearss.add(filter.getYear());
		yearss.add(filter.getYear()-1);
		respDafault.setYears(yearss);
		return respDafault;
	}
	public Boolean verifyInvoiceMounth(LocalDate date, Long userId) {
		return repository.existsByMonthAndYearAndUserId(Month.getById(date.getMonthValue()), Long.valueOf(date.getYear()), userId);
	}
	
	
	@Transactional
	@Override
	public Invoice createInvoice(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() 
				-> new BaseFullException(HttpStatus.NOT_FOUND, "User", "api.error.user.not.found"));
		
		LocalDate date = LocalDate.now();
		Invoice model = new Invoice();
				model.setYear(Long.valueOf(date.getYear()));
				model.setMonth(Month.getById(date.getMonthValue()));
				model.setPaymentStatus(InvoicePaymentStatus.PROCESSING);
				model.setUser(user);
		model =	repository.save(model);
		return repository.save(model);
	}

	@Override
	@Transactional
	public PaymentInvoiceResponse payInvoice(Long userId, Long InvoiceId) {
		Invoice invoice = repository.findInvoiceToPay(InvoiceId, userId);
		
		
		invoice.setPaymentStatus(InvoicePaymentStatus.PAYMENTMADE);
		if(!Objects.nonNull(invoice)) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Invoice", "api.error.invoice.wallet.not.found");
		}
		invoice = repository.save(invoice);
		
		List<Invoice> invoices = repository.findInvoiceOverdue();
		
		if(invoices.size() == 0) {
				List<Long> idos = getIdoIds(invoice.getUser().getBusiness());
				idoPlanRepository.updateStatusPaid(idos);
				idoToolRepository.updateStatus(idos, IdoToolStatus.AVAILABLE);
		}
		return null;
	}
	
	
	private List<Long>getIdoIds(List<Business> business){
		List<Long> ids = new ArrayList<>();
		business.forEach(b ->{
			ids.addAll(b.getIdos().stream().map(i -> i.getId()).collect(Collectors.toList()));
		});
		return ids;
	}

}
