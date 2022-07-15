package br.com.idolink.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.ProductTypeResponse;
import br.com.idolink.api.mapper.ProductTypeMapper;
import br.com.idolink.api.model.ProductType;
import br.com.idolink.api.repository.ProductTypeRepository;
import br.com.idolink.api.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	
	@Autowired
	private ProductTypeMapper mapper;
	
	@Autowired
	private ProductTypeRepository repository;
	
	@Override
	public List<ProductTypeResponse> list() {
		List<ProductType> model = repository.findAll();
		return mapper.response(model);
	}

}
