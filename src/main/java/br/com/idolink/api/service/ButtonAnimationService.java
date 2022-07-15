package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ButtonAnimationRequest;
import br.com.idolink.api.dto.response.ButtonAnimationResponse;
import br.com.idolink.api.model.ButtonAnimation;


public interface ButtonAnimationService {

	 ButtonAnimationResponse findById(Long id);

	 ButtonAnimationResponse create(ButtonAnimationRequest request);

	 ButtonAnimationResponse update(Long id, ButtonAnimationRequest request);

     void delete(Long id);

	List<ButtonAnimation> findListById(List<ButtonAnimationRequest> buttonList);

	   
}