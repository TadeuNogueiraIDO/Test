package br.com.idolink.api.repository;

import java.util.List;

import br.com.idolink.api.model.enums.IdoToolStatus;

public interface IdoToolFilterRepository {
	void updateStatus(List<Long> idos, IdoToolStatus status);
}
