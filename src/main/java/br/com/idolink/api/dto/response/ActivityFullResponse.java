package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ActivityFullResponse extends ActivityResponse {
	private BlobFileResponse img;
}
