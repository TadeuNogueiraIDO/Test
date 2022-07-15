package br.com.idolink.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import br.com.idolink.api.model.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_hour_day")
@AllArgsConstructor
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE business_hour_day SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class BusinessHourDay extends BaseModel<BusinessHour>{

	private static final long serialVersionUID = -3533866817403378099L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "day")
	private WeekDay day;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "business_hour_day_schedule", joinColumns = {
			@JoinColumn(name = "business_hour_day_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "business_hour_schedule_id", referencedColumnName = "id") })
	private List<BusinessHourSchedule> schedules;
	
}
