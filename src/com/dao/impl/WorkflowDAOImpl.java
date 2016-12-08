package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.WorkflowDAO;
import com.service.ImageService;
import com.bean.Image;
import com.bean.Workflow;

public class WorkflowDAOImpl implements WorkflowDAO{

	
	@Autowired
	DataSource dataSource;
	@Autowired
	ImageService imageservice;
	
	@Override
	public int createWorkflow(Workflow workflow) {
		PreparedStatement  workflowstmt;
		Connection conn = null; 
		
		
		int workflowId=0;
		try {
			conn=dataSource.getConnection();
			
			String sqlworkflow = "INSERT INTO workflow"
				    + "( image_id, assign_to, review_by, acting_user, status,time_spend) VALUES (?,?,?,?,?,?)";
			
			workflowstmt=conn.prepareStatement(sqlworkflow,Statement.RETURN_GENERATED_KEYS);
			
			workflowstmt.setInt(1,workflow.getImageId());
			workflowstmt.setInt(2, workflow.getAssignTo());
			workflowstmt.setInt(3, workflow.getReviewBy());
			workflowstmt.setInt(4, workflow.getCurrent_user());
			workflowstmt.setString(5, workflow.getStatus());
			workflowstmt.setLong(6, workflow.getTimeSpend());
			workflowstmt.executeUpdate();
			ResultSet rsworkflow = null;
			rsworkflow = workflowstmt.getGeneratedKeys();
		
		    if (rsworkflow.next()) {
		    	workflowId = rsworkflow.getInt(1);
		    } 
		    workflowstmt.close();
		    rsworkflow.close();
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return workflowId;
	
		
	}

	@Override
	public int updateWorkflow(Workflow workflow) {
		// TODO Auto-generated method stub
		PreparedStatement  workflowstmt;
		Connection conn = null; 
		
		
		
		try {
			conn=dataSource.getConnection();
			
			String sqlworkflow = "UPDATE workflow"
				    + " SET acting_user=?,status=?,time_spend=? where workflow_id="+workflow.getWorkflowId();
			
			workflowstmt=conn.prepareStatement(sqlworkflow);
			workflowstmt.setInt(1, workflow.getCurrent_user());
			workflowstmt.setString(2, workflow.getStatus());
			long updatedTimespend=getTimeSpendByworkflowId(workflow.getWorkflowId())+workflow.getTimeSpend();
			workflowstmt.setLong(3, updatedTimespend);
			workflowstmt.executeUpdate();
			workflowstmt.close();
		    
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return 0;
	
	}
	
	public long getTimeSpendByworkflowId(int workflowId) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		
		long timeSpend=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT time_spend from workflow where workflow_id="+workflowId;
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				timeSpend=rs.getLong("time_spend");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeSpend;
	}
	@Override
	public Workflow getWorkflowByImage(int imageId) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		Workflow workflow=new Workflow();
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where image_id="+imageId;
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				workflow.setWorkflowId(rs.getInt("workflow_id"));				
				workflow.setAssignTo(rs.getInt("assign_to"));
				workflow.setReviewBy(rs.getInt("review_by"));
				workflow.setImageId(rs.getInt("image_id"));
				workflow.setCurrent_user(rs.getInt("acting_user"));
				workflow.setStatus(rs.getString("status"));
				workflow.setTimeSpend(rs.getLong("time_spend"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
	}

	@Override
	public Workflow getWorkflowById(int workflowId) {
		// TODO Auto-generated method stub
		Connection conn = null; 
		
		Statement stmt=null;;
		Workflow workflow=new Workflow();
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where workflow_id="+workflowId;
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				workflow.setWorkflowId(rs.getInt("workflow_id"));				
				workflow.setAssignTo(rs.getInt("assign_to"));
				workflow.setReviewBy(rs.getInt("review_by"));
				workflow.setImageId(rs.getInt("image_id"));
				workflow.setCurrent_user(rs.getInt("current_user"));
				workflow.setStatus(rs.getString("status"));
				workflow.setTimeSpend(rs.getLong("time_spend"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
	}

	@Override
	public List<Integer> getImageIdsByUser(int userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Integer> getUserIdbyRole(int roleId) {
		// TODO Auto-generated method stub
		List<Integer> userList=new ArrayList<Integer>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlUser = "SELECT * from user where role_id="+roleId;
			ResultSet rs=stmt.executeQuery(sqlUser);  
			while(rs.next()) {
				
				
				
				userList.add(rs.getInt("user_id"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public int getImageCountByStatus(String status, int batchId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Image> getImagesByStatus(String status, int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getPendingImages(int userId,String status) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where acting_user="+userId+" and status='"+status+"'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
								
				imageList.add(imageservice.getImageById(rs.getInt("image_id")));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}

	@Override
	public List<Image> getImagesByAssignedUser(int userId,String status) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where assign_to="+userId+" and status='"+status+"'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
								
				imageList.add(imageservice.getImageById(rs.getInt("image_id")));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}
	
	@Override
	public List<Image> getApprovedImages(int userId) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where assign_to="+userId+" and status='APPROVED'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
								
				imageList.add(imageservice.getImageById(rs.getInt("image_id")));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}
	
	@Override
	public List<Image> getRejectedImages(int userId) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from workflow where assign_to="+userId+" and status='REJECTED'";
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
								
				imageList.add(imageservice.getImageById(rs.getInt("image_id")));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}

	
	
	
}
