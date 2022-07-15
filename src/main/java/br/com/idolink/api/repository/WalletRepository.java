package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

	@Query("FROM Wallet w WHERE w.user.id = :userId")
	List<Wallet> findAll(Long userId);
}
