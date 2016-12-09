package com.dao;


import java.util.List;

import com.bean.User;

public interface UserDAO{
		public User isValidUser(String userid, String password);
		public int createUser(User user,int createdBy);
		public User viewUser(int userid);
		public int modifyUser(User user,int modifiedBy);
		public List<User> viewAllUser();
		public boolean deleteUser(int userid);
		
}


