package br.com.idolink.api.model;

import java.io.Serializable;
import java.time.LocalDate;

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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "access_report")
@AllArgsConstructor
@RequiredArgsConstructor
public class AccessReport implements Serializable {
	
	private static final long serialVersionUID = 9140554358460533930L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ip")
	private String ip;

	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;

	@Enumerated(EnumType.STRING)
	@Column(name = "tool_cod_name")
	private ToolCodName toolCodName;
	
	@Column(name = "access_date")
	private LocalDate accessDate;
	
	@Column(name = "generic_tool_id")
	private Long genericToolId;
	
}
