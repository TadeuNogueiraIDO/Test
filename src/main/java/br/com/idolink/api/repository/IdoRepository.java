package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;

@Repository
public interface IdoRepository extends JpaRepository<Ido, Long>, IdoFilterRepository {

	@Query("from Ido i where i.domain = ?1 and i.idoPublished is null")
	Optional<Ido> findByDomain(String domain);
		
	@Query("from Ido i where i.domain = :domain and i.id != :idoUpdate and i.idoPublished is null")
	Optional<Ido> findByDomain(String domain, Long idoUpdate);
		
	@Query("from Ido i where i.domain = :domain and i.status = '0'")
	Optional<Ido> findDraftByDomain(String domain);
	
	@Query("FROM Ido i WHERE i.id IN :idos")
	List<Ido> findByIds(List<Long> idos);
	
	@Query("select i.id from Ido i where i.business.user.id = :userId and i.idoPublished is null order by i.id desc")
    List<Long> findIdByUser(Long userId);

	/**
	 * Busca o rascunho do Ido ja publicado
	 * @param idoPublished
	 * @return
	 */
	Optional<Ido> findByIdoPublished(Long idoPublished);
	
	@Query("from Ido i where i.business.id = :businessId and i.idoPublished is null order by i.id desc")
	List<Ido> findByBusiness(@Param("businessId") Long businessId);
	
	
	@Query("select i.id from Ido i where i.business.id = :businessId and i.idoPublished is null order by i.id desc")
	List<Long> findIdByBusiness(Long businessId);
			
}
