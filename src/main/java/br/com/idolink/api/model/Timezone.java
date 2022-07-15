package br.com.idolink.api.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "timezone")
@AllArgsConstructor
@RequiredArgsConstructor
public class Timezone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108408195437989192L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cod_name")
	private String codName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "gmt")
	private Time gmt;
	
	@Column(name = "value")
	private Integer value;
	//-1 Negativo 1 Positivo
	
		
}