package com.dao;

import java.util.List;

import com.bean.WorkflowInfo;
import com.ui.WorkflowUI;

public interface WorkflowInfoDAO {
	
	public int createWorkflowInfo(WorkflowInfo workflowInfo);
	public boolean updateWorkflowInfo(WorkflowInfo workflowInfo);
	public List<WorkflowUI> getWorkflowDetail(int imageId);

}
