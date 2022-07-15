package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.model.ButtonShape;

public interface ButtonShapeService {

	List<ButtonShapeResponse> list();

	ButtonShape findByName(String name);

	ButtonShape findByName(String name, boolean returnDefault);

}
