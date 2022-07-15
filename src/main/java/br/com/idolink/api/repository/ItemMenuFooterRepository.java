package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ItemMenuFooter;

@Repository
public interface ItemMenuFooterRepository extends JpaRepository<ItemMenuFooter, Long>{
	
	@Query("FROM ItemMenuFooter i WHERE i.menuFooter.id = :menuId AND i.id = :id")
	List<ItemMenuFooter> findByMenuFooter(Long id, Long menuId);
	
	boolean existsById(Long id);
}
