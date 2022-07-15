package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.CategoryRequest;
import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.CategoryMapper;
import br.com.idolink.api.model.Category;
import br.com.idolink.api.repository.CategoryRepository;
import br.com.idolink.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryMapper mapper;

	@Override
	public List<CategoryResponse> list() {

		List<Category> model = repository.findAll();
		return mapper.response(model);
	}

	@Override
	public CategoryResponse findById(Long id) {
		Optional<Category> model = repository.findById(id);
		validate(model, "Category", "api.error.category.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public CategoryResponse create(CategoryRequest request) {
		Category model = mapper.model(request);
		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public CategoryResponse update(Long id, CategoryRequest request) {

		@SuppressWarnings("serial")
		Category model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Category",  "api.rerror.category.not.found") {
				});

		BeanUtils.copyProperties(request, model, "id", "dateModel");
		return mapper.response(repository.save(model));

	}

	@Transactional
	public void delete(Long id) {

		Optional<Category> model = repository.findById(id);
		validate(model,"Category", "api.error.category.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Category","api.error.category.conflict");
		}

	}
	
	@Override
	public CategoryResponse findByCodName(String name) {
		Optional<Category> model = repository.findByCodName(name);
		validate(model, "Category", "api.error.category.not.found");
		return mapper.response(model.get());
	}
	
	
	@Override
	public List<Category> findListByName(List<CategoryRequest> list) {
		
		List<Category> categoryList = new ArrayList<>();
		
		for (CategoryRequest categoryRequest : list) {
			
			Optional<Category> model = repository.findByCodName(categoryRequest.getCodName());
			validate(model, "Category", "api.error.category.not.found");
			categoryList.add(model.get());
		}
					
		return categoryList;
		
	}
	
	@Override
	public List<Category> findListById(List<CategoryRequest> list) {
		
		List<Category> categoryList = new ArrayList<>();
		
		for (CategoryRequest categoryRequest : list) {
			
			Optional<Category> model = repository.findById(categoryRequest.getId());
			validate(model, "Category", "api.error.category.not.found");
			categoryList.add(model.get());
		}
					
		return categoryList;
		
	}
	
	
	
	
	@Override
	public List<CategoryResponse> findByFilterContainingIgnoreCase(String name) {
     	
		List<Category> model = new ArrayList<>();
		
		if(name!= null && !name.isBlank()) {
			model = repository.findTop20ByFilterContainingIgnoreCase(name);
		}else {
			model = repository.findTop20ByOrderByFilterAsc();
		}
		
		return mapper.response(model);
	}
	
	private void validate(Optional<Category> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

}
