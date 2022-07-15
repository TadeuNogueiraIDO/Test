package br.com.idolink.api.api;

import br.com.idolink.api.dto.request.NotificationApiRequest;
import feign.Headers;
import feign.RequestLine;

public interface OneSignalApi {

	@RequestLine("POST /v1/notifications")
    @Headers({ "Content-Type: application/json", "Authorization: Basic MmFjY2I3NDItZTEzYS00ZTBlLTk3NTQtYTU4MmE1OGQ2ZDg3"})
    public void send(NotificationApiRequest request);
	
}