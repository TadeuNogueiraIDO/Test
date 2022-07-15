package br.com.idolink.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.response.CountryResponse;

public interface CountryService {

	Page<CountryResponse> list(Pageable pageable);

	List<CountryResponse> listByBraEuaCad();
}
