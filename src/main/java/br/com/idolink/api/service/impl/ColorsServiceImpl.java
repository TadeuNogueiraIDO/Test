package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ColorsMapper;
import br.com.idolink.api.model.Colors;
import br.com.idolink.api.repository.ColorsRepository;
import br.com.idolink.api.service.ColorsService;

@Service
public class ColorsServiceImpl implements ColorsService {

	@Autowired
	private ColorsRepository repository;

	@Autowired
	private ColorsMapper mapper;

	@Override
	public List<ColorsResponse> list() {

		List<Colors> model = repository.findAll();
		return mapper.response(model);
	}
	
	@Override
	public Colors findByHexadecimalCode(String hexa) {
		return findByHexadecimalCode(hexa, false);
	}
	
	
	@Override
	public Colors findByHexadecimalCode(String hexa, boolean returnDefault) {
		
		Colors color = repository.findByHexadecimalCode(hexa);
		
		if(Objects.isNull(color)) {
			
			if(returnDefault) {
				color = Colors.builder().id(1L).build();
			}else {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Cor", "api.error.color.not.found");
			}
					
		}
			
		return color;
	}
}
