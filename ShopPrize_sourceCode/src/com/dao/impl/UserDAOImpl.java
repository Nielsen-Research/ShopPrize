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

import com.bean.User;
import com.dao.UserDAO;


public class UserDAOImpl implements UserDAO{

	@Autowired
	DataSource dataSource;
	
	@Override
	public User isValidUser(String userid, String password)
	{
		
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		Statement stmt=null;
		User user=null;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlUser = "SELECT * from user where user_id="+userid;
			ResultSet rs=stmt.executeQuery(sqlUser);  
			if (rs.next()) {
				if(rs.getString("password").equals(password)){
					user=new User();
					user.setUserid(rs.getInt("user_id"));
					user.setFname(rs.getString("first_name"));
					user.setLname(rs.getString("last_name"));
					user.setStatus(rs.getString("status"));
					user.setRole(getRole(rs.getInt("role_id")));
				}
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

	@Override
	public int createUser(User user,int createdBy){
		// TODO Auto-generated method stub
		
		PreparedStatement  userstmt;
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		Statement stmt;
		int userid=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			int roleId=0;
			String sqlrole = "SELECT role_id from role where role='"+user.getRole()+"'";
			ResultSet rs=stmt.executeQuery(sqlrole);  
			if (rs.next()) {
				roleId=rs.getInt("role_id");
			}
			
			String sqluser = "INSERT INTO user"
				    + "(first_name,last_name,password,status,role_id,created_by,created_date) VALUES (?, ?, ?,?,?,?,?)";
			java.sql.Date createdDate=new java.sql.Date(calendar.getTime().getTime());
			userstmt=conn.prepareStatement(sqluser,Statement.RETURN_GENERATED_KEYS);
			userstmt.setString(1, user.getFname());
			userstmt.setString(2, user.getLname());
			userstmt.setString(3, user.getPassword());
			userstmt.setString(4, "active");
			userstmt.setInt(5, roleId);
			userstmt.setInt(6,createdBy);
			userstmt.setDate(7, createdDate);
			userstmt.executeUpdate();
			ResultSet rsUser = null;
			rsUser = userstmt.getGeneratedKeys();
		
		    if (rsUser.next()) {
		        userid = rsUser.getInt(1);
		    } 
		    rs.close();
		    rsUser.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userid;
		
		
	}

	@Override
	public User viewUser(int userid) {
		// TODO Auto-generated method stub
		
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		Statement stmt=null;;
		User user=new User();
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlUser = "SELECT * from user where user_id="+userid;
			ResultSet rs=stmt.executeQuery(sqlUser);  
			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setFname(rs.getString("first_name"));
				user.setLname(rs.getString("last_name"));
				user.setStatus(rs.getString("status"));
				user.setPassword(rs.getString("password"));
				user.setRole(getRole(rs.getInt("role_id")));
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

	@Override
	public List<User> viewAllUser(){
		List<User> userList=new ArrayList<User>();
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		Statement stmt=null;;
		
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			String sqlUser = "SELECT * from user";
			ResultSet rs=stmt.executeQuery(sqlUser);  
			while(rs.next()) {
				User user=new User();
				user.setUserid(rs.getInt("user_id"));
				user.setFname(rs.getString("first_name"));
				user.setLname(rs.getString("last_name"));
				user.setStatus(rs.getString("status"));
				user.setRole(getRole(rs.getInt("role_id")));
				userList.add(user);
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
	public int modifyUser(User user,int createdBy){
		// TODO Auto-generated method stub
		PreparedStatement  userstmt;
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		Statement stmt;
		int userid=0;
		try {
			conn=dataSource.getConnection();
			stmt = conn.createStatement();
			int roleId=0;
			String sqlrole = "SELECT role_id from role where role='"+user.getRole()+"'";
			ResultSet rs=stmt.executeQuery(sqlrole);  
			if (rs.next()) {
				roleId=rs.getInt("role_id");
			}
			
			String sqluser = "UPDATE user"
				    + " SET first_name=?,last_name=?,password=?,status=?,role_id=?,created_by=?,created_date=? where user_id="+user.getUserid();
			java.sql.Date createdDate=new java.sql.Date(calendar.getTime().getTime());
			userstmt=conn.prepareStatement(sqluser,Statement.RETURN_GENERATED_KEYS);
			userstmt.setString(1, user.getFname());
			userstmt.setString(2, user.getLname());
			userstmt.setString(3, user.getPassword());
			userstmt.setString(4, "active");
			userstmt.setInt(5, roleId);
			userstmt.setInt(6,createdBy);
			userstmt.setDate(7, createdDate);
			userstmt.executeUpdate();
			ResultSet rsUser = null;
			rsUser = userstmt.getGeneratedKeys();
		
		    if (rsUser.next()) {
		        userid = rsUser.getInt(1);
		    } 
		    rs.close();
		    rsUser.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userid;
	}

	@Override
	public boolean deleteUser(int userid){
		
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
		PreparedStatement stmt=null;;
		User user=new User();
		try {
			conn=dataSource.getConnection();
		
			String sqlUser = "DELETE from user where user_id="+userid;
			stmt = conn.prepareStatement(sqlUser);
			stmt.execute(sqlUser);  
			stmt.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getRole(int roleId)
	{
		Connection conn = null; 
		Calendar calendar = Calendar.getInstance();
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
