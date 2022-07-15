package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.SingleQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderSingleResponse;
import br.com.idolink.api.dto.response.SingleQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.SingleQuickPayResponse;

public interface SingleQuickPayService{

	List<SingleQuickPayResponse> listByUser(Long idoId);

	SingleQuickPayResponse findById(Long id);
	
	/**
	 * salva o checkout do pedido
	 * @param idoId
	 * @param profileRequest
	 * @return
	 */
	SingleQuickPayCheckoutResponse createCheckoutSingleQuickPay(Long idoId,
			SingleQuickPayCheckoutRequest profileRequest);

	/**
	 * Cria o pedido baseado no checkout passado por parametro
	 */
	SingleQuickPayResponse updateOrderSingleQuickPay(Long singleQuickPayId, QuickPayOrderRequest request);

	/**
	 * Atualiza o pagamento e o status do pedido para pago e entregue 
	 * @param singleQuickPayId
	 * @param status
	 * @return
	 */
	SingleQuickPayResponse orderPaymentAndSeding(Long singleQuickPayId, QuickPayFinishRequest request);

	/**
	 * Atualiza o pagamento e o status do pedido para pago e aguardando entrega 
	 * @param singleQuickPayId
	 * @param status
	 * @return
	 */
	SingleQuickPayResponse orderPaymentAndWaitingDelivery(Long singleQuickPayId, QuickPayFinishRequest request);

	GenericDetailOrderSingleResponse findByDetailsSingle(Long userId, Long singleQuickPayId);

	GenericDetailOrderSingleResponse findByDetailsSingleByOrder(Long userId, String orderNumber);
    
}