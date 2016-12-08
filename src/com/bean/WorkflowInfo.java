package com.bean;

public class WorkflowInfo {
	
	int id;
	int workflowId;
	int currentUser;
	String status;
	/* 
	 * 1. pending
	 * 2. submitted
	 * 3. approved
	 * 4. rejected
	 * 
	 */
	String remarks;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(int workflowId) {
		this.workflowId = workflowId;
	}
	public int getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	

}
