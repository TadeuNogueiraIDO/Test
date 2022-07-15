package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.CategoryRequest;
import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.model.Category;


public interface CategoryService {

	 List <CategoryResponse> list();
    
	 CategoryResponse findById(Long id);

	 CategoryResponse create(CategoryRequest request);

	 CategoryResponse update(Long id, CategoryRequest request);

     void delete(Long id);

	List<Category> findListByName(List<CategoryRequest> list);
	
	List<CategoryResponse> findByFilterContainingIgnoreCase(String name);

	CategoryResponse findByCodName(String name);

	List<Category> findListById(List<CategoryRequest> list);
    
}