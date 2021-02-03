package com.javan.camunda.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StartResponse{

	@SerializedName("caseInstanceId")
	private Object caseInstanceId;

	@SerializedName("businessKey")
	private String businessKey;

	@SerializedName("ended")
	private boolean ended;

	@SerializedName("tenantId")
	private Object tenantId;

	@SerializedName("links")
	private List<LinksItem> links;

	@SerializedName("id")
	private String id;

	@SerializedName("suspended")
	private boolean suspended;

	@SerializedName("definitionId")
	private String definitionId;

	public Object getCaseInstanceId(){
		return caseInstanceId;
	}

	public String getBusinessKey(){
		return businessKey;
	}

	public boolean isEnded(){
		return ended;
	}

	public Object getTenantId(){
		return tenantId;
	}

	public List<LinksItem> getLinks(){
		return links;
	}

	public String getId(){
		return id;
	}

	public boolean isSuspended(){
		return suspended;
	}

	public String getDefinitionId(){
		return definitionId;
	}
}