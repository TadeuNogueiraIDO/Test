package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "policies_terms_use_shop")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PoliciesTermsUse {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "term_privacy")
	private String privacy;
	
	@Column(name = "term_use")
	private String use;
	
	@Column(name = "term_reimbursement")
	private String reimbursement;
	
}
