package br.com.idolink.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.response.BusinessHourResponse;

public interface BusinessHourService {

	Page<BusinessHourResponse> list(Pageable pageable);

	BusinessHourResponse findByIdo(Long id);
	
	BusinessHourResponse publicFindByIdo(Long idoId, Boolean validation);	

	BusinessHourResponse findById(Long id);

	BusinessHourResponse create(Long idIdo, BusinessHourRequest request);

	BusinessHourResponse update(Long id, BusinessHourRequest request);

	void delete(Long id);

	BusinessHourResponse findByIdo(Long idoId, Boolean validation);

}
