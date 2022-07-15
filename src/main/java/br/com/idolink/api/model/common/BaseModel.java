package br.com.idolink.api.model.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel<T extends BaseModel<T>> implements Serializable {

	private static final long serialVersionUID = -1112571230237032841L;

	public static final String DATE_MODEL = "dateModel";

	@Embedded
	public DateModel dateModel;
	
    @Version
    @Column(nullable = false, name = "version")
    public Integer version;

	@PrePersist
	public void initializeDates() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
		dateModel = new DateModel(now, null, null);
	}
	
	@PreUpdate
	public void updateUpdatedAt() {
		dateModel.dt_updated = LocalDateTime.now(ZoneId.of("UTC"));
	}
}
