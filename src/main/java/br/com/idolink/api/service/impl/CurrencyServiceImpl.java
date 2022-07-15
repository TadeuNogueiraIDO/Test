package br.com.idolink.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.CurrencyResponse;
import br.com.idolink.api.mapper.CurrencyMapper;
import br.com.idolink.api.model.Currency;
import br.com.idolink.api.repository.ToolCoinRepository;
import br.com.idolink.api.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private ToolCoinRepository repository;

	@Autowired
	private CurrencyMapper mapper;

	@Override
	public List<CurrencyResponse> list() {

		List<Currency> model = repository.findAll();

		return mapper.response(model);
	}
}
