package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.ShopQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderResponse;
import br.com.idolink.api.dto.response.ShopQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.ShopQuickPayResponse;

public interface ShopQuickPayService{

	List<ShopQuickPayResponse> listByUser(Long userId);

	ShopQuickPayResponse findById(Long id);

	ShopQuickPayCheckoutResponse createCheckoutShopQuickPay(Long idoId, ShopQuickPayCheckoutRequest profileRequest);

	ShopQuickPayResponse updateOrderShopQuickPay(Long singleQuickPayId, QuickPayOrderRequest request);

	ShopQuickPayResponse orderPaymentAndSeding(Long singleQuickPayId, QuickPayFinishRequest request);

	ShopQuickPayResponse orderPaymentAndWaitingDelivery(Long singleQuickPayId, QuickPayFinishRequest request);

	GenericDetailOrderResponse findByDetailsShop(Long userId, Long shopQuickPayId);

	GenericDetailOrderResponse findByDetailsShopByNumberOrder(Long userId, String orderNumber);
    
}