package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ProfileIdoLink;
import br.com.idolink.api.model.enums.ProfileIdoCodName;

@Repository
public interface ProfileIdoLinkRepository  extends  JpaRepository< ProfileIdoLink, Long> {
	
	ProfileIdoLink findByCodName(ProfileIdoCodName codName);
	
}
