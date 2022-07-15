package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.ShopResponse;

public interface UserFollowShopService {
	
	List<ShopResponse> find(Long userId, String name);

	ShopResponse vinculate(Long userId, Long shopId);

	void unlink(Long userId, Long shopId);
}
