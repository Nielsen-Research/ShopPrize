package com.dao;

import java.util.List;

import com.bean.Image;
import com.bean.Workflow;

public interface WorkflowDAO {
	
	
	public int createWorkflow(Workflow workflow);
	public int updateWorkflow(Workflow workflow);
	public Workflow getWorkflowByImage(int imageId);
	public Workflow getWorkflowById(int workflowId);
	public List<Integer> getImageIdsByUser(int userid);
	public  List<Integer> getUserIdbyRole(int roleId);
	public int getImageCountByStatus(String status,int batchId);
	public List<Image> getImagesByStatus(String status,int batchId);
	public List<Image>  getPendingImages(int userId,String status);
	public List<Image> getImagesByAssignedUser(int userId,String status);
	public List<Image>  getApprovedImages(int userId);
	public List<Image>  getRejectedImages(int userId);
	
}
