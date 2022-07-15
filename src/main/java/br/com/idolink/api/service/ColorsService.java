package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.model.Colors;

public interface ColorsService {

	List<ColorsResponse> list();

	Colors findByHexadecimalCode(String hexa);

	Colors findByHexadecimalCode(String hexa, boolean returnDefault);
}
