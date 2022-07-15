package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.PostOfficeOptionalServiceRequest;
import br.com.idolink.api.dto.request.PostOfficeShippingRequest;
import br.com.idolink.api.dto.request.PostOfficeTypeShippingRequest;
import br.com.idolink.api.dto.response.PostOfficeOptionalServiceResponse;
import br.com.idolink.api.dto.response.PostOfficeShippingResponse;
import br.com.idolink.api.dto.response.PostOfficeTypeShippingResponse;
import br.com.idolink.api.model.PostOfficeShipping;
import br.com.idolink.api.model.enums.PostOfficeOptionalServices;
import br.com.idolink.api.model.enums.PostOfficeTypeShipping;

@Component
public class PostOfficeShippingMapper {

	@Autowired
	private ModelMapper mapper;

	public PostOfficeShippingResponse response(PostOfficeShipping model) {
		PostOfficeShippingResponse response = mapper.map(model, PostOfficeShippingResponse.class);
		convertTypeShippingInResponse(response, model.getTypeShipping());
		convertOptionalServicesInResponse(response, model.getOptionalServices());
		
		return response;
	}

	public List<PostOfficeShippingResponse> response(List<PostOfficeShipping> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public PostOfficeShipping model(PostOfficeShippingRequest request) {
		PostOfficeShipping model = mapper.map(request, PostOfficeShipping.class);
		convertRequestInTypeShipping(request, model);
		convertRequestInOptionalService(request, model);
		
		return model;
	}

	// START TYPESHIPPING CONVERT

	private void convertTypeShippingInResponse(PostOfficeShippingResponse response, String typeShipping) {

		if(Objects.nonNull(typeShipping)) {
			
			String[] splitTypeShipping = typeShipping.split(";");

			PostOfficeTypeShippingResponse typeResponse = new PostOfficeTypeShippingResponse();

			for (String data : splitTypeShipping) {

				if (data.equals(PostOfficeTypeShipping.PAC.toString())) {
					typeResponse.setPac(true);
				}
				if (data.equals(PostOfficeTypeShipping.REG_LETTER.toString())) {
					typeResponse.setRegletter(true);
				}
				if (data.equals(PostOfficeTypeShipping.SEDEX.toString())) {
					typeResponse.setSedex(true);
				}
				if (data.equals(PostOfficeTypeShipping.SEDEX10_ENV.toString())) {
					typeResponse.setSedex10Env(true);
				}
				if (data.equals(PostOfficeTypeShipping.SEDEX10_PAC.toString())) {
					typeResponse.setSedex10Pac(true);
				}
				if (data.equals(PostOfficeTypeShipping.SEDEX12_.toString())) {
					typeResponse.setSedex12(true);
				}
				if (data.equals(PostOfficeTypeShipping.SEDEX_TODAY.toString())) {
					typeResponse.setSedexToday(true);
				}

			}

			response.setTypeShipping(typeResponse);
		}

	}

	private void convertRequestInTypeShipping(PostOfficeShippingRequest request, PostOfficeShipping model) {

		StringBuffer typeShipping = new StringBuffer();

		PostOfficeTypeShippingRequest typeRequest = request.getTypeShipping();

		if (typeRequest.isPac()) {
			typeShipping.append(PostOfficeTypeShipping.PAC.toString()).append(";");
		}

		if (typeRequest.isRegletter()) {
			typeShipping.append(PostOfficeTypeShipping.REG_LETTER.toString()).append(";");
		}

		if (typeRequest.isSedex()) {
			typeShipping.append(PostOfficeTypeShipping.SEDEX.toString()).append(";");
		}

		if (typeRequest.isSedex10Env()) {
			typeShipping.append(PostOfficeTypeShipping.SEDEX10_ENV.toString()).append(";");
		}

		if (typeRequest.isSedex10Pac()) {
			typeShipping.append(PostOfficeTypeShipping.SEDEX10_PAC.toString()).append(";");
		}

		if (typeRequest.isSedex12()) {
			typeShipping.append(PostOfficeTypeShipping.SEDEX12_.toString()).append(";");
		}

		if (typeRequest.isSedexToday()) {
			typeShipping.append(PostOfficeTypeShipping.SEDEX_TODAY.toString()).append(";");
		}

		model.setTypeShipping(typeShipping.toString());

	}
	// END TYPESHIPPING CONVERT
	
	// START OPTIONALSERVICE CONVERT

	private void convertOptionalServicesInResponse(PostOfficeShippingResponse response, String optionalServices) {

		if(Objects.nonNull(optionalServices)) {
			
			String[] splitOptionalService = optionalServices.split(";");

			PostOfficeOptionalServiceResponse optionalResponse = new PostOfficeOptionalServiceResponse();

			for (String data : splitOptionalService) {

				if (data.equals(PostOfficeOptionalServices.DECLARED_VALUE.toString())) {
					optionalResponse.setDeclaredValue(true);
				}
				if (data.equals(PostOfficeOptionalServices.OWN_HAND.toString())) {
					optionalResponse.setOwnHand(true);
				}
				if (data.equals(PostOfficeOptionalServices.RECEIPT_NOTICE.toString())) {
					optionalResponse.setReceiptNotice(true);
				}
			}

			response.setOptionalServices(optionalResponse);
			
		}

	}

	private void convertRequestInOptionalService(PostOfficeShippingRequest request, PostOfficeShipping model) {


		StringBuffer service = new StringBuffer();

		PostOfficeOptionalServiceRequest serviceRequest = request.getOptionalServices();

		if (serviceRequest.isDeclaredValue()) {
			service.append(PostOfficeOptionalServices.DECLARED_VALUE.toString()).append(";");
		}

		if (serviceRequest.isOwnHand()) {
			service.append(PostOfficeOptionalServices.OWN_HAND.toString()).append(";");
		}

		if (serviceRequest.isReceiptNotice()) {
			service.append(PostOfficeOptionalServices.RECEIPT_NOTICE.toString()).append(";");
		}
		
		model.setOptionalServices(service.toString());

	}

	//END OPTIONALSERVICE CONVERT
	
}
