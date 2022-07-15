package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.NotificationSetting;

@Repository
public interface NotificationSettingRepository extends  JpaRepository<NotificationSetting, Long> {

	
	NotificationSetting findByUserId(Long userId);
	
	@Query("SELECT n FROM NotificationSetting n WHERE n.user.id =:id")
	NotificationSetting  findBySettingUserId (@Param("id") Long id);
	
}
