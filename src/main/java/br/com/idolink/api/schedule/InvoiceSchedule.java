package br.com.idolink.api.schedule;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.filter.ShopDashboardFilter;
import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.DiscountCoupon;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Invoice;
import br.com.idolink.api.model.ToolPlanPrice;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.InvoicePaymentStatus;
import br.com.idolink.api.model.enums.Month;
import br.com.idolink.api.model.enums.PlanPackagePaymentStatus;
import br.com.idolink.api.model.enums.ReportPeriod;
import br.com.idolink.api.model.enums.ShopDashboardDate;
import br.com.idolink.api.repository.DiscountCouponRepository;
import br.com.idolink.api.repository.GeneralSettingsRepository;
import br.com.idolink.api.repository.IdoToolRepository;
import br.com.idolink.api.repository.InvoiceRepository;
import br.com.idolink.api.repository.ShopQuickPayRepository;
import br.com.idolink.api.repository.SingleQuickPayRepository;
import br.com.idolink.api.repository.UserPlanPackageRepository;
import br.com.idolink.api.service.AccessReportService;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.InvoiceWalletService;
import br.com.idolink.api.service.ShopDashboardService;
import br.com.idolink.api.service.email.SendEmailService;

@EnableScheduling
@Component
public class InvoiceSchedule {

	@Autowired
	private SendEmailService sendingEmail;
	
	@Autowired
	private InvoiceRepository repository;

	@Autowired
	private IdoService serviceIdo;

	
	@Autowired
	private InvoiceWalletService invoiceService;

	@Autowired
	private AccessReportService serviceReport;
	
	@Autowired
	private ShopDashboardService serviceShopDashboard;

	@Autowired
	private UserPlanPackageRepository idoPlanRepository;

	
	@Autowired
	private IdoToolRepository idoToolRepository;

	
	@Autowired
	private SingleQuickPayRepository singleRepository;

	
	@Autowired
	private ShopQuickPayRepository shopRepository;

	@Autowired
	private GeneralSettingsRepository repositoryGeneral;
	
	@Autowired
	private DiscountCouponRepository repositoryCoupon;

	
	@Scheduled(cron = "0 0 0 1 * *")
	@Transactional
	public void closeInvoice() {


		LocalDate date = LocalDate.now().minusMonths(1);
		
		List<Invoice> invoices = repository.findInvoice(Month.getById(date.getMonthValue()), Long.valueOf(date.getYear()));
	
		
		List<Invoice> models = new ArrayList<>();
		
		LocalDateTime startDate = date.withDayOfMonth(1).atStartOfDay();
		LocalDateTime endTime =  date.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);
		
		invoices.forEach(i ->{
			List<UserPlanPackage> planUser = idoPlanRepository.findByIdoIds(getIdoIds(i.getUser().getBusiness()), startDate.toLocalDate(), endTime.toLocalDate());
			i.setPaymentStatus(InvoicePaymentStatus.WAITINGPAYMENT);
			BigDecimal total = BigDecimal.ZERO;
			total = total.add(toolPrice(planUser, PlanPackagePaymentStatus.WAITINGPAYMENT));
			total = total.add(BigDecimal.valueOf(singleRepository.countInvoice(i.getUser().getId(),startDate , endTime) * 0.24));
			total = total.add(BigDecimal.valueOf(shopRepository.countInvoice(i.getUser().getId(),startDate , endTime) * 0.24));
			i.setPrice(total);
			models.add(i);
			invoiceService.createInvoice(i.getUser().getId());
		});
		if(models.size() > 0) {			
			repository.saveAll(models);
		}
	}
	
	@Scheduled(cron = "0 0 0 6 * *")
	@Transactional
	public void validInvoice() {
		
		List<Invoice> invoices = repository.findInvoiceWaitingPyment();
		List<Invoice> invoicesUpdating = new ArrayList<>();
		
		invoices.forEach(i ->{
			i.setPaymentStatus(InvoicePaymentStatus.OVERDUE);
			List<Long> idos = getIdoIds(i.getUser().getBusiness());
			idoPlanRepository.updateStatusExpired(idos);
			idoToolRepository.updateStatus(idos, IdoToolStatus.INACTIVATED);
			invoicesUpdating.add(i);
		});
		repository.saveAll(invoicesUpdating);
	}
	
	
	
	
	private List<Long>getIdoIds(List<Business> business){
		List<Long> ids = new ArrayList<>();
		business.forEach(b ->{
			ids.addAll(b.getIdos().stream().map(i -> i.getId()).collect(Collectors.toList()));
		});
		return ids;
	}
	
	public BigDecimal calculatePrice (LocalDate expiredDate, ToolPlanPrice price) {
		LocalDate date = LocalDate.now(); 
		
		BigDecimal days = BigDecimal.valueOf(ChronoUnit.DAYS.between(expiredDate, date));
		BigDecimal dayCount = BigDecimal.valueOf(ChronoUnit.DAYS.between(expiredDate.withDayOfMonth(1), date));
		BigDecimal percentual = days.multiply(BigDecimal.valueOf(100)).divide(dayCount, 2, RoundingMode.HALF_UP);
		price.setPrice(price.getPrice().add(percentual.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).multiply(price.getPrice())));
		return price.getPrice();

	}
	
	public BigDecimal toolPrice(List<UserPlanPackage> planUser, PlanPackagePaymentStatus statusPayment) {
		BigDecimal price = BigDecimal.ZERO;
		List<UserPlanPackage> updatePlan = new ArrayList<>();
		
		for(UserPlanPackage p : planUser) { 
			ToolPlanPrice planPrice = p.getToolPlanPackage().getToolPlanPrice().stream().filter(toolPrice -> 
				toolPrice.getPlanSubscription() == p.getPlanSubscription()).findFirst().orElseGet(null);
			
			BigDecimal value = calculatePrice(p.getExpirationDate(), planPrice);
			if(value.compareTo(BigDecimal.ZERO) >0) {				
				p.setPaymentStatus(statusPayment);
				updatePlan.add(p);
			}
			price = price.add(value);
		}
		if(updatePlan.size() > 0) {			
			idoPlanRepository.saveAll(updatePlan);
		}
		return price;
	}
	
	@Scheduled(cron="0 0 0 * * MON")
	public void SendingEmailsIntheWeek() {

		List<GeneralSettings> models = repositoryGeneral.findAll();

		for (GeneralSettings model : models) {
			if (model.getReceiveReportEmailWeek()) {

				if (model.getUser() != null) {
						
					String IdosFormatString = null;
					for (Ido Idos : serviceIdo.findModelByUser(model.getUser().getId())) {
						if(nonNull(Idos.getId())) {
						IdosFormatString = new StringBuilder().append(IdosFormatString!=null? IdosFormatString:"").append(Idos.getId().toString()).append(",").toString();
								
						}
						
					}
					if(nonNull(IdosFormatString)) {
						System.out.println("IdoList"+IdosFormatString);
					VisitorsClicksResponse VisitorsData = serviceReport.getVisitorsAndCliscks(model.getUser().getId(),
							IdosFormatString, ReportPeriod.WEEK);

				
					sendingEmail.sendMail(model.getUser().getEmail(), "\n Weekly report"," Total de Visitantes: " + VisitorsData.getTotalVisitors()+ "\n Distinto do Total De Visitantes: " + VisitorsData.getDistinctTotalVisitors()+ "\n Clicks: " + VisitorsData.getClicks() + "\n Clicks Convertidos: " + VisitorsData.getConvertedClicks() + "\n ConversionTax"+ VisitorsData.getConversionTax()+ "\n ClickTools: " + VisitorsData.getClickTools() ); 
					}
				}
							
			}


		}
	}
	
	@Scheduled(cron="0 0 1 * * ?")
	public void SendingEmailsIntheMonth() {

		List<GeneralSettings> models = repositoryGeneral.findAll();

		for (GeneralSettings model : models) {
			
			if (model.getReceiveReportEmailMonth()) {

				if (model.getUser() != null) {
						
							
													

				ShopDashboardResponse ShopData = serviceShopDashboard.find(model.getUser().getId(),ShopDashboardFilter
						.builder()
						.period(ShopDashboardDate.MONTH).build() );
							

				
 				sendingEmail.sendMail(model.getUser().getEmail(), "\n Month report"," \n Pedidos: " +ShopData.getOrder() + "\n Vendas: "+ ShopData.getSales()); 
				}
			}
							
			}


		}
	
	
	@Scheduled(cron="0 0 0/1 * * *")
	public void deleteValidateExpiredDate() {
		
		 LocalDate dateAtual = LocalDate.now();
		
		List<DiscountCoupon> couponList = repositoryCoupon.findAll();
		for(DiscountCoupon model : couponList) {
			
			if(model.getDatexpirationDate()==dateAtual) {
				model.setStatus(false);
				repositoryCoupon.save(model);
			}
			}
			
		}
	}
	
