package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.PushNotificationType;

@Repository
public interface PushNotificationTypeRepository extends JpaRepository<PushNotificationType, Long> {

}
