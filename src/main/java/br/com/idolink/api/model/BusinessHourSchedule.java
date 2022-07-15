package br.com.idolink.api.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import br.com.idolink.api.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_hour_schedule")
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Where(clause = "dt_deleted is null")
@SQLDelete(sql = "UPDATE business_hour_schedule SET dt_deleted = CURRENT_TIMESTAMP WHERE id=? and version=?")
public class BusinessHourSchedule extends BaseModel<BusinessHour>{
	
	private static final long serialVersionUID = 7058894326334595040L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "close_time")
	private LocalTime openTime;
	
	@Column(name = "open_time")
	private LocalTime closeTime;
}
