package br.com.idolink.api.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ItemMenuFooterRequest;
import br.com.idolink.api.dto.request.MenuFooterRequest;
import br.com.idolink.api.dto.request.UpdateMenuFooterRequest;
import br.com.idolink.api.dto.response.ItemMenuFooterResponse;
import br.com.idolink.api.dto.response.MenuFooterResponse;
import br.com.idolink.api.dto.response.ToolsIdoReponse;
import br.com.idolink.api.dto.response.UpdateMenuFooterResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.IdoToolMapper;
import br.com.idolink.api.mapper.MenuFooterMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.model.ItemMenuFooter;
import br.com.idolink.api.model.MenuFooter;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.MenuFooterItem;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.IdoToolRepository;
import br.com.idolink.api.repository.ItemMenuFooterRepository;
import br.com.idolink.api.repository.MenuFooterRepository;
import br.com.idolink.api.repository.ShopCategoryRepository;
import br.com.idolink.api.service.MenuFooterService;

@Service
public class MenuFooterServiceImpl extends GenericToolsServiceImpl implements MenuFooterService {

	@Autowired
	private MenuFooterMapper mapper;

	@Autowired
	private IdoToolMapper idoToolMapper;

	@Autowired
	private MenuFooterRepository repository;

	@Autowired
	private IdoToolRepository idoToolRepository;

	@Autowired
	private ItemMenuFooterRepository itemRepository;

	@Autowired
	private IdoRepository idoRepository;

	@Autowired
	private ShopCategoryRepository categoryRepository;

	@Override
	public MenuFooterResponse publicFindByIdo(Long idoId, boolean validation) {

		Optional<Ido> ido = idoRepository.findById(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		Optional<MenuFooter> model = repository.findByIdo(idoId);
		if(model.isEmpty()) {
			if(validation) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Menu e Rodapé", "api.error.menu.footer.not.found");
			} else {
				return null;
			}
		}
		
		return mapper.response(model.get());
	}

	@Override
	public MenuFooterResponse publicFindByIdo(Long idoid) {
		return this.publicFindByIdo(idoid, true);
	}

	@Override
	public MenuFooterResponse findByIdo(Long idoId, boolean validation) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "O Ido informado não foi encontrado!");
		}

		Optional<MenuFooter> model = repository.findByIdo(idoId);
		if(model.isEmpty()) {
			if(validation) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Menu e Rodapé", "api.error.menu.footer.not.found");
			} else {
				return null;
			}
		}
		
		return mapper.response(model.get());
	}

	@Override
	public MenuFooterResponse findByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<MenuFooter> model = repository.findByIdo(idoId);
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Menu e Rodapé", "api.error.menu.footer.not.found");
		}
		return mapper.response(model.get());
	}

	@Override
	public List<ToolsIdoReponse> findToolByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "Ido não encontrado!");
		}

		List<IdoTool> idoTools = idoToolRepository.findToolByIdoAndStatus(ido.get(), IdoToolStatus.INSTALLED).stream()
				.filter(value -> value.getTool().getId() != 15L).collect(Collectors.toList());

		if (idoTools.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ferramenta", "api.error.tool.instaler.not.found");
		}

		return idoToolMapper.modelResponse(idoTools);
	}

	@Override
	public MenuFooterResponse findById(Long id) {
		Optional<MenuFooter> model = repository.findById(id);
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "MenuFooter", "api.error.menu.footer.not.found");
		}
		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public MenuFooterResponse create(MenuFooterRequest request, Long idoId) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<MenuFooter> menuFooter = repository.findByIdo(ido.get());

		if (menuFooter.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "MenuFooter", "api.error.menu.footer.conflict");
		}

		MenuFooter model = mapper.requestModel(request, ido.get());

		for (ItemMenuFooterRequest item : request.getItens()) {

			if (item.getTypeItem().equals(MenuFooterItem.TOOL)) {
				Optional<IdoTool> idoTool = idoToolRepository.findById(item.getToolId());
				if (idoTool.isEmpty()) {
					throw new BaseFullException(HttpStatus.NOT_FOUND, "Tools", "api.error.menu.footer.tools.not.found");
				}
				if (ido.get().getIdoTools().isEmpty()) {
					throw new BaseFullException(HttpStatus.NOT_FOUND, "Ferramenta", "api.error.tool.not.exist");
				}

				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(idoTool.get()).menuFooter(model).shopCategory(null).externalLink(null).build();

				model.getItens().add(itemMenu);

			}

			if (item.getTypeItem().equals(MenuFooterItem.CATEGORY_SHOP)) {
				Optional<ShopCategory> category = categoryRepository.findById(item.getShopCategoryId());
				if (category.isEmpty()) {
					throw new BaseFullException(HttpStatus.NOT_FOUND, "Category",
							"api.error.menu.footer.category.not.found");
				}
				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(null).menuFooter(model).shopCategory(category.get()).externalLink(null).build();

				model.getItens().add(itemMenu);
			}

			if (item.getTypeItem().equals(MenuFooterItem.EXTERNAL_LINK)) {
				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(null).menuFooter(model).shopCategory(null)
						.externalLink(validateLink(item.getExternalLink())).build();

				model.getItens().add(itemMenu);
			}
		}

		model = repository.save(model);

		super.createTools(ToolCodName.MENURODAPE, idoId, model.getId());

		MenuFooterResponse response = mapper.response(model);

		return response;
	}

	@Override
	@Transactional
	public MenuFooterResponse update(MenuFooterRequest request, Long menuId) {

		MenuFooter model = validateIdExists(menuId);

		if (!model.getItens().isEmpty()) {
			model.getItens().clear();
		}
		if (model.getItens() != null && !model.getItens().isEmpty()) {
			model.getItens().clear();
		}

		BeanUtils.copyProperties(request, model, "id", "dateModel", "ido", "itens");

		for (ItemMenuFooterRequest item : request.getItens()) {

			if (item.getTypeItem().equals(MenuFooterItem.TOOL)) {
				Optional<IdoTool> idoTool = idoToolRepository.findById(item.getToolId());
				if (idoTool.isEmpty()) {
					throw new BaseFullException(HttpStatus.NOT_FOUND, "Tools", "api.error.menu.footer.tools.not.found");
				}

				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(idoTool.get()).menuFooter(model).shopCategory(null).externalLink(null).build();

				model.getItens().add(itemMenu);

			}

			if (item.getTypeItem().equals(MenuFooterItem.CATEGORY_SHOP)) {
				Optional<ShopCategory> category = categoryRepository.findById(item.getShopCategoryId());
				if (category.isEmpty()) {
					throw new BaseFullException(HttpStatus.NOT_FOUND, "Category",
							"api.error.menu.footer.category.not.found");
				}
				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(null).menuFooter(model).shopCategory(category.get()).externalLink(null).build();

				model.getItens().add(itemMenu);
			}

			if (item.getTypeItem().equals(MenuFooterItem.EXTERNAL_LINK)) {
				ItemMenuFooter itemMenu = ItemMenuFooter.builder().typeItem(item.getTypeItem()).label(item.getLabel())
						.idoTool(null).menuFooter(model).shopCategory(null)
						.externalLink(validateLink(item.getExternalLink())).build();

				model.getItens().add(itemMenu);

			}
		}

		model = repository.save(model);

		MenuFooterResponse response = mapper.response(model);

		return response;

	}

	public ItemMenuFooterResponse addItem(ItemMenuFooterRequest request, Long menuId) {

		MenuFooter model = validateId(menuId);
		ItemMenuFooter itemMenu = new ItemMenuFooter();

		if (request.getTypeItem().equals(MenuFooterItem.TOOL)) {
			Optional<IdoTool> idoTool = idoToolRepository.findById(request.getToolId());
			if (idoTool.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Tools", "api.error.menu.footer.tools.not.found");
			}
			itemMenu = ItemMenuFooter.builder().typeItem(request.getTypeItem()).label(request.getLabel())
					.idoTool(idoTool.get()).menuFooter(model).shopCategory(null).externalLink(null).build();

		} else if (request.getTypeItem().equals(MenuFooterItem.CATEGORY_SHOP)) {
			Optional<ShopCategory> category = categoryRepository.findById(request.getShopCategoryId());
			if (category.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Category",
						"api.error.menu.footer.category.not.found");
			}
			itemMenu = ItemMenuFooter.builder().typeItem(request.getTypeItem()).label(request.getLabel()).idoTool(null)
					.menuFooter(model).shopCategory(category.get()).externalLink(null).build();

		} else if (request.getTypeItem().equals(MenuFooterItem.EXTERNAL_LINK)) {
			itemMenu = ItemMenuFooter.builder().typeItem(request.getTypeItem()).label(request.getLabel()).idoTool(null)
					.menuFooter(model).shopCategory(null).externalLink(validateLink(request.getExternalLink())).build();
		}

		model.getItens().add(itemMenu);

		model = repository.save(model);

		ItemMenuFooterResponse response = mapper.itemModelToResponse(itemMenu);

		return response;
	}

	@Override
	@Transactional
	public UpdateMenuFooterResponse updateMenuFooter(Long id, UpdateMenuFooterRequest request) {

		MenuFooter model = validateId(id);

		BeanUtils.copyProperties(request, model, "id", "dateModel", "ido");

		model = repository.save(model);
		return mapper.modelToResponse(model);

	}

	@Transactional
	@Override
	public ItemMenuFooterResponse updateItemMenuFooter(Long itemId, Long menuId, ItemMenuFooterRequest request) {

		Optional<MenuFooter> menuFooter = repository.findById(menuId);

		if (menuFooter.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "MenuFooter", "api.error.menu.footer.not.found");
		}

		List<Long> ids = new ArrayList<>();

		List<ItemMenuFooter> itens = menuFooter.get().getItens();

		ids = itens.stream().map(i -> i.getId()).collect(Collectors.toList());

		if (!ids.contains(itemId)) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Item", "api.error.menu.footer.item.not.belong");
		}

		ItemMenuFooter model = itemRepository.findById(itemId).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "Item", "api.error.menu.footer.item.not.found"));

		if (request.getTypeItem().equals(MenuFooterItem.TOOL)) {
			Optional<IdoTool> idoTool = idoToolRepository.findById(request.getToolId());
			if (idoTool.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Tools", "api.error.menu.footer.tools.not.found");
			}
			model.setIdoTool(idoTool.get());
			model.setShopCategory(null);
			model.setExternalLink(null);
		}

		if (request.getTypeItem().equals(MenuFooterItem.CATEGORY_SHOP)) {
			Optional<ShopCategory> category = categoryRepository.findById(request.getShopCategoryId());
			if (category.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Category",
						"api.error.menu.footer.category.not.found");
			}
			model.setIdoTool(null);
			model.setShopCategory(category.get());
			model.setExternalLink(null);
		}

		if (request.getTypeItem().equals(MenuFooterItem.EXTERNAL_LINK)) {
			model.setIdoTool(null);
			model.setShopCategory(null);
			model.setExternalLink(validateLink(request.getExternalLink()));
		}

		model.setMenuFooter(menuFooter.get());

		BeanUtils.copyProperties(request, model, "id", "dateModel");

		model = itemRepository.save(model);

		ItemMenuFooterResponse response = mapper.itemModelToResponse(model);

		return response;
	}

	@Override
	@Transactional
	public ItemMenuFooterResponse hideItem(Long itemId, Long menuId) {
		boolean exists = repository.existsById(menuId);
		boolean existsItem = itemRepository.existsById(itemId);

		if (!exists) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "MenuFooter", "api.error.menu.footer.not.found");
		}
		if (!existsItem) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Item", "api.error.menu.footer.item.not.belong");
		}
		ItemMenuFooter model = itemRepository.findById(itemId).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "", "api.error.menu.footer.item.not.found"));

		model.setHide(!model.getHide());

		itemRepository.save(model);

		return mapper.itemModelToResponse(model);

	}

	@Override
	@Transactional
	public void delete(Long id) {

		MenuFooter model = validateId(id);
	try {
		repository.delete(model);
		repository.flush();
		
		Long idoId = model.getIdo().getId();
		super.deleteTools(ToolCodName.MENURODAPE, idoId, id, null);
		
	} catch (DataIntegrityViolationException e) {
		throw new BaseFullException(HttpStatus.CONFLICT, "ConfigContactUs", "api.error.contact.us.conflict");
		}
	}

	@Override
	@Transactional
	public void deleteItemMenuFooter(Long itemId, Long menuId) {

		boolean exists = repository.existsById(menuId);
		boolean existsItem = itemRepository.existsById(itemId);

		if (!exists) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "MenuFooter", "api.error.menu.footer.not.found");
		}
		if (!existsItem) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Item", "api.error.menu.footer.item.not.belong");
		}

		itemRepository.deleteById(itemId);
		repository.flush();

	}

	private MenuFooter validateId(Long id) {
		Optional<MenuFooter> model = repository.findById(id);
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "MenuFooter", "api.error.menu.footer.not.found");
		}
		return model.get();
	}

	private MenuFooter validateIdExists(Long id) {

		Optional<MenuFooter> model = repository.findById(id);
		return model.get();
	}

	@SuppressWarnings("unused")
	private String validateLink(String link) {
		String[] linkSplit = link.split("//");

		if (linkSplit.length >= 2) {
			link = "https://" + linkSplit[1];
		} else {
			link = "https://" + linkSplit[0];
		}

		try {
			URL u = new URL(link);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "url", "api.error.url.incorrect");
		}

		return link;

	}

	@SuppressWarnings("unused")
	private MenuFooter deleteListItens(MenuFooter model) {

		if (model.getItens() != null) {

			model.getItens().clear();

		}
		return model;

	}

}
