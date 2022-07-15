package br.com.idolink.api.dto.request.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.idolink.api.dto.request.ContentsRequest;
import br.com.idolink.api.dto.request.HeadingsRequest;
import br.com.idolink.api.dto.request.NotificationApiRequest;


public class NotificationApiRequestBuilder {
	
	private String app_id;
	
	private List<String> includeExternalUserIds;
	
	private String channelForExternalUserIds;
	
	private ContentsRequest contents;
	
	private HeadingsRequest headings;
	
	private String external_id;
	
	private HashMap<String, String> data;
	
	public NotificationApiRequestBuilder() {
		this.app_id = "06d3c4c9-58a3-49b6-98f3-f6bc45768307";
		this.includeExternalUserIds = new ArrayList<>();
		this.channelForExternalUserIds = "";
		this.contents = new ContentsRequest();
		this.headings = new HeadingsRequest();
		this.external_id = "";
		this.data = new HashMap<>();
	}
	
	public static NotificationApiRequestBuilder init() {
		return new NotificationApiRequestBuilder();
	}
	
	public NotificationApiRequestBuilder content(String type, String value) {
		
		ContentsRequest content = new ContentsRequest();
		content.setEn(type);
		content.setEs(value);
		
		this.contents = content;
		return this;
	}
	
	public NotificationApiRequestBuilder externalId(String externalId) {
		this.external_id = externalId;
		return this;
	}
	
	public NotificationApiRequestBuilder heading(String tittle) {
		
		HeadingsRequest heading = new HeadingsRequest();
		heading.setEn(tittle);
		this.headings = heading;
		
		return this;
	}
	
	public NotificationApiRequestBuilder push() {
		this.channelForExternalUserIds = "push";
		
		return this;
	}
	
	public NotificationApiRequestBuilder uuid(String uuid) {
		this.includeExternalUserIds.add(uuid);	
		return this;
	}
	
	public NotificationApiRequestBuilder uuids(List<String> uuids) {
		this.includeExternalUserIds = uuids;
		return this;
	}
	
	public NotificationApiRequestBuilder additionalFields(HashMap<String, String> data) {
		this.data = data;
		return this;
	}
	
	public NotificationApiRequest build() {
		
		NotificationApiRequest request = new NotificationApiRequest();
		request.setApp_id(this.app_id);
		request.setChannel_for_external_user_ids(this.channelForExternalUserIds);
		request.setContents(this.contents);
		request.setHeadings(this.headings);
		request.setInclude_external_user_ids(this.includeExternalUserIds);
		request.setExternal_id(this.external_id);
		request.setData(this.data);
		
		return request;
		
	}
	
	

}
