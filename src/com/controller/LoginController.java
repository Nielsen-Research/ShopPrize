package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.LoginBean;
import com.bean.User;
import com.service.UserService;

@Controller
public class LoginController
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
		model.addObject("loginBean", loginBean);
		return model;
	} 
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean)
	{
	
		ModelAndView model= null;
		try
		{
			User user = userService.isValidUser(loginBean.getUserid(), loginBean.getPassword());
			if(user!=null)
			{
				System.out.println("User Login Successful");
				HttpSession session=request.getSession();  
				session.setAttribute("userid", user.getUserid());
				session.setAttribute("name", user.getFname()+" "+user.getLname());
				//code for get role for the user
				
				String role=user.getRole();
				//---end--
				session.setAttribute("role", role);
				
				model = new ModelAndView("redirect:/dashboard");
				
			}
			else
			{
				model = new ModelAndView("login");
				model.addObject("loginBean", loginBean);
				request.setAttribute("message", "Invalid credentials!!");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();  
		session.invalidate();
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@RequestMapping(value="/dashboard")
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
		request.setAttribute("loggedInUser", session.getAttribute("userid"));
		request.setAttribute("message",  session.getAttribute("userid"));
		String role=(String) session.getAttribute("role");
		if(role.equalsIgnoreCase("ADMIN"))
		{
			return new ModelAndView("dashboard");
		}
		else if(role.equalsIgnoreCase("EDITOR")){
			return new ModelAndView("editorDashboard");
		}
		else
		{
			return new ModelAndView("reviewerDashboard");
		}
		
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
}
