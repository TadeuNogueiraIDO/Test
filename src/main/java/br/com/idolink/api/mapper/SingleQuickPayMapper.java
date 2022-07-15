package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.SingleQuickPayProductResponse;
import br.com.idolink.api.dto.response.SingleQuickPayResponse;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.QuickPayType;

@Component
public class SingleQuickPayMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PaymentTypeMapper paymentTypeMapper;
	
	public SingleQuickPayResponse modelToResponse(SingleQuickPay model) {
		SingleQuickPayResponse response = mapper.map(model, SingleQuickPayResponse.class);
		
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
	
	public List<SingleQuickPayResponse> modelToResponse(List<SingleQuickPay> model) {
		return model.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
	}
	
	private void convertPhoneInResponse(SingleQuickPayResponse response, String phoneDatabase) {
		
		String[]splitPhone = phoneDatabase.split(";");
		
		if(Objects.nonNull(splitPhone) && splitPhone.length > 0) {
			response.setDialCode((Objects.nonNull(splitPhone[0]) && !splitPhone[0].isBlank()) ? splitPhone[0] : null);
			response.setClientPhone((Objects.nonNull(splitPhone[1]) && !splitPhone[1].isBlank()) ? splitPhone[1] : null);
		}else {
			response.setDialCode(null);
			response.setClientPhone(null);
		}
	}
	
	private void loadTotalValue(SingleQuickPayResponse response) {
		BigDecimal subtotal = new BigDecimal(0);
		
		if(response.getType().equals(QuickPayType.SINGLE)) {
			subtotal = response.getSinglePrice().multiply(new BigDecimal(response.getSingleQuantity()));
		}else {
			for (SingleQuickPayProductResponse product : response.getProducts()) {
				BigDecimal subTotalProduct = product.getPrice().multiply(new BigDecimal(product.getQuantity()));
				subtotal = subTotalProduct.add(subtotal);
			}
		}
		response.setSubTotalValue(subtotal);
		
		
		BigDecimal total = new BigDecimal(0);
		total = response.getSubTotalValue().add(response.getAdditionalValue()).subtract(response.getDiscountValue());
		
		response.setTotalValue(total);
	}
	
}
