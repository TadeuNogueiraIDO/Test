package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.BannerPublicity;

@Repository
public interface BannerPublicityRepository extends JpaRepository<BannerPublicity, Long> {
	
	
	Optional<BannerPublicity> findTop1ByOrderByTitle();
}
