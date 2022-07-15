package br.com.idolink.api.dto.request;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class NotificationApiRequest {
	
	private String app_id; 
	
	private List<String> include_external_user_ids;
	
	private String channel_for_external_user_ids;
	
	private ContentsRequest contents;
	
	private HeadingsRequest headings;
	
	private HashMap<String, String> data;
	
	private String external_id;

}
