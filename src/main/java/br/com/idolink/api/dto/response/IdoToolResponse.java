package br.com.idolink.api.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idolink.api.dto.response.ido.IdoProfileResponse;
import br.com.idolink.api.model.enums.IdoToolStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdoToolResponse {

	private Long id;

	private IdoToolStatus status;
	
	private IdoProfileResponse ido;
	
	private ToolResponse tool;
	
	@JsonIgnore
	private LocalDateTime dt_created;
}
