package com.service.impl;

import java.util.List;
import java.util.Random;

import com.bean.Image;
import com.bean.Workflow;
import com.bean.WorkflowInfo;
import com.dao.WorkflowDAO;
import com.dao.WorkflowInfoDAO;
import com.service.WorkflowService;
import com.ui.WorkflowUI;

public class WorkflowServiceImpl implements WorkflowService {

	private WorkflowDAO workflowDAO;
	private WorkflowInfoDAO workflowInfoDAO;
	
	
	public WorkflowDAO getWorkflowDAO() {
		return workflowDAO;
	}


	public void setWorkflowDAO(WorkflowDAO workflowDAO) {
		this.workflowDAO = workflowDAO;
	}

	

	public WorkflowInfoDAO getWorkflowInfoDAO() {
		return workflowInfoDAO;
	}


	public void setWorkflowInfoDAO(WorkflowInfoDAO workflowInfoDAO) {
		this.workflowInfoDAO = workflowInfoDAO;
	}


	@Override
	public int startWorkflow(Workflow workflow) {
		// TODO Auto-generated method stub
		int workflowId=workflowDAO.createWorkflow(workflow);
		WorkflowInfo workflowInfo=new WorkflowInfo();
		workflowInfo.setWorkflowId(workflowId);
		workflowInfo.setStatus("PENDING");
		workflowInfo.setCurrentUser(workflow.getAssignTo());
		workflowInfoDAO.createWorkflowInfo(workflowInfo);
		return workflowId;
		
	}


	@Override
	public int getUserIdByRole(int roleId) {
		// TODO Auto-generated method stub
			List<Integer> userIdList=workflowDAO.getUserIdbyRole(roleId);
			Random random = new Random();
		 int index = random.nextInt(userIdList.size());
		 return userIdList.get(index);
	}


	@Override
	public int getImageCountByStatus(String status, int batchId) {
		// TODO Auto-generated method stub
		return workflowDAO.getImageCountByStatus(status, batchId);
	}


	@Override
	public List<Image> getImagesByStatus(String status, int batchId) {
		// TODO Auto-generated method stub
		return workflowDAO.getImagesByStatus(status, batchId);
	}


	@Override
	public List<Image> getPendingImages(int userId,String status) {
		
		// TODO Auto-generated method stub
		return workflowDAO.getPendingImages(userId,status);
	}


	@Override
	public List<WorkflowUI> getWorkflowDetails(int imageId) {
		// TODO Auto-generated method stub
		
		
		return workflowInfoDAO.getWorkflowDetail(imageId);
	}


	@Override
	public boolean updateWorkflowInfo(WorkflowInfo workflowInfo) {
		// TODO Auto-generated method stub
		return workflowInfoDAO.updateWorkflowInfo(workflowInfo);
	}


	@Override
	public boolean createWorkflowInfo(WorkflowInfo workflowInfo) {
		// TODO Auto-generated method stub
		workflowInfoDAO.createWorkflowInfo(workflowInfo);
		return true;
	}


	@Override
	public Workflow getWorkflowDetailById(int id) {
		// TODO Auto-generated method stub
		return workflowDAO.getWorkflowById(id);
	}


	@Override
	public Workflow getWorkflowDetailByImageId(int imageId) {
		// TODO Auto-generated method stub
		return workflowDAO.getWorkflowByImage(imageId);
	}


	@Override
	public List<Image> getImagesByAssignedUser(int userId,String status){
		// TODO Auto-generated method stub
		return workflowDAO.getImagesByAssignedUser(userId,status);
	}


	@Override
	public int updateWorkflow(Workflow workflow) {
		// TODO Auto-generated method stub
		return workflowDAO.updateWorkflow(workflow);
	}


	@Override
	public List<Image> getApprovedImages(int userId) {
		// TODO Auto-generated method stub
		return workflowDAO.getApprovedImages(userId);
	}


	@Override
	public List<Image> getRejectedImages(int userId) {
		// TODO Auto-generated method stub
		return workflowDAO.getRejectedImages(userId);
	}


	

	

}
