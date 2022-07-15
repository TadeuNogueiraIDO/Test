package br.com.idolink.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "logo_bio")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE logo_bio SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class LogoBio  extends BaseModel<LogoBio>{

	private static final long serialVersionUID = 1946902676875773220L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "activated")
    @Builder.Default
    @ColumnDefault("true")
	private Boolean activated = true;
	
	@Column(name = "file_id")
	private Long fileId;
	
	@Column(name = "logo")
	private Boolean logo;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "name_boo")
	private Boolean nameActivated;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "bio_boo")
	private Boolean bioActivated;
		
	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Ido ido;

}
