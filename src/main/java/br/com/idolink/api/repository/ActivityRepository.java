package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Activity;
import br.com.idolink.api.model.enums.TypeActivity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
	List<Activity> findByTypeActivity(TypeActivity typeActivity);
	
	Page<Activity> findByFilterContainingIgnoreCase(String name, Pageable pageable);

}
