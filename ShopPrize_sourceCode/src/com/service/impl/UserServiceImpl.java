package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.bean.User;
import com.dao.UserDAO;
import com.service.UserService;

public class UserServiceImpl implements UserService
{

	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}



	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public User isValidUser(String userid, String password) throws SQLException
	{
		return userDAO.isValidUser(userid, password);
	}



	@Override
	public int createUser(User user,int loggedInUser) {
		// TODO Auto-generated method stub
		return userDAO.createUser(user,loggedInUser);
	}



	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userDAO.viewUser(id);
	}



	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDAO.viewAllUser();
	}



	@Override
	public int modifyUser(User user,int loggedInUser) {
		// TODO Auto-generated method stub
		return userDAO.modifyUser(user,loggedInUser);
	}



	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(id);
	}



	








}
