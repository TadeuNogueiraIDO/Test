package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.BankResponse;
import br.com.idolink.api.mapper.BankMapper;
import br.com.idolink.api.repository.BankRepository;
import br.com.idolink.api.service.BankService;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRepository repository;
	
	@Autowired
	private BankMapper mapper;
	
	@Override
	public List<BankResponse> findAllBanks() {
		return repository.findAll().stream().map(b -> mapper.modelToResponse(b)).collect(Collectors.toList());
	}

}
