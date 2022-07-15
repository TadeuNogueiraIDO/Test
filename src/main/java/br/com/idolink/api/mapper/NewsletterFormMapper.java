package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.model.NewsletterForm;

@Component
public class NewsletterFormMapper {

	@Autowired
	private ModelMapper mapper;

	public NewsletterFormResponse response(NewsletterForm model) {
		return mapper.map(model, NewsletterFormResponse.class);
	}

	public List<NewsletterFormResponse> response(List<NewsletterForm> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public NewsletterForm model(NewsletterFormRequest request) {
		return mapper.map(request, NewsletterForm.class);
	}

}
