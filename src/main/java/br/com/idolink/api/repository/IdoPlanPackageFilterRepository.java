package br.com.idolink.api.repository;

import java.util.List;

public interface IdoPlanPackageFilterRepository {
	void updateStatusExpired(List<Long> idos);
	
	void updateStatusPaid(List<Long> idos);
}
