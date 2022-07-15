package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ShopRequest;
import br.com.idolink.api.dto.request.UpdateFormatCardShopRequest;
import br.com.idolink.api.dto.response.PersonalShopProfile;
import br.com.idolink.api.dto.response.ShopGenericResponse;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ShopMapper;
import br.com.idolink.api.model.Activity;
import br.com.idolink.api.model.ActivityShop;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ActivityRepository;
import br.com.idolink.api.repository.ActivityShopRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.IdoToolService;
import br.com.idolink.api.service.ShopCategoryService;
import br.com.idolink.api.service.ShopService;

@Service
public class ShopServiceImpl extends GenericToolsServiceImpl implements ShopService {

	@Autowired
	private ShopMapper mapper;

	@Autowired
	private ShopRepository repository;

	@Autowired
	private IdoRepository idoRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityShopRepository activityShopRepository;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private IdoToolService idoToolService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ShopResponse publicFindByIdo(Long idoId) {
		return this.publicFindByIdo(idoId, true);
	}

	@Override
	public ShopResponse publicFindByIdo(Long idoId, boolean validation) {

		Optional<Ido> ido = idoRepository.findById(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<Shop> model = repository.findByIdo(ido.get());

		if (validation) {
			validate(model, "Shop", "api.error.shop.not.found");
		}

		if (model.isPresent()) {
			ShopResponse response = mapper.response(model.get());
			response.setCategories(shopCategoryService.findByShop(model.get().getId()));
			return response;
		}

		return null;
	}
	
	@Override
	@Transactional
	public ShopResponse updateStatus(Long idShop, Boolean status) {
		
		Optional<Shop> shop = repository.findById(idShop);
		
		shop.get().setActivated(status);
		
		return mapper.response(repository.save(shop.get()));
	
	}

	@Override
	public ShopResponse findByIdo(Long idoId) {
		return this.findByIdo(idoId, true);
	}

	@Override
	public List<ShopResponse> findByUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		List<Shop> shops = repository.findByUser(userId);
		return mapper.response(shops);

	}

	@Override
	public ShopResponse findByIdo(Long idoId, boolean validation) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<Shop> model = repository.findByIdo(ido.get());

		if (validation) {
			validate(model, "Shop", "api.error.shop.not.found");
		}

		if (model.isPresent()) {
			ShopResponse response = mapper.response(model.get());
			response.setCategories(shopCategoryService.findByShop(model.get().getId()));
			return response;
		}

		return null;
	}

	@Override
	public ShopResponse findById(Long id) {
		Optional<Shop> model = repository.findById(id);
		validate(model, "Shop", "api.error.shop.not.found");
		ShopResponse response = mapper.response(model.get());
		response.setCategories(shopCategoryService.findByShop(model.get().getId()));
		return response;
	}

	@Override
	@Transactional
	public ShopResponse create(Long idoId, ShopRequest request) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<Activity> activity = activityRepository.findById(request.getActivity().getTypeActivity().getId());
		validate(activity, "Activity", "api.error.activity.not.found");

		ActivityShop activityShop = ActivityShop.builder().activity(activity.get())
				.otherSegment(request.getActivity().getOtherSegment()).build();
		validateOtherSegment(activityShop);

		activityShop = activityShopRepository.save(activityShop);

		Optional<Shop> shop = repository.findByIdo(ido.get());

		if (shop.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Loja", "api.error.shop.conflict");
		}

		Shop model = mapper.model(request);
		model.setIdo(ido.get());
		model.setActivityShop(activityShop);

		model = repository.save(model);

		// idoToolService.saveAssociateTool(ToolCodName.SHOP, ido.get().getId());
		createTools(ToolCodName.SHOP, idoId, model.getId());
		return mapper.response(model);
	}

	@Transactional
	@Override
	public ShopResponse update(Long id, ShopRequest request) {

		@SuppressWarnings("serial")
		Shop model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"Shop", "api.error.shop.not.found") {
		});

		Optional<Activity> activity = activityRepository.findById(request.getActivity().getTypeActivity().getId());
		validate(activity, "Activity", "api.error.activity.not.found");

		ActivityShop activityShop = ActivityShop.builder().activity(activity.get())
				.otherSegment(request.getActivity().getOtherSegment()).build();
		validateOtherSegment(activityShop);

		activityShop = activityShopRepository.save(activityShop);

		// excluindo atividade anterior
		if (Objects.nonNull(model.getActivityShop())) {

			Optional<ActivityShop> activityShopOld = activityShopRepository.findById(model.getActivityShop().getId());

			if (activityShopOld.isPresent()) {
				activityShopRepository.delete(activityShopOld.get());
			}

		}

		BeanUtils.copyProperties(request, model, "id", "dataModel", "ido");
		model.setActivityShop(activityShop);

		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public ShopResponse updateFormatCardShop(Long id, UpdateFormatCardShopRequest request) {

		Optional<Shop> model = repository.findById(id);

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Shop", "api.error.shop.not.found");
		}

		Shop shop = model.get();
		shop.setFormatCardProduct(request.getFormatCardProduct());
		shop.setFormatShowcase(request.getFormatShowcase());
		repository.save(shop);
		return mapper.response(shop);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<Shop> model = repository.findById(id);
		validate(model, "shop", "api.error.shop.not.found");
		try {

			Long idIdo = model.get().getIdo().getId();
			idoToolService.avaliableAssociateIdoTool(ToolCodName.SHOP, idIdo);
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Shop", "api.error.shop.conflict");
		}

	}

	@Override
	public List<ShopResponse> findAll() {
		List<Shop> model = repository.findAll();
		return mapper.response(model);
	}

	@Override
	public List<ShopGenericResponse> findAllShopProfile(String filter) {
       
		filter = Objects.nonNull(filter)?filter.toUpperCase():filter;
	
		List<Shop> model = repository.findByNameAndByCategoryInIgnoreCase(filter);
		for(Shop correcao : model) {
			if(correcao.getIdo() == null) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "shop", "api.error.shop.not.found");
			}
			
		}
	
		return model.stream().map(s -> mapper.responseEspecific(s)).collect(Collectors.toList());
	}

	@Override
	public PersonalShopProfile findProfileShop(Long shopId) {
		Optional<Shop> model = repository.findById(shopId);
		validate(model, "shop", "api.error.shop.not.found");
		return mapper.modelToPersonalShopProfile(model.get());
	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	private void validateOtherSegment(ActivityShop activityShop) {

		if (activityShop.getActivity().getHasOtherSegment()
				&& (Objects.isNull(activityShop.getOtherSegment()) || activityShop.getOtherSegment().isBlank())) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Atividade",
					"api.error.activity.type.conflict");
		}

		if (!activityShop.getActivity().getHasOtherSegment()) {
			activityShop.setOtherSegment(null);
		}
	}

}
