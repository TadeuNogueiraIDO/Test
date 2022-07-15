package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

	Optional<Shop> findByIdo(Ido ido);
	
	@Query("FROM Shop s join s.categories c WHERE :filter is null OR UPPER(s.name) LIKE %:filter% OR UPPER(c.name) LIKE %:filter%")
	List<Shop> findByNameAndByCategoryInIgnoreCase(String filter);
	
//	List<Shop>findByNameLikeIgnoreCaseOrByCategoryLikeIgnoreCase(String filter);
	
	@Query("from Shop s where s.ido.business.user.id = :userId")
	List<Shop> findByUser(Long userId);
	
	
	
	
}
