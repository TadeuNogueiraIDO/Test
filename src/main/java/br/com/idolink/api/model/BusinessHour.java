package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.BusinessHourType;
import br.com.idolink.api.model.enums.TimeFormat;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_hour")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE business_hour SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class BusinessHour extends BaseModel<BusinessHour> {

	private static final long serialVersionUID = 119384182276367304L;

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
	
	@Column(name = "type")
	@Enumerated(EnumType.ORDINAL)
	private BusinessHourType type;
	
	@Column(name = "time_format")
	@Enumerated(EnumType.ORDINAL)
	private TimeFormat timeFormat;
	
	@Column(name = "icon_file_id")
	private Long icon;
	
	@Column(name = "typeicon")
	@Enumerated(EnumType.STRING)
	private Typeicon typeicon;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "business_hour_id", referencedColumnName = "id", nullable = false, insertable = true)
	private List<BusinessHourDay> days;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "business_hour_id", referencedColumnName = "id", nullable = false, insertable = true)
	private List<BusinessHourAlternateSchedule> alternativeSchedules;
	
	@OneToOne
	@JoinColumn(name = "ido_id", referencedColumnName = "id")
	private Ido ido;
}
