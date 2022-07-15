package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Representa o Status de todas as ferramentas para um unico Ido
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolStatusListResponse {

	private List<ToolStatusResponse> tool;
}
