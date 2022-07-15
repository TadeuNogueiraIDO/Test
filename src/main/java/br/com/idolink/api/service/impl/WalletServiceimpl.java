package br.com.idolink.api.service.impl;

import static java.util.Objects.nonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.api.PagarmeAPI;
import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.request.WalletWithsrawFrequencyRequest;
import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.dto.response.WalletDetailsResponse;
import br.com.idolink.api.dto.response.WalletGeneralResponse;
import br.com.idolink.api.dto.response.WalletRecipientResponse;
import br.com.idolink.api.dto.response.WalletResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import br.com.idolink.api.mapper.WalletMapper;
import br.com.idolink.api.model.Bank;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.Wallet;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.repository.BankRepository;
import br.com.idolink.api.repository.InvoiceRepository;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.repository.WalletRepository;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NotificationService;
import br.com.idolink.api.service.PortifolioMovimentsService;
import br.com.idolink.api.service.WalletService;

@Service
public class WalletServiceimpl implements WalletService{

	
	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PortifolioMovimentsService movimentsService;
	
	@Autowired
	private NotificationSettingRepository notificationServiceRepository;
	
	@Autowired
	private PagarmeAPI pagarmeApi;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private WalletMapper mapper;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	@Override
	@Transactional
	public WalletDetailsResponse create(Long userId, WalletRequest request) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "User", "api.error.user.not.found"));
		
		Bank bank = bankRepository.findById(request.getBank().getId())
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Bank", "api.error.bank.not.found"));
		
		request.setWithdrawFrequency(valideFrequencyRequest(request));
		
		Wallet wallet = mapper.requestToModel(request, user, bank);
		checkAndBuildRequestToPagarme(request);
		
		WalletRecipientResponse obj = pagarmeApi.createRecipient(request);
		wallet.setToken(obj.getId());
		wallet.setTypeWallet(request.getTypeWallet().getName());
		
		createNotification(userId);
		
		return mapper.modelToDetailsResponse(repository.save(wallet));
	}
	
	@Override
	@Transactional
	public WalletDetailsResponse update(Long id, WalletRequest request) {
		
		Wallet model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Wallet", "api.error.wallet.not.found"));
		
		Bank bank = bankRepository.findById(request.getBank().getId())
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Bank", "api.error.bank.not.found"));
		
		
		request.setWithdrawFrequency(valideFrequencyRequest(request));
		
		Wallet wallet = mapper.requestToModel(request, model.getUser(), bank);
		
		BeanUtils.copyProperties(wallet, model, "id", "user", "dateModel");
		return mapper.modelToDetailsResponse(repository.save(model));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Wallet model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Wallet", "api.error.wallet.not.found"));
		try {			
			repository.delete(model);
		}catch (Exception e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Wallet", "api.error.wallet.conflict");
		}
		
	}

	@Override
	public WalletDetailsResponse findById(Long id) {
		
		Wallet wallet = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Wallet", "api.error.wallet.not.found"));
		
		return mapper.modelToDetailsResponse(wallet);
	}
	
	public WalletWithsrawFrequencyRequest valideFrequencyRequest(WalletRequest request) {
		WalletWithsrawFrequencyRequest frequency =  request.getWithdrawFrequency();
		
		switch (frequency.getPeriodFrequency()) {
		case daily:
			frequency.setDay(null);
			frequency.setWeekDay(null);
			break;
		case monthly:
			frequency.setWeekDay(null);
			if(frequency.getDay() == null) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Wallet","api.error.wallet.day.not.found");
			}
			break;
		default:
			frequency.setDay(null);
			if(frequency.getWeekDay() == null) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Wallet", "api.error.wallet.week.not.found");
			}
			break;
		}
		
		return frequency;
	}

	@Override
	public List<WalletResponse> findAll(Long userId) {
		
		List<Wallet> wallets = repository.findAll(userId);
		
		return wallets.stream().map(w -> mapper.modelToResponse(w)).collect(Collectors.toList());
	}

	@Override
	public WalletGeneralResponse findGeneralWallet(Long userId) {
		List<Wallet> wallets = repository.findAll(userId);
		
		 PortifolioMovimentationGeneralResponse movimentation = movimentsService.findPortifolioMovements(new PortifolioMovimentationFilter(),
					 userId);
		 
		 BigDecimal totalPriceGatewayIdo = wallets.stream().map(w -> 
		 	pagarmeApi.findGeneralBalance(w.getToken()).getAvailable().getAmount()).reduce(BigDecimal.ZERO, BigDecimal :: add);
		 
		 
		 BigDecimal totalPriceInvoice = invoiceRepository.getTotalPriceInvoices(userId);
		 
		return mapper.modelToGeneral(wallets, movimentation.getTotal(), totalPriceGatewayIdo, totalPriceInvoice);
	}
	
	private void checkAndBuildRequestToPagarme(WalletRequest request) {
		
		Boolean isValid = true;
		if(nonNull(request.getBank_account()) 	
			&& (!request.getDocument().equals(request.getBank_account().getDocument_number())
			|| !request.getNameReason().equals(request.getBank_account().getLegal_name()))) { 
			isValid = false;
		}
		if(!isValid) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Wallet", "api.error.wallet.client.bank.conflict");
		}
		
	}
	
		private void createNotification(Long userId) {
		
        Optional<User> user = userRepository.findById(userId);
        
        NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.get().getId());
	
		if(settingNotification.getRequests()) {
			
		Notification notification =	Notification.builder()
		.title(messagePropertieService.getMessageAttribute("api.notification.user.title.wallet"))
		.message(messagePropertieService.getMessageAttribute("api.notification.user.wallet"))
		.icon("8de9e347-0c11-4a54-b1a8-eef9bdd92791")
		.user(user.get())
		.read(false)
		.creationDate(LocalDate.now())
		.typeNotification(NotificationType.WALLET).build();
		
		
		notificationService.create( notification );
		
		}
		
		
	}
}
