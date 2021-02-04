package com.javan.camunda.data;

import com.google.gson.annotations.SerializedName;

public class TaskResponse{

	@SerializedName("owner")
	private Object owner;

	@SerializedName("processDefinitionId")
	private String processDefinitionId;

	@SerializedName("processInstanceId")
	private String processInstanceId;

	@SerializedName("caseExecutionId")
	private Object caseExecutionId;

	@SerializedName("caseDefinitionId")
	private Object caseDefinitionId;

	@SerializedName("formKey")
	private Object formKey;

	@SerializedName("created")
	private String created;

	@SerializedName("parentTaskId")
	private Object parentTaskId;

	@SerializedName("caseInstanceId")
	private Object caseInstanceId;

	@SerializedName("description")
	private Object description;

	@SerializedName("priority")
	private int priority;

	@SerializedName("suspended")
	private boolean suspended;

	@SerializedName("delegationState")
	private Object delegationState;

	@SerializedName("followUp")
	private Object followUp;

	@SerializedName("executionId")
	private String executionId;

	@SerializedName("taskDefinitionKey")
	private String taskDefinitionKey;

	@SerializedName("due")
	private Object due;

	@SerializedName("name")
	private String name;

	@SerializedName("tenantId")
	private Object tenantId;

	@SerializedName("id")
	private String id;

	@SerializedName("assignee")
	private Object assignee;

	public Object getOwner(){
		return owner;
	}

	public String getProcessDefinitionId(){
		return processDefinitionId;
	}

	public String getProcessInstanceId(){
		return processInstanceId;
	}

	public Object getCaseExecutionId(){
		return caseExecutionId;
	}

	public Object getCaseDefinitionId(){
		return caseDefinitionId;
	}

	public Object getFormKey(){
		return formKey;
	}

	public String getCreated(){
		return created;
	}

	public Object getParentTaskId(){
		return parentTaskId;
	}

	public Object getCaseInstanceId(){
		return caseInstanceId;
	}

	public Object getDescription(){
		return description;
	}

	public int getPriority(){
		return priority;
	}

	public boolean isSuspended(){
		return suspended;
	}

	public Object getDelegationState(){
		return delegationState;
	}

	public Object getFollowUp(){
		return followUp;
	}

	public String getExecutionId(){
		return executionId;
	}

	public String getTaskDefinitionKey(){
		return taskDefinitionKey;
	}

	public Object getDue(){
		return due;
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

	public Object getAssignee(){
		return assignee;
	}
}