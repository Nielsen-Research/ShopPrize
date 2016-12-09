package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Image;
import com.bean.User;
import com.bean.Workflow;
import com.service.WorkflowService;
import com.ui.WorkflowUI;


@Controller
public class WorkflowController {
	
	@Autowired
	WorkflowService workflowService;
	
	
	@RequestMapping(value="/getImages")
	@ResponseBody
	public int getImages(@RequestParam int userId,@RequestParam String status)
	{
		return workflowService.getPendingImages(userId,status).size();
		
	}
	
	@RequestMapping(value="/getImagesByAssignedUser")
	@ResponseBody
	public int getReviewPendingImages(@RequestParam int userId,@RequestParam String status)
	{
		return workflowService.getImagesByAssignedUser(userId,status).size();
		
	}
	
	@RequestMapping(value="/modal_workflow/{id}")
	public ModelAndView reviewImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		
			List<WorkflowUI> workflowList=workflowService.getWorkflowDetails(id);
			ModelAndView model = new ModelAndView("modal_workflow");
			model.addObject("workflowList", workflowList);
			return model;
		
		
	}
	@RequestMapping(value="/rejectedImageList/{id}")
	public ModelAndView rejectedImageList(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getImagesByAssignedUser(id,"REJECTED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Rejected Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/approvedImageList/{id}")
	public ModelAndView approvedImageList(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getImagesByAssignedUser(id,"APPROVED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Approved Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/pendingReviewList/{id}")
	public ModelAndView pendingReviewList(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getImagesByAssignedUser(id,"PENDING APPROVAL");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Pending Review");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/pendingImageList/{id}")
	public ModelAndView pendingImageList(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getPendingImages(id,"PENDING");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Pending Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/reviewList/{id}")
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getPendingImages(id,"PENDING APPROVAL");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Pending Review");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/approvedImagesByActingUser/{id}")
	public ModelAndView approvedImagesByActingUser(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getPendingImages(id,"APPROVED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Approved Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/rejectedImagesByActingUser/{id}")
	public ModelAndView rejectedImagesByActingUser(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=workflowService.getPendingImages(id,"REJECTED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Rejected Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
}
