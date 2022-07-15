package br.com.idolink.api.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.DisplayFormPdf;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attached_pdf")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE attached_pdf SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class AttachedPdf extends BaseModel<AttachedPdf>{

	private static final long serialVersionUID = -2349820645262390772L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "subtitle")
	private String subtitle;
	
	@Column(name = "pdf_file_id")
	private Long pdf;
	
	@Column(name = "icon_file_id")
	private Long icon;
	
	@Column(name = "data_user")
	private String dataUser;
	
	@Column(name = "banner")
	private Long banner;
		
	@Column(name = "typeicon")
	@Enumerated(EnumType.STRING)
	private Typeicon typeicon;
	
	@ManyToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
	
	@OneToMany(mappedBy = "attachedPdf", fetch = FetchType.LAZY)
	private List<AttachedPdfLeads> attachedPdfLeads;
	
	@Column(name = "button_animation")
	private Boolean buttonAnimation;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "display_form")
	private DisplayFormPdf displayForm;
	
	@Column(name = "show_title")
	private Boolean showTitle;
	
	@PrePersist
	private void initializeShowTitlefalse() {
		if (Objects.isNull(showTitle)) {
			showTitle = false;
		}
	
	}
}
