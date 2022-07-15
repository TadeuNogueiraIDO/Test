package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ButtonShapeMapper;
import br.com.idolink.api.model.ButtonShape;
import br.com.idolink.api.repository.ButtonShapeRepository;
import br.com.idolink.api.service.ButtonShapeService;

@Service
public class ButtonShapeServiceImpl implements ButtonShapeService {

	@Autowired
	private ButtonShapeRepository repository;

	@Autowired
	private ButtonShapeMapper mapper;

	@Override
	public List<ButtonShapeResponse> list() {

		List<ButtonShape> model = repository.findAll();

		return mapper.response(model);
	}
	
	@Override
	public ButtonShape findByName(String name) {
		return findByName(name, false);
	}
	
	
	@Override
	public ButtonShape findByName(String name, boolean returnDefault) {
		
		ButtonShape shape = repository.findByName(name);
		
		if(Objects.isNull(shape)) {
			
			if(returnDefault) {
				shape = ButtonShape.builder().id(1L).build();
			}else {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Shape", "api.error.appearance.button.shape.not.found");
			}
					
		}
			
		return shape;
	}
}
