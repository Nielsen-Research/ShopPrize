package com.service;

import java.util.List;

import com.bean.Image;
import com.bean.Workflow;
import com.bean.WorkflowInfo;
import com.ui.WorkflowUI;

public interface WorkflowService {
	
	public int startWorkflow(Workflow workflow);
	public int updateWorkflow(Workflow workflow);
	public int getUserIdByRole(int roleId);
	public int getImageCountByStatus(String status,int batchId);
	public List<Image> getImagesByStatus(String status,int batchId);
	public List<Image>  getPendingImages(int userId,String status);
	public List<WorkflowUI> getWorkflowDetails(int imageId);
	public boolean updateWorkflowInfo(WorkflowInfo workflowInfo);
	public boolean createWorkflowInfo(WorkflowInfo workflowInfo);
	public Workflow getWorkflowDetailById(int id);
	public Workflow getWorkflowDetailByImageId(int imageId);
	public List<Image> getImagesByAssignedUser(int userId,String status);
	public List<Image>  getApprovedImages(int userId);
	public List<Image>  getRejectedImages(int userId);
	
}
