package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "language", schema = "public")
public class Language implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5858854494021949014L;

	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "locale")
	private String locale;
}
