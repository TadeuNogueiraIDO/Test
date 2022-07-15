package br.com.idolink.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.ToolPlanPackageType;

@Repository
public interface UserPlanPackageRepository extends JpaRepository<UserPlanPackage, Long>, IdoPlanPackageFilterRepository{

	@Query("FROM UserPlanPackage p WHERE p.ido.id = :idoId AND p.toolType = :toolType")
	UserPlanPackage findByToolType(Long idoId, ToolPlanPackageType toolType);
	
	@Query("FROM UserPlanPackage p WHERE p.ido.id IN :idoIds AND p.paymentStatus = 'PAID' AND p.expirationDate BETWEEN :startDate AND :endDate")
	List<UserPlanPackage> findByIdoIds(List<Long> idoIds, LocalDate startDate, LocalDate endDate);
	
	@Query("FROM UserPlanPackage p WHERE p.ido.id IN :idoIds AND p.paymentStatus = 'WAITINGPAYMENT'")
	List<UserPlanPackage> findByIdoIds(List<Long> idoIds);
	
	@Query("FROM UserPlanPackage p WHERE p.ido.business.user.id = :userId AND (:idoIds is null or p.ido.id in :idoIds)")
	List<UserPlanPackage> findAllByUserId(Long userId, List<Long> idoIds);
	
	@Query("FROM UserPlanPackage p WHERE p.ido.id = :idoId AND p.id = :planId")
	UserPlanPackage findByIdAndIdoId(Long planId, Long idoId);
	
	@Query("FROM UserPlanPackage p WHERE p.ido.id = :idoId AND p.paymentStatus = 'PAID' AND p.expirationDate >= current_date")
	List<UserPlanPackage> findPlanActiveByIdoIdByToolCodName(Long idoId);
	
}
