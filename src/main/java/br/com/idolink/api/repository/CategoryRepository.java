package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByCodName(String name); 
	
	List<Category> findTop20ByFilterContainingIgnoreCase(String name);
	
	List<Category> findTop20ByOrderByFilterAsc();
	
	
}
