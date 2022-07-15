package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ShopMapper;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.UserFollowShop;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.UserFollowShopRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.UserFollowShopService;

@Service
public class UserFollowShopServiceImpl implements UserFollowShopService{

	@Autowired
	private UserFollowShopRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopMapper shopMapper;
	
	@Override
	public List<ShopResponse> find(Long userId, String name) {
		List<UserFollowShop> models;
		if(StringUtils.isBlank(name)) {
			models = repository.findAllByUserId(userId);
		}else {
			models = repository.findAllByUserIdAndName(userId, name);
		}
		
		return models.stream()
				.map(p -> shopMapper.response(p.getShop()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ShopResponse vinculate(Long userId, Long shopId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"user", "Usuario não encontrado!"));
		Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"shop", "Loja não encontrada!"));
		
		if(repository.existsByUserIdAndShopId(userId, shopId)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"userFollowShop", "api.error.user.follow.exists");
		}
		UserFollowShop model = repository.save(UserFollowShop.builder().shop(shop).user(user).build());
		
		return shopMapper.response(model.getShop());
	}

	@Override
	@Transactional
	public void unlink(Long userId, Long shopId) {
		UserFollowShop follow = repository.findByUserIdAndShopId(userId, shopId);
		if(follow != null) {
			try {
				repository.delete(follow);
			}catch(Exception e) {
				e.getStackTrace();
			}
		}else{
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"userFollowShop", "api.error.user.follow.not.exists");
		}
	}

}
