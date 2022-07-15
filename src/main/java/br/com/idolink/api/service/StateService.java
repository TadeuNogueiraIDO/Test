package br.com.idolink.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.response.StateResponse;

public interface StateService {

	public Page<StateResponse> list(Pageable pageable);
}
