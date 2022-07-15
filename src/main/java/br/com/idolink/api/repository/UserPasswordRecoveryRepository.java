package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.UserPasswordRecovery;

public interface UserPasswordRecoveryRepository extends JpaRepository<UserPasswordRecovery, Long>{

	UserPasswordRecovery findByCode(String code);
	
	@Query("from UserPasswordRecovery r where (r.used = false and r.user.id = :user)  or (r.used = false and r.user.id = :user and r.expirationDate < CURRENT_TIMESTAMP)")
	List<UserPasswordRecovery> findUnusedCode(Long user);
}
