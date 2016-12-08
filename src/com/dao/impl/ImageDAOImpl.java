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

import com.dao.ImageDAO;
import com.bean.Batch;
import com.bean.Image;
import com.bean.User;

public class ImageDAOImpl implements ImageDAO{

	@Autowired
	DataSource dataSource;
	
	@Override
	public int createImage(Image image) {
		
		PreparedStatement  imagestmt;
		Connection conn = null; 
		
		
		int imageId=0;
		try {
			conn=dataSource.getConnection();
			
			String sqlimage = "INSERT INTO image"
				    + "(image_name,image_location,batch_id,stage_id,status) VALUES (?, ?, ?,?,?)";
			
			imagestmt=conn.prepareStatement(sqlimage,Statement.RETURN_GENERATED_KEYS);
			imagestmt.setString(1, image.getImageName());
			imagestmt.setString(2,image.getImageLocation());
			imagestmt.setInt(3, image.getBatchId());
			imagestmt.setInt(4, image.getStageId());
			imagestmt.setString(5,image.getStatus());
			imagestmt.executeUpdate();
			ResultSet rsUser = null;
			rsUser = imagestmt.getGeneratedKeys();
		
		    if (rsUser.next()) {
		    	imageId = rsUser.getInt(1);
		    } 
		    imagestmt.close();
			rsUser.close();
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return imageId;
		
		
	}

	@Override
	public int updateImage(Image image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Image> viewAllImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image viewImage(int imageId) {
		// TODO Auto-generated method stub
		
		Connection conn = null; 
		Image image=new Image();
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * from image where image_id="+imageId;
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				
				image.setImageId(rs.getInt("image_id"));
				image.setBatchId(rs.getInt("batch_id"));
				image.setClassification(rs.getString("classification"));
				image.setImageLocation(rs.getString("image_location"));
				image.setImageName(rs.getString("image_name"));
				image.setStageId(rs.getInt("stage_id"));
				
				
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public List<Image> getImages(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Image> getImagesByClassification(String classification, int batchId) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = null;
			if(batchId==0)
			{
				sql = "SELECT image.image_id as image_id,image.batch_id as batch_id,image.classification as classification,"
						+ "image.image_location as image_location,image.image_name as image_name,image.stage_id as stage_id,workflow.status as status from image,workflow "
						+ "where image.image_id=workflow.image_id and image.classification='"+classification+"'";
						
			}
			else
			{
				sql = "SELECT image.image_id as image_id,image.batch_id as batch_id,image.classification as classification,"
						+ "image.image_location as image_location,image.image_name as image_name,image.stage_id as stage_id,workflow.status as status from image,workflow "
						+ "where image.image_id=workflow.image_id and image.classification='"+classification+"'"+" and image.batch_id="+batchId;
						
			}
			
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
				Image image=new Image();
				image.setImageId(rs.getInt("image_id"));
				image.setBatchId(rs.getInt("batch_id"));
				image.setClassification(rs.getString("classification"));
				image.setImageLocation(rs.getString("image_location"));
				image.setImageName(rs.getString("image_name"));
				image.setStageId(rs.getInt("stage_id"));
				image.setStatus(rs.getString("status"));
				
				imageList.add(image);
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
	public String getCurrentStatus(int imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getImageCountByClassification(String classification, int batchId) {
		// TODO Auto-generated method stub
		int count=0;
		Connection conn = null; 
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlBatch =null;
			if(batchId==0)
			{
				sqlBatch = "SELECT count(*) from image,workflow "
						+ "where image.image_id=workflow.image_id and image.classification='"+classification+"'";
						
			}
			else{
				sqlBatch = "SELECT count(*) from image,workflow "
						+ "where image.image_id=workflow.image_id and image.classification='"+classification+"'"+" and image.batch_id="+batchId;
						
			}
			
			ResultSet rs=stmt.executeQuery(sqlBatch);  
			while(rs.next()) {
				
				count=rs.getInt(1);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Image> getImagesByStage(int stageId, int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getImageCountByStage(int stageId, int batchId) {
		// TODO Auto-generated method stub
		int count=0;
		Connection conn = null; 
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlBatch = "SELECT count(*) from batch where stage_id='"+stageId+"'"+" and batch_id='"+batchId+"'"+" and status='INPROGRESS'";
			ResultSet rs=stmt.executeQuery(sqlBatch);  
			while(rs.next()) {
				
				count=rs.getInt(1);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean updateClassification(int imageId, String Classification) {
		// TODO Auto-generated method stub
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sqluser = "UPDATE image SET classification=? where image_id="+imageId;
			
			pstmt=conn.prepareStatement(sqluser);
			pstmt.setString(1, Classification);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean updateStage(int imageId, int stageId) {
		// TODO Auto-generated method stub
		PreparedStatement  pstmt;
		Connection conn = null; 
				
		try {
			conn=dataSource.getConnection();
			String sqluser = "UPDATE image SET stage_id=? where image_id="+imageId;
			
			pstmt=conn.prepareStatement(sqluser);
			pstmt.setInt(1, stageId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	@Override
	public List<Image> getImagesbyBatchAndStatus(int batchId,String status) {
		// TODO Auto-generated method stub
		List<Image> imageList=new ArrayList<Image>();
		Connection conn = null; 
		
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sql = null;
			if(batchId==0)
			{
				sql = "SELECT image.image_id as image_id,image.image_name as image_name,image.image_location as image_location,"
						+ "image.stage_id,image.batch_id as batch_id,image.classification as classification,workflow.status as status"
						+ " from image,workflow where image.image_id=workflow.image_id and workflow.status='"+status+"'";
			}
			else
			{
				sql = "SELECT image.image_id as image_id,image.image_name as image_name,image.image_location as image_location,"
						+ "image.stage_id,image.batch_id as batch_id,image.classification as classification,workflow.status as status"
						+ " from image,workflow where image.image_id=workflow.image_id and batch_id="+batchId+" and workflow.status='"+status+"'";
			}
			
			ResultSet rs=stmt.executeQuery(sql);  
			while(rs.next()) {
								
				Image image=new Image();
				image.setImageId(rs.getInt("image_id"));
				image.setBatchId(rs.getInt("batch_id"));
				image.setClassification(rs.getString("classification"));
				image.setImageLocation(rs.getString("image_location"));
				image.setImageName(rs.getString("image_name"));
				image.setStageId(rs.getInt("stage_id"));
				image.setStatus(rs.getString("status"));
				imageList.add(image);
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
