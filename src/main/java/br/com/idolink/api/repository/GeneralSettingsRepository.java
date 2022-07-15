package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.User;

@Repository
public interface GeneralSettingsRepository extends JpaRepository<GeneralSettings, Long> {
	
	Optional<GeneralSettings> findByUser(User user);
	
	@Query
	GeneralSettings findByUserId(Long userId);
}
