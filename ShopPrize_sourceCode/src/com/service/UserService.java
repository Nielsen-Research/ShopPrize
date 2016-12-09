package com.service;

import java.sql.SQLException;
import java.util.List;

import com.bean.User;



public interface UserService {

	public User isValidUser(String userid, String password) throws SQLException;
	public int createUser(User user,int loggedInUser);
	public User getUser(int id);
	public List<User> getAllUser();
	public int modifyUser(User user,int loggedInUser);
	public boolean deleteUser(int id);
	
}
