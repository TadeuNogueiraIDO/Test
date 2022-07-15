package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "country", schema = "public")
public class Country implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3198080250642324387L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "country")
	@Nullable
	private String country;
	
	@Column(name = "ddi")
	@Nullable
	private String ddi;
	
	@Column(name = "coins")
	private String coin;
	
	@Column(name = "coins_name")
	private String coinName;
	
	@Column(name = "masked")
	private String masked;
		
}
