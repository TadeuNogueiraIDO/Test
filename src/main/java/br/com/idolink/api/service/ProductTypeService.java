package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.ProductTypeResponse;

public interface ProductTypeService {

	List<ProductTypeResponse> list();
}
