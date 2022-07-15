package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.DashboardFormPaymentsResponse;
import br.com.idolink.api.dto.response.DashboardOrderResponse;
import br.com.idolink.api.dto.response.DashboardSalesFunnelResponse;
import br.com.idolink.api.dto.response.DashboardSalesResponse;
import br.com.idolink.api.dto.response.DashboardTopSellingProductsResponse;
import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class ShopDashboardMappper {
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public ShopDashboardResponse convertToResponse(BigDecimal totalValues, BigDecimal quantity,
			DashboardSalesFunnelResponse salesFunnel, HashMap<PaymentType, 
			DashboardFormPaymentsResponse> paymentTypes, HashMap<String, DashboardTopSellingProductsResponse> sellingProducts) {
		ShopDashboardResponse response = new ShopDashboardResponse();
		
		DashboardOrderResponse order = DashboardOrderResponse.builder()
				.quantity(quantity)
				.averageValue(calculateAverageValue(totalValues, quantity)).build();
		
		DashboardSalesResponse sales = DashboardSalesResponse.builder()
				.invoicing(totalValues)
				.formPayments(calculateFormPayment(paymentTypes))
				.salesFunel(calculatePercentualSalesFunel(salesFunnel))
				.topSellingProducts(calculateTopSellingProducts(sellingProducts)).build();
		
		response.setOrder(order);
		response.setSales(sales);
	
		
		
		return response;
	}
	
	private DashboardSalesFunnelResponse calculatePercentualSalesFunel(DashboardSalesFunnelResponse salesFunnel) {
		
		BigDecimal totalOrders = salesFunnel.getAddCart().add(salesFunnel.getCreateOrder()).add(salesFunnel.getFinalizePurchase()).add(salesFunnel.getGoToCheckout());
		
		DashboardSalesFunnelResponse sales = DashboardSalesFunnelResponse.builder()
				.addCart(valideDivide(salesFunnel.getAddCart().multiply(BigDecimal.valueOf(100)), totalOrders))
				.createOrder(valideDivide(salesFunnel.getCreateOrder().multiply(BigDecimal.valueOf(100)), totalOrders))
				.finalizePurchase(valideDivide(salesFunnel.getFinalizePurchase().multiply(BigDecimal.valueOf(100)), totalOrders))
				.goToCheckout(valideDivide(salesFunnel.getGoToCheckout().multiply(BigDecimal.valueOf(100)),totalOrders)).build();
		return sales;
	}
	
	private BigDecimal valideDivide(BigDecimal value, BigDecimal totalOrders) {
		if(value != null && value != BigDecimal.ZERO) {
			value = value.divide(totalOrders, 2, RoundingMode.HALF_UP);
		}else {
			value = BigDecimal.ZERO;
		}
		return value;
	}
	
	private BigDecimal calculateAverageValue(BigDecimal totalValues, BigDecimal quantity) {
		if(quantity.compareTo(BigDecimal.ZERO)==0) {
            return BigDecimal.ZERO;
        }
		return totalValues.divide(quantity, 2,RoundingMode.HALF_UP);
	}
	
	private List<DashboardFormPaymentsResponse> calculateFormPayment(HashMap<PaymentType, 
			DashboardFormPaymentsResponse> paymentTypes){
		
		List<DashboardFormPaymentsResponse> response = paymentTypes.values().stream()
				  .sorted(Comparator.comparing(DashboardFormPaymentsResponse::getValue).reversed())
				  .collect(Collectors.toList());
		
		BigDecimal totalQuantity = response.stream().map(f -> f.getValue()).reduce(BigDecimal.ZERO, BigDecimal :: add);
		
		response.forEach(payment -> {
			payment.setPercentage(payment.getValue().multiply(BigDecimal.valueOf(100)).divide(totalQuantity, 2 , RoundingMode.HALF_UP));
			payment.setName(messagePropertieService.getMessageAttribute(payment.getName()));
		});
		
		
		return response;
	}
	
	private List<DashboardTopSellingProductsResponse> calculateTopSellingProducts(HashMap<String, 
			DashboardTopSellingProductsResponse> sellingProducts){
		List<DashboardTopSellingProductsResponse> response = sellingProducts.values().stream()
				  .sorted(Comparator.comparing(DashboardTopSellingProductsResponse::getQuantity).reversed())
				  .collect(Collectors.toList());
		
		response = response.subList(0, response.size()<5?response.size():5);
		
		BigDecimal totalQuantity = response.stream().map(r -> r.getQuantity()).reduce(BigDecimal.ZERO, BigDecimal :: add);
		
		response.forEach(topSelling -> {
			topSelling.setPercentage(topSelling.getQuantity().multiply(BigDecimal.valueOf(100)).divide(totalQuantity, 2 , RoundingMode.HALF_UP));
		});
		
		
		return response;
	}
}
