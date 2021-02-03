package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class ProcessDefinitionResponse{

	@SerializedName("resource")
	private String resource;

	@SerializedName("startableInTasklist")
	private boolean startableInTasklist;

	@SerializedName("description")
	private Object description;

	@SerializedName("historyTimeToLive")
	private Object historyTimeToLive;

	@SerializedName("versionTag")
	private Object versionTag;

	@SerializedName("version")
	private int version;

	@SerializedName("suspended")
	private boolean suspended;

	@SerializedName("diagram")
	private Object diagram;

	@SerializedName("deploymentId")
	private String deploymentId;

	@SerializedName("name")
	private String name;

	@SerializedName("tenantId")
	private Object tenantId;

	@SerializedName("id")
	private String id;

	@SerializedName("category")
	private String category;

	@SerializedName("key")
	private String key;

	public String getResource(){
		return resource;
	}

	public boolean isStartableInTasklist(){
		return startableInTasklist;
	}

	public Object getDescription(){
		return description;
	}

	public Object getHistoryTimeToLive(){
		return historyTimeToLive;
	}

	public Object getVersionTag(){
		return versionTag;
	}

	public int getVersion(){
		return version;
	}

	public boolean isSuspended(){
		return suspended;
	}

	public Object getDiagram(){
		return diagram;
	}

	public String getDeploymentId(){
		return deploymentId;
	}

	public String getName(){
		return name;
	}

	public Object getTenantId(){
		return tenantId;
	}

	public String getId(){
		return id;
	}

	public String getCategory(){
		return category;
	}

	public String getKey(){
		return key;
	}
}