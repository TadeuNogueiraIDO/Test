package br.com.idolink.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.DocumentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "wallet")
@Getter
@Setter
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE wallet SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class Wallet extends BaseModel<Wallet>{
	
	private static final long serialVersionUID = 5881397816742910765L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "wallet_nickname")
	private String walletNickname;
	
	@Column(name = "token")
	private String token;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "document_type")
	private DocumentType documentType;
	
	@Column(name = "type_wallet")
	private String typeWallet;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "withdraw_frequency_id", referencedColumnName = "id")
	private WalletWithdrawFrequency withdrawFrequency;
	
	@Column(name = "telephone")
	private String telephone;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Bank bank;
	
	@Column(name = "document")
	private String document;


}
