package br.com.idolink.api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.DashboardFormPaymentsResponse;
import br.com.idolink.api.dto.response.DashboardSalesFunnelResponse;
import br.com.idolink.api.dto.response.DashboardTopSellingProductsResponse;
import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.ShopDashboardFilter;
import br.com.idolink.api.mapper.ShopDashboardMappper;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.ShopDashboardDate;
import br.com.idolink.api.repository.GeneralSettingsRepository;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.MenuOrderService;
import br.com.idolink.api.service.ShopDashboardService;
import br.com.idolink.api.service.UserService;
import br.com.idolink.api.utils.DashboardDateUtils;

@SuppressWarnings("unused")
@Service
public class ShopDashboardServiceImpl implements ShopDashboardService{
	
	@Autowired
	private IdoService serviceIdo;


	@Autowired
	private MenuOrderService menuOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShopDashboardMappper mapper;	
	
	@Autowired
	private PaymentTypeRepository paymentRepository;	
	
	@Autowired
	private GeneralSettingsRepository repositoryGeneral;
	
	@Override
	public ShopDashboardResponse find(Long userId, ShopDashboardFilter filter) {
		BigDecimal quantity = BigDecimal.ZERO;
		DashboardSalesFunnelResponse salesFunnel = new DashboardSalesFunnelResponse();
		
		GeneralSettings model = repositoryGeneral.findByUserId(userId);
        validate(model, "UsuarioGeneral ", "Erro");
		
		List<LocalDateTime> dates = null;
		if(Objects.nonNull(filter.getPeriod())) {
			dates = DashboardDateUtils.convertEnumToDate(filter.getPeriod());
		}
					
		userService.findById(userId);
		ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> orders = menuOrderService.findOrderDashboard(dates, userId, filter.getShopId());
		
		quantity = BigDecimal.valueOf(orders.getLeft().size() + orders.right.size());
		
		HashMap<PaymentType, DashboardFormPaymentsResponse> paymentTypes = new HashMap<>();
		HashMap<String, DashboardTopSellingProductsResponse> sellingProducts = new HashMap<>();
		
		BigDecimal totalValues = BigDecimal.ZERO;
		
		for(SingleQuickPay single : orders.left) {
			totalValues = totalValues.add(calculateValue(single));
			calculateSalesFunnel(single.getStatusPayment(), salesFunnel);
			calculatePaymentType(single.getPaymentType(), paymentTypes);
			calculateSellingProducts(single, sellingProducts);
		}
		
		for(ShopQuickPay shop : orders.right) {
			totalValues = totalValues.add(calculateValue(shop));
			calculateSalesFunnel(shop.getStatusPayment(), salesFunnel);
			calculatePaymentType(shop.getPaymentType(), paymentTypes);
			calculateSellingProducts(shop, sellingProducts);
		}
		 ShopDashboardResponse response =  mapper.convertToResponse(totalValues, quantity, salesFunnel, paymentTypes, sellingProducts);
		 response.setReceiveReportEmailMonth(model.getReceiveReportEmailMonth());
		 return response;
	}
	@Override
	public ShopDashboardResponse canSendReportEmailMonth(Long id, Boolean reportEmail) {

		GeneralSettings model = repositoryGeneral.findByUserId(id);
		
		if (model != null) {
		model.setReceiveReportEmailMonth(reportEmail);

		model = repositoryGeneral.save(model);
			
		return find(id, ShopDashboardFilter
				.builder()
				.period(ShopDashboardDate.MONTH).build());
				
	}

	throw new BaseFullException(HttpStatus.NOT_FOUND, "GeneralSettings",
			"api.error.general.settings.not.found");

}

	
	private BigDecimal calculateValue(SingleQuickPay single) {
		BigDecimal value = BigDecimal.ZERO;
		
		if(single.getSinglePrice() == null) {
			value = single.getProducts().stream().map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal :: add);
		}else {
			value = single.getSinglePrice().multiply(BigDecimal.valueOf(single.getSingleQuantity()));
		}
		
		return value.subtract(single.getDiscountValue()).add(single.getAdditionalValue());
	}
	
	private BigDecimal calculateValue(ShopQuickPay shop) {
		BigDecimal value = BigDecimal.ZERO;
		
		if(shop.getProducts() != null) {
			shop.getProducts().stream().map(p -> p.getShopProductVariation().getPrice().multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal :: add);
		}

		
		return value.subtract(shop.getDiscountValue()).add(shop.getAdditionalValue());
	}
	
	private void calculateSalesFunnel(QuickPayPaymentStatus status, DashboardSalesFunnelResponse sales) {
		switch (status) {
		case PAID:
			sales.setFinalizePurchase(sales.getFinalizePurchase().add(BigDecimal.ONE));
			break;
		case WAITINGPAYMENT:
			sales.setAddCart(sales.getAddCart().add(BigDecimal.ONE));
			break;
		default:
			break;
		}
	}
	
	private void calculatePaymentType(PaymentType paymentType, HashMap<PaymentType, DashboardFormPaymentsResponse> hash) {
		if(hash.containsKey(paymentType)) {			
			hash.get(paymentType).getValue().add(BigDecimal.ONE);
		}else {
			hash.put(paymentType, DashboardFormPaymentsResponse.builder().name(paymentType.getName()).value(BigDecimal.ONE).percentage(BigDecimal.ZERO).build());
		}
	}
	
	private void calculateSellingProducts(SingleQuickPay single, HashMap<String, DashboardTopSellingProductsResponse> sellingProducts ) {
		if(single.getSingleQuantity() != null) {
			if(sellingProducts.containsKey(single.getShippingDescription())) {
				sellingProducts.get(single.getShippingDescription()).getQuantity().add(BigDecimal.valueOf(single.getSingleQuantity()));
			}else {
				sellingProducts.put(single.getShippingDescription(), DashboardTopSellingProductsResponse.builder()
						.name(single.getShippingDescription()).quantity(BigDecimal.valueOf(single.getSingleQuantity())).build());
			}
		}else{
			single.getProducts().forEach(p ->{
				if(sellingProducts.containsKey(p.getName())) {
					sellingProducts.get(p.getName()).getQuantity().add(BigDecimal.valueOf(p.getQuantity()));
				}else {
					sellingProducts.put(p.getName(), DashboardTopSellingProductsResponse.builder()
							.name(p.getName()).quantity(BigDecimal.valueOf(p.getQuantity())).build());
				}
			});
			
		}
	}
	
	
	private void calculateSellingProducts(ShopQuickPay shop, HashMap<String, DashboardTopSellingProductsResponse> sellingProducts ) {
		shop.getProducts().forEach(p->{			
			if(sellingProducts.containsKey(p.getShopProductVariation().getName())) {
				sellingProducts.get(p.getShopProductVariation().getName()).getQuantity().add(BigDecimal.valueOf(p.getQuantity()));
			}else {
				sellingProducts.put(p.getShopProductVariation().getName(), DashboardTopSellingProductsResponse.builder()
						.name(p.getShopProductVariation().getName()).quantity(BigDecimal.valueOf(p.getQuantity())).build());
			}
		});
	}
		private void validate(GeneralSettings generalUser, String field, String message) {

			if (generalUser == null) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
			}
		
	}

	
	
	
		
	
	
	

}
