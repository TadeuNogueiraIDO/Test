package br.com.idolink.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.idolink.api.model.enums.ToolCodName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ido_tools_position")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class IdoToolPosition implements Serializable{

	private static final long serialVersionUID = 4556406692579804610L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;

	@Column(name="generic_tool_id")
	private Long genericToolId; 
	
	@Column(name="position")
	private Long position;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tool_cod_name")
	private ToolCodName toolCodName;

}

