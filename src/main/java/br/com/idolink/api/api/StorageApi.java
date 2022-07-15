package br.com.idolink.api.api;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface StorageApi {

	@RequestLine("GET /file/{id}")
	@Headers({ "Content-Type: application/json"})
    BlobFileResponse findFileById(@Param("id") Long id);
	
	@RequestLine("GET /file/uuid/{uuid}")
	@Headers({ "Content-Type: application/json"})
    BlobFileResponse findByUuid(@Param("uuid") String uuid);
}
