package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tool_currency")
@AllArgsConstructor
@RequiredArgsConstructor
public class ToolCurrency {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "tool_id", referencedColumnName = "id")
	private Tool tool;
	
	@ManyToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currency;
	
	@Column(name = "price")
	private Double price;
	

}
