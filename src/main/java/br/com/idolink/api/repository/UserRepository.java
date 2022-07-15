package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String Email);
	
//	Optional<User> findByPassword(String password);
	
	@Query("select i.business.user from Ido i where i.id = :ido")
	Optional<User> findByIdo(Long ido);
	
	@Query(value = "select distinct (uuid) FROM \"user\" u where u.id = :idUser", nativeQuery = true)
	String findByUuidUser(Long idUser);
}
