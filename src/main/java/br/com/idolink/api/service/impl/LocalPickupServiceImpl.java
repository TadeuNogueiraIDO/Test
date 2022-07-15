package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.LocalPickupRequest;
import br.com.idolink.api.dto.response.LocalPickupResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.LocalPickupMapper;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.model.LocalPickup;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.State;
import br.com.idolink.api.repository.CountryRepository;
import br.com.idolink.api.repository.LocalPickupRepository;
import br.com.idolink.api.repository.ShippingSettingsRepository;
import br.com.idolink.api.repository.StateRepository;
import br.com.idolink.api.service.LocalPickupService;

@Service
public class LocalPickupServiceImpl implements LocalPickupService {

	@Autowired
	private LocalPickupMapper mapper;

	@Autowired
	private LocalPickupRepository repository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ShippingSettingsRepository settingsRepository;

	@Override
	public List<LocalPickupResponse> findByShippingSettings(Long settingsId, Boolean isEnabled) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");

		List<LocalPickup> model = repository.findByShippingSettings(settings.get().getId(), isEnabled);

		return mapper.response(model);

	}

	@Override
	public LocalPickupResponse findById(Long id) {
		Optional<LocalPickup> model = repository.findById(id);
		validate(model, "LocalPickup", "api.error.local.pickup.not.found");
		return mapper.response(model.get());
	}

	@Transactional
	@Override
	public LocalPickupResponse create(Long settingsId, LocalPickupRequest request) {

		Country country = new Country();

		if (request.getCountry().getId() != null) {
			Optional<Country> c = countryRepository.findById(request.getCountry().getId());

			if (!c.isEmpty()) {
				country = c.get();
			}

		}

		State state = new State();

		if (Objects.nonNull(request.getStateBrasil()) && Objects.nonNull(request.getStateBrasil().getId())) {
			Optional<State> s = stateRepository.findById(request.getStateBrasil().getId());

			if (!s.isEmpty()) {
				state = s.get();
			}
		}
		validateAddress(country, state);

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");

		LocalPickup model = mapper.model(request);

		// Outros
		if (!request.getCountry().getId().equals(1L)) {
			model.setStateBrasil(null);
			model.setNumber(null);
			model.setComplement(null);
			model.setDistrict(null);

		}
		// Brasil
		if (request.getCountry().getId().equals(1L)) {
			model.setAddressLine2(null);
			model.setState(null);
			model.setStateBrasil(state);

		}

		model.setShippingSettings(settings.get());
		model.setCountry(country);
		model = repository.save(model);

		return mapper.response(model);
	}

	@Transactional
	@Override
	public LocalPickupResponse update(Long id, LocalPickupRequest request) {

		@SuppressWarnings("serial")
		LocalPickup model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"LocalPickup", "api.error.local.pickup.not.found") {
		});

		Country country = new Country();

		if (request.getCountry().getId() != null) {
			Optional<Country> c = countryRepository.findById(request.getCountry().getId());

			if (!c.isEmpty()) {
				country = c.get();
			}
		}
		State state = new State();

		if (Objects.nonNull(request.getStateBrasil()) && Objects.nonNull(request.getStateBrasil().getId())) {
			Optional<State> s = stateRepository.findById(request.getStateBrasil().getId());

			if (!s.isEmpty()) {
				state = s.get();
			}
		}
		validateAddress(country, state);

		// Outros
		if (!request.getCountry().getId().equals(1L)) {
			model.setStateBrasil(null);
			model.setNumber(null);
			model.setComplement(null);
			model.setDistrict(null);

		}
		// Brasil
		if (request.getCountry().getId().equals(1L)) {
			model.setAddressLine2(null);
			model.setState(null);
			model.setStateBrasil(state);

		}

		LocalPickup modelNew = mapper.model(request);
		model.setCountry(country);
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shippingSettings");
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public LocalPickupResponse updateEnabled(Long id, Boolean isEnabled) {

		@SuppressWarnings("serial")
		LocalPickup model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"LocalPickup", "api.error.local.pickup.not.found") {
		});

		model.setIsEnabled(isEnabled);
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<LocalPickup> model = repository.findById(id);
		validate(model, "LocalPickup", "api.error.local.pickup.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "LocalPickup",
					"api.error.local.pickup.conflict");
		}
	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	private void validateAddress(Country country, State state) {

		if (country.getId().equals(null)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Country", "api.error.country.not.found");
		}

		if (country.getId().equals(1L) && state.getId().equals(null)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "State", "api.error.address.state.not.found");
		}

	}

}
