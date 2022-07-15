package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>{
	
	List<Faq> findByConfigFaq(ConfigFaq configFaq);
}
