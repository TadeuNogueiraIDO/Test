package br.com.idolink.api.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.repository.IdoToolFilterRepository;

public class IdoToolFilterRepositoryImpl implements IdoToolFilterRepository{
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public void updateStatus(List<Long> idos, IdoToolStatus status) {
		template.update("UPDATE ido_tools i"
				+ "	SET status = '"+status
				+ "' WHERE i.ido_id in "+idos);
		
	}


}
