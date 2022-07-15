package br.com.idolink.api.filter;

import br.com.idolink.api.model.enums.IdoToolStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolFilter {

	private String name;
	
	private IdoToolStatus availability;
}
