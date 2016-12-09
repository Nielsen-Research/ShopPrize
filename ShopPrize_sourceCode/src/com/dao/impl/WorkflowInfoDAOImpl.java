package com.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.bean.Image;
import com.bean.User;
import com.bean.WorkflowInfo;
import com.dao.WorkflowInfoDAO;
import com.ui.WorkflowUI;


public class WorkflowInfoDAOImpl implements WorkflowInfoDAO{

	
	@Autowired
	DataSource dataSource;
	
	@Override
	public int createWorkflowInfo(WorkflowInfo workflowInfo) {
		// TODO Auto-generated method stub
		PreparedStatement  workflowstmt;
		Connection conn = null; 
		
		System.out.println(workflowInfo.getWorkflowId());
		System.out.println(workflowInfo.getCurrentUser());
		int workflowInfoId=0;
		try {
			conn=dataSource.getConnection();
			
			String sqlworkflow = "INSERT INTO workflow_info"
				    + "(workflow_id,acting_user,status,remark) VALUES (?, ?,?,?)";
			
			workflowstmt=conn.prepareStatement(sqlworkflow,Statement.RETURN_GENERATED_KEYS);
			
			workflowstmt.setInt(1,workflowInfo.getWorkflowId());
			workflowstmt.setInt(2, workflowInfo.getCurrentUser());
			workflowstmt.setString(3, workflowInfo.getStatus());
			workflowstmt.setString(4, workflowInfo.getRemarks());
			
			workflowstmt.executeUpdate();
			ResultSet rsworkflow = null;
			rsworkflow = workflowstmt.getGeneratedKeys();
		
		    if (rsworkflow.next()) {
		    	workflowInfoId = rsworkflow.getInt(1);
		    } 
		    workflowstmt.close();
		    rsworkflow.close();
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return workflowInfoId;
	}

	@Override
	public boolean updateWorkflowInfo(WorkflowInfo workflowInfo) {
		// TODO Auto-generated method stub
		PreparedStatement  workflowstmt;
		Connection conn = null; 
		
		
		int workflowInfoId=0;
		try {
			conn=dataSource.getConnection();
			
			String sqlworkflow = "UPDATE workflow_info"
				    + " SET status= ? , remark= ? where workflow_id="+workflowInfo.getWorkflowId() +" and acting_user="+workflowInfo.getCurrentUser();
			
			workflowstmt=conn.prepareStatement(sqlworkflow);
			workflowstmt.setString(1, workflowInfo.getStatus());
			workflowstmt.setString(2, workflowInfo.getRemarks());
			workflowstmt.executeUpdate();
			workflowstmt.close();
		   
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return true;
	}

	public List<WorkflowUI> getWorkflowDetail(int imageId)
	{
		
		List<WorkflowUI> workflowUIList=new  ArrayList<WorkflowUI>();
		Connection conn = null; 
		
		Statement stmt=null;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT workflow.workflow_id as workflow_id ,workflow_info.workflow_info_id as workflow_info_id ,workflow_info.acting_user as acting_user,workflow_info.status as status,workflow_info.remark as remark from workflow,workflow_info where workflow.workflow_id=workflow_info.workflow_id and workflow.image_id="+imageId;
			ResultSet rs=stmt.executeQuery(sql); 
			int count=1;
			while(rs.next()) {
				User user=getUser(rs.getInt("acting_user"));
				WorkflowUI workflowUI=new WorkflowUI();	
				workflowUI.setStep(count);
				workflowUI.setWorkflowId(rs.getInt("workflow_id"));
				workflowUI.setWorkflowInfoId(rs.getInt("workflow_info_id"));
				workflowUI.setUserId(user.getUserid());
				workflowUI.setUserName(user.getFname()+" "+user.getLname());
			
				workflowUI.setRole(user.getRole());
				workflowUI.setStatus(rs.getString("status"));
				workflowUI.setRemark(rs.getString("remark"));
				workflowUIList.add(workflowUI);
				count++;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return workflowUIList;
		
	}
	public User getUser(int userId)
	{
		Connection conn = null; 
		
		Statement stmt=null;;
		User user=new User();
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlUser = "SELECT * from user where user_id="+userId;
			ResultSet rs=stmt.executeQuery(sqlUser);  
			while(rs.next()) {
				
				user.setUserid(rs.getInt("user_id"));
				user.setFname(rs.getString("first_name"));
				user.setLname(rs.getString("last_name"));
				user.setStatus(rs.getString("status"));
				user.setRole(getUserRole(rs.getInt("role_id")));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	public String getUserRole(int roleId)
	{
		Connection conn = null; 
		
		Statement stmt=null;
		String role=null;
		try {
			conn=dataSource.getConnection();
			stmt=conn.createStatement();
			String sqlrole = "SELECT role from role where role_id="+roleId;
			ResultSet rs=stmt.executeQuery(sqlrole);  
			if (rs.next()) {
				role=rs.getString("role");
						}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return role;
		
	}
}
