package br.com.idolink.api.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.User;


@Repository
public interface NotificationRepository extends  JpaRepository<Notification, Long> {
		
	void deleteByUser(User user);
	 
	@Query(value = "SELECT * FROM public.notification where user_id = :userId and type_notification =:type ORDER BY creation_date desc", nativeQuery = true)
	List<Notification> findByUserIdByDateCreationByType(Long userId, String type);
	
	@Query( value = "SELECT * FROM public.notification where user_id = :userId ORDER BY creation_date desc ", nativeQuery = true)
	List<Notification> findByUserIdByDateCreation(Long userId);
	
	
	@Query(value = "select distinct (creation_date) FROM notification i \r\n"
			+ "where i.user_id = :userId \r\n"
			+ "order by creation_date desc", nativeQuery = true)
	List<Timestamp> findByDatebyUser(Long userId);
	
	@Query(value = "FROM Notification n"
			+ " where n.user.id = :userId"
			+ " order by creation_date desc")
	List<Notification> findbyUser(Long userId);
	
	@Query(value= "select * from notification n \r\n"
			+ "where creation_date = :date and n.user_id = :userId", nativeQuery = true)
	List<Notification> findByNotificationByDate(Timestamp date, Long userId);
	
	@Query(value = "SELECT * FROM public.notification where user_id = :idUser and  id = :idNotification \r\n"
			+ "ORDER BY creation_date desc", nativeQuery = true)
	Notification findByUser (Long idNotification, Long idUser);
	
	@Query(value = "SELECT * FROM public.notification where user_id = :idUser \r\n"
			+ "ORDER BY creation_date desc", nativeQuery = true)
	List<Notification> findByAllNotificationByUser(Long idUser);
	
	@Query("SELECT n FROM Notification n where n.user.id = :idUser")
	List<Notification> findByAllNotificationByUserLogged(Long idUser);
	
	
	
	}
