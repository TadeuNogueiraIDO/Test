package br.com.idolink.api.model;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.StatusLeads;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attached_pdf_leads")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE attached_pdf_leads SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class AttachedPdfLeads extends BaseModel<AttachedPdfLeads>{

	private static final long serialVersionUID = -2349820645262390772L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusLeads statusLeads;
	
	@ManyToOne
	@JoinColumn(name = "attached_pdf_id", referencedColumnName = "id")
	private AttachedPdf attachedPdf;
	
	
}
