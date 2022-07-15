package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.QuickPayIdoShopDTO;
import br.com.idolink.api.dto.response.InvoiceItem;
import br.com.idolink.api.dto.response.InvoiceItens;
import br.com.idolink.api.dto.response.InvoiceWalletResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Invoice;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.ToolPlanPrice;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.Fee;
import br.com.idolink.api.model.enums.InvoiceType;
import br.com.idolink.api.model.enums.SubscriptionType;
import br.com.idolink.api.repository.ShopQuickPayRepository;

@Service
public class InvoiceWalletMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ShopQuickPayRepository shopQuickPayRepository;
	
	public InvoiceWalletResponse modelToResponse(Invoice model, List<ShopQuickPay> shop, List<SingleQuickPay> single, List<UserPlanPackage> tools) {
		
		List<InvoiceItens> itens = modelToItens(model, shop, single, tools);
	
		InvoiceWalletResponse response = InvoiceWalletResponse.builder()
				.dueDate(LocalDate.of(model.getYear().intValue(), model.getMonth().getId(), 5).plusMonths(1))
				.itens(itens)
				.invoice(model.getPrice() != null ? model.getPrice() : calculatePrice(itens))
				.paymentStatus(model.getPaymentStatus())
				.years(Arrays.asList(model.getYear()+1, model.getYear(), model.getYear()-1))
				.build();
		return response;
	}
	
	public BigDecimal calculatePrice(List<InvoiceItens> itens) {
		BigDecimal price = BigDecimal.ZERO;
		
		for(InvoiceItens i : itens){
			price = price.add(i.getItens().stream().map(p -> p.getPrice())
					.reduce(BigDecimal.ZERO, BigDecimal :: add));
		}
		return price;
	}
	
	public List<InvoiceItens> modelToItens(Invoice model, List<ShopQuickPay> shop, List<SingleQuickPay> single, List<UserPlanPackage> plans) {
		List<InvoiceItens> response = new ArrayList<>();
		
		HashMap<LocalDate, List<InvoiceItem>> itemDate = new HashMap<>();
		if(plans != null) {
				plans.forEach(p ->{
					if(itemDate.containsKey(p.getExpirationDate())) {
					
						ToolPlanPrice price = p.getToolPlanPackage().getToolPlanPrice().stream().filter(toolPrice -> 
						toolPrice.getPlanSubscription() == p.getPlanSubscription()).findFirst().orElseGet(null);
						
						itemDate.get(p.getExpirationDate()).add(idoToolsToItem(p, p.getIdo(), model.getPrice() != null? price: validatePrice(p.getExpirationDate(), price)));							
						
					}else {
						ToolPlanPrice price = p.getToolPlanPackage().getToolPlanPrice().stream().filter(toolPrice -> 
							toolPrice.getPlanSubscription() == p.getPlanSubscription()).findFirst().orElseGet(null);
						
						List<InvoiceItem> list = new ArrayList<>();
						list.add(idoToolsToItem(p, p.getIdo(), model.getPrice() != null? price : validatePrice(p.getExpirationDate(), price)));		
						itemDate.put(p.getExpirationDate(), list);

					}
				});
		}			
			
			single.forEach(s ->{
				if(itemDate.containsKey(LocalDate.now())) {
					itemDate.get(s.getDateModel().dt_created.toLocalDate()).add(singleToItem(s));
				}else {
					List<InvoiceItem> list = new ArrayList<>();
					list.add(singleToItem(s));
					itemDate.put(s.getDateModel().dt_created.toLocalDate(), list);
				}
			});
			
			shop.forEach(s ->{
				if(itemDate.containsKey(s.getDateModel().dt_created.toLocalDate())) {
					itemDate.get(s.getDateModel().dt_created.toLocalDate()).add(shopToItem(s));
				}else {
					List<InvoiceItem> list = new ArrayList<>();
					list.add(shopToItem(s));
					itemDate.put(s.getDateModel().dt_created.toLocalDate(), list);
				}
			});
		
		itemDate.forEach((date,itens ) ->{
			response.add(InvoiceItens.builder().date(date).itens(itens).build());
		});

		return response;
	}
	
	public ToolPlanPrice validatePrice (LocalDate expiredDate, ToolPlanPrice price) {
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		if(expiredDate.compareTo(date) > 0) {
			price.setPrice(BigDecimal.ZERO);
		}else {
			BigDecimal days = BigDecimal.valueOf(ChronoUnit.DAYS.between(expiredDate, date));
			BigDecimal dayCount = BigDecimal.valueOf(ChronoUnit.DAYS.between(expiredDate.withDayOfMonth(1), date));
			BigDecimal percentual = days.multiply(BigDecimal.valueOf(100)).divide(dayCount, 2, RoundingMode.HALF_UP);
			if(percentual.compareTo(BigDecimal.valueOf(100)) != 0){
				price.setPrice(price.getPrice().add(percentual.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).multiply(price.getPrice())));
			}
		}
		return price;
	}
	
	public InvoiceItem idoToolsToItem(UserPlanPackage model, Ido ido, ToolPlanPrice price) {
		
		InvoiceItem item = InvoiceItem.builder()
		.paymentStatus(model.getPaymentStatus())
		.typePackage(model.getToolPlanPackage().getToolType())
		.nameIdo(ido.getName())
		.domainIdo(ido.getDomain())
		.id(model.getId())
		.type(InvoiceType.TOOL)
		.subcriptionType(SubscriptionType.getById(price.getPlanSubscription().getId()))
		.price(price.getPrice().negate())
		.build();
		
//		model.getTool().getToolCurrency().stream().filter(c ->
//				c.getCurrency().getSimbol().equals(model.getIdo().getCurrency()))
//				.findFirst().ifPresentOrElse(p -> item.setPrice(BigDecimal.valueOf(p.getPrice())), () -> item.setPrice(BigDecimal.ZERO));

		return item;		
	}
	
	public InvoiceItem singleToItem(SingleQuickPay model) {
		BigDecimal price = BigDecimal.valueOf(0.24);
	
		return InvoiceItem.builder()
				.id(model.getId())
				.fee(Fee.PROCESSINGFEE)
				.order(model.getOrderNumber())
				.price(price)
				.type(InvoiceType.SINGLEQUICKPAY).build();
	}
	
	public InvoiceItem shopToItem(ShopQuickPay model) {
		BigDecimal price = BigDecimal.valueOf(0.24);
		
		Optional<QuickPayIdoShopDTO> names = shopQuickPayRepository.findByShopQuickPay(model.getId());
		
		//String idoName = names.isPresent()? names.get().getIdoName() : null;
		String shopName = names.isPresent()? names.get().getShopName() : null;
			
		return InvoiceItem.builder()
				.id(model.getId())
				//.nameIdo(idoName)
				.name(shopName)
				.fee(Fee.PROCESSINGFEE)
				.price(price)
				.order(model.getOrderNumber())
				.type(InvoiceType.SHOPQUICKPAY).build();
	}
	public Invoice response (InvoiceWalletResponse response) {
		return mapper.map(response, Invoice.class);
		
		
	}
	
	
}
