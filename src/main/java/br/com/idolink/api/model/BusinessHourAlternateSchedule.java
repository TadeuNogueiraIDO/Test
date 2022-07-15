package br.com.idolink.api.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_hour_alternative_schedule")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE business_hour_alternative_schedule SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class BusinessHourAlternateSchedule extends BaseModel<BusinessHourAlternateSchedule> {

	private static final long serialVersionUID = 6379258572147157015L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date day;
	
	@Column(name = "is_alert")
	private Boolean isAlert;
	
	@Column(name = "message_alert")
	private String messageAlert;
	
	@Column(name = "closed")
	private Boolean closed;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "business_hour_alternative_schedule_schedule", joinColumns = {
			@JoinColumn(name = "business_hour_alternative_schedule_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "business_hour_schedule_id", referencedColumnName = "id") })
	private List<BusinessHourSchedule> schedules;
	
	@PrePersist
	private void buildNew() {
		if(Objects.isNull(isAlert)) {
			isAlert = false;
		}
	}
}
