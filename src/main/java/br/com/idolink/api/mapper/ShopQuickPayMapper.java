package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.ShopQuickPayProductResponse;
import br.com.idolink.api.dto.response.ShopQuickPayResponse;
import br.com.idolink.api.model.ShopQuickPay;

@Component
public class ShopQuickPayMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PaymentTypeMapper paymentTypeMapper;
	
	public ShopQuickPayResponse modelToResponse(ShopQuickPay model) {
		ShopQuickPayResponse response = mapper.map(model, ShopQuickPayResponse.class);
		
		if(Objects.nonNull(model.getDataClient()) && Objects.nonNull(model.getDataClient().getClientPhone())) {
			convertPhoneInResponse(response, model.getDataClient().getClientPhone());
		}
		
		if(Objects.nonNull(model.getPaymentType())) {
			response.setPaymentType(paymentTypeMapper.response(model.getPaymentType()));
		}
		
		response.setCreatedIn(model.getDateModel().dt_created);
		if(Objects.nonNull(model.getDateModel().dt_updated)) {
			response.setUpdatedIn(model.getDateModel().dt_updated);
		}else {
			response.setUpdatedIn(model.getDateModel().dt_created);
		}
		loadTotalValue(response);
		
		return response;
	}
	
	public List<ShopQuickPayResponse> modelToResponse(List<ShopQuickPay> model) {
		return model.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
	}
	
	private void convertPhoneInResponse(ShopQuickPayResponse response, String phoneDatabase) {
		
		String[]splitPhone = phoneDatabase.split(";");
		
		if(Objects.nonNull(splitPhone[0])) {
			response.setDialCode(splitPhone[0]);
		}
		
		if(Objects.nonNull(splitPhone[1]) && splitPhone[1].length() > 3) {
			response.setClientPhone(splitPhone[1]);
		}
	}
	
	private void loadTotalValue(ShopQuickPayResponse response) {
		
		BigDecimal subtotal = new BigDecimal(0);

		for (ShopQuickPayProductResponse product : response.getProducts()) {

				BigDecimal priceProduct = Objects.nonNull(product.getProductVariation().getPromoPrice())
						? product.getProductVariation().getPromoPrice()
						: product.getProductVariation().getPrice();

				BigDecimal subTotalProduct = priceProduct.multiply(new BigDecimal(product.getQuantity()));
				subtotal = subTotalProduct.add(subtotal);
		}
			response.setSubTotalValue(subtotal);

		BigDecimal total = new BigDecimal(0);
		total = response.getSubTotalValue().add(response.getAdditionalValue()).subtract(response.getDiscountValue());
		response.setTotalValue(total);
	 }
	
}
