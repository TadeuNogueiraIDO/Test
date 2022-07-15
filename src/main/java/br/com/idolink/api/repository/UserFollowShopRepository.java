package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.UserFollowShop;

@Repository
public interface UserFollowShopRepository extends JpaRepository<UserFollowShop, Long>{

	@Query("FROM UserFollowShop u WHERE u.user.id = :userId")
	List<UserFollowShop>findAllByUserId(Long userId);
	
	Boolean existsByUserIdAndShopId(Long userId, Long shopId);
	
	@Query("FROM UserFollowShop u WHERE u.user.id = :userId AND u.shop.name LIKE %:name%")
	List<UserFollowShop>findAllByUserIdAndName(Long userId, String name);
	
	UserFollowShop findByUserIdAndShopId(Long userId, Long shopId);
}
