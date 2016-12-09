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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.Batch;
import com.dao.BatchDAO;
import com.service.BatchService;

public class BatchDAOImpl implements BatchDAO{

	@Autowired
	DataSource dataSource;
	
	@Override
	public int createBatch(Batch batch) {
		
		PreparedStatement  batchstmt;
		Connection conn = null; 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		int batchId=0;
		try {
			conn=dataSource.getConnection();
			
			String sqlbatch = "INSERT INTO batch"
				    + "(start_date,created_by,total_image,status) VALUES (?, ?, ?,?)";
			
			batchstmt=conn.prepareStatement(sqlbatch,Statement.RETURN_GENERATED_KEYS);
			batchstmt.setString(1, dateFormat.format(date));
			batchstmt.setInt(2,batch.getCreated_by());
			batchstmt.setInt(3, batch.getTotalImage());
			batchstmt.setString(4, batch.getStatus());
			batchstmt.executeUpdate();
			ResultSet rsBatch = null;
			rsBatch = batchstmt.getGeneratedKeys();
		
		    if (rsBatch.next()) {
		    	batchId = rsBatch.getInt(1);
		    } 
		    batchstmt.close();
		    rsBatch.close();
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return batchId;
	
		
	}

	@Override
	public boolean updateBatch(Batch batch) {
		// TODO Auto-generated method stub
		PreparedStatement  batchstmt;
		Connection conn = null; 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		
		try {
			conn=dataSource.getConnection();
			
			String sqlbatch = "UPDATE batch"
				    + " SET status=?,end_date=? where batch_id="+batch.getBatchId();
			
			batchstmt=conn.prepareStatement(sqlbatch);
			batchstmt.setString(1, batch.getStatus());
			batchstmt.setString(2, dateFormat.format(date));
			
			batchstmt.executeUpdate();
			
		    batchstmt.close();
		    
			conn.close();
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Batch> viewAllBatch() {
		List<Batch> batchList=new ArrayList<Batch>();
		Connection conn = null; 
		Statement stmt=null;;
	
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlBatch = "SELECT * from batch where status='COMPLETED'";
			ResultSet rs=stmt.executeQuery(sqlBatch);  
			while(rs.next()) {
				Batch batch=new Batch();
				
				batch.setBatchId(rs.getInt("batch_id"));
				batch.setCreated_by(rs.getInt("created_by"));
				batch.setStartDate(rs.getString("start_date"));
				batch.setEndDate(rs.getString("end_date"));
				batch.setStatus(rs.getString("status"));
				batch.setTotalImage(rs.getInt("total_image"));
				batchList.add(batch);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return batchList;
	}

	@Override
	public Batch viewBatch(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Batch viewCurrentBatch() {
		Batch batch=null;
		Connection conn = null; 
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlBatch = "SELECT * from batch where status='INPROGRESS'";
			ResultSet rs=stmt.executeQuery(sqlBatch);  
			while(rs.next()) {
				
				batch=new Batch();
				batch.setBatchId(rs.getInt("batch_id"));
				batch.setCreated_by(rs.getInt("created_by"));
				batch.setStartDate(rs.getString("start_date"));
				batch.setEndDate(rs.getString("end_date"));
				batch.setStatus(rs.getString("status"));
				batch.setTotalImage(rs.getInt("total_image"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return batch;
	}

	
	

}
