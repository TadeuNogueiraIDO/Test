package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.CategoryRequest;
import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.model.Category;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class CategoryMapper{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public CategoryResponse response(Category model) {
		CategoryResponse response = mapper.map(model, CategoryResponse.class);
		response.setName(messagePropertieService.getMessageAttribute(model.getName()));
		return response; 		
	}

	public List<CategoryResponse> response(List<Category> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public Category model(CategoryRequest request) {
		return mapper.map(request, Category.class);
	}

	/**
	 * converts a CategoryRequest List in Category List
	 * @param model
	 * @return
	 */
	public List<Category> modelList(List<CategoryRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());		
	}
			
}
