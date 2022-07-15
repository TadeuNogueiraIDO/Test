package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.UserValidateAccount;

public interface UserValidateAccountRepository extends JpaRepository<UserValidateAccount, Long>{

	UserValidateAccount findByCode(String token);

	@Query("from UserValidateAccount r where (r.used = false and r.user.id = :user)  or (r.used = false and r.user.id = :user and r.expirationDate < CURRENT_TIMESTAMP)")
	List<UserValidateAccount> findUnusedCode(Long user);
}

