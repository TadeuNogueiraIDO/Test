package br.com.idolink.api.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.idolink.api.repository.IdoPlanPackageFilterRepository;

public class IdoPlanPackageFilterRepositoryImpl implements IdoPlanPackageFilterRepository{

	@Autowired
	private JdbcTemplate template;

	@Override
	public void updateStatusExpired(List<Long> idos) {
		template.update("UPDATE user_plan_package p"
				+ "	SET payment_status = 'EXPIRED' "
				+ "WHERE p.payment_status != 'PAID' AND p.ido_id in "+idos);
		
	}
	
	@Override
	public void updateStatusPaid(List<Long> idos) {
		template.update("UPDATE user_plan_package p"
				+ "	SET payment_status = 'PAID' "
				+ "WHERE p.payment_status != 'PAID' AND p.ido_id in "+idos);
		
	}
	
	
}
