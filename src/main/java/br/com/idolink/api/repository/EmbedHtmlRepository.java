package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.Ido;

@Repository
public interface EmbedHtmlRepository extends JpaRepository<EmbedHtml, Long> {

	List<EmbedHtml> findByIdo(Ido ido);
	
	@Query("select count(i.id) from EmbedHtml i where i.ido.id = ?1")
	Long  countByIdo(Long id);
		
}
