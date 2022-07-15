package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.NewsletterFormResponse;

public interface NewsletterFormService {

	NewsletterFormResponse findById(Long id);

	NewsletterFormResponse create(Long configContactId, NewsletterFormRequest request);

	List<NewsletterFormResponse> findByConfig(Long config);
	
	void delete(Long id);

}
