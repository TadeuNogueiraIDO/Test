package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.NewsletterForm;

@Repository
public interface NewsletterFormRepository extends JpaRepository<NewsletterForm, Long>{

	
	List<NewsletterForm> findByConfigNewsletterForm(ConfigNewsletterForm config);
}
