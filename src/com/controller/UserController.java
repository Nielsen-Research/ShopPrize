package com.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.service.UserService;



@Controller
public class UserController {
	private static final Logger logger =LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	//createUser
	//viewUser
	//view All user
	//modify user
	//De-activate user
	
	@RequestMapping(value="/userHome")
	public ModelAndView userHome(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			request.setAttribute("loggedInUser", session.getAttribute("userid"));
			request.setAttribute("name",  session.getAttribute("name"));
		return new ModelAndView("createUser");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
		
	
	}
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			request.setAttribute("loggedInUser", session.getAttribute("userid"));
			request.setAttribute("userid",  session.getAttribute("userid"));
			//code for creating user
			User user=new User();
			user.setFname(request.getParameter("fname").toString());
			user.setLname(request.getParameter("lname").toString());
			user.setPassword(request.getParameter("password").toString());
			user.setRole(request.getParameter("role").toString());
			int userid=userService.createUser(user,(int) session.getAttribute("userid"));
			
			
			//--end--
			
		return new ModelAndView("createUser");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
		
	
	}
	@RequestMapping(value="/viewUser")
	public ModelAndView viewUser(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
		request.setAttribute("loggedInUser", session.getAttribute("userid"));
		request.setAttribute("message",  session.getAttribute("userid"));
		//code for view user details
		
		
		
		//--end--
		return new ModelAndView("createUser");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	
	}
	@RequestMapping(value="/viewAllUser")
	public ModelAndView viewAllUser(HttpServletRequest request, HttpServletResponse response)
	{
		
		logger.debug("==========hello=============");
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
		request.setAttribute("loggedInUser", session.getAttribute("userid"));
		request.setAttribute("message",  session.getAttribute("userid"));
		//code for view all user
		List<User> userlist=userService.getAllUser();
		ModelAndView model = new ModelAndView("viewAllUser");
		model.addObject("userlist", userlist);
			
		//--end--
		
		return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	
	}
	
	@RequestMapping(value="/modifyUser/{id}")
	public ModelAndView modifyUser(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			User user=userService.getUser((int) id);
			ModelAndView model = new ModelAndView("modifyUser");
			model.addObject("user", user);
		
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
		
			//code for update user
			User user=new User();
			user.setFname(request.getParameter("fname").toString());
			user.setLname(request.getParameter("lname").toString());
			user.setPassword(request.getParameter("password").toString());
			user.setRole(request.getParameter("role").toString());
			
			//int userid=userService.modifyUser(user,(int) session.getAttribute("userid"));
		
		
		//--end--
		
		return new ModelAndView("redirect:/viewAllUser");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	
	}
	
	@RequestMapping(value="/deleteUser/{id}")
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
		
		//code for delete/deactivate user
		
			userService.deleteUser((int) id);
			
			
		//--end--
		
		return  new ModelAndView("redirect:/viewAllUser");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	
	}

}
