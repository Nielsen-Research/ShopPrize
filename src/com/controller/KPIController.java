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

import com.bean.KPI;
import com.service.KpiService;
import com.ui.WorkflowUI;



@Controller
public class KPIController {

	
	@Autowired
	KpiService kpiservice;
	
	@RequestMapping(value="/kpiHome")
	public ModelAndView kpiHome(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			ModelAndView model = new ModelAndView("kpiHome");
			
		
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/kpiInfo")
	public ModelAndView kpiInfo(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			ModelAndView model = new ModelAndView("kpiInfo");
			List<KPI> kpiList=kpiservice.viewAllKpi();
		System.out.println(kpiList.size());
			model.addObject("kpiList", kpiList);
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/modal_kpi/{id}")
	public ModelAndView modal_kpi(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		
			KPI kpi=kpiservice.viewKpi(id);
			ModelAndView model = new ModelAndView("modal_kpiDetail");
			model.addObject("kpi", kpi);
			return model;
		
		
	}
	
	@RequestMapping(value="/getOcredCount")
	@ResponseBody
	public int getOcredCount()
	{
		return kpiservice.getOcredCount();
		
	}
	@RequestMapping(value="/getApprovedImageCount")
	@ResponseBody
	public int getApprovedImageCount()
	{
		return kpiservice.getApprovedImageCount();
		
	}
	@RequestMapping(value="/getRejectedImageCount")
	@ResponseBody
	public int getRejectedImageCount()
	{
		return kpiservice.getRejectedImageCount();
		
	}
	@RequestMapping(value="/getChainNameCount")
	@ResponseBody
	public int getChainNameCount()
	{
		return kpiservice.getChainNameCount();
		
	}
	@RequestMapping(value="/getFullTranscriptionCount")
	@ResponseBody
	public int getFullTranscriptionCount()
	{
		return kpiservice.getFullTranscriptionCount();
		
	}
	@RequestMapping(value="/getPartialTranscriptionCount")
	@ResponseBody
	public int getPartialTranscriptionCount()
	{
		return kpiservice.getPartialTranscriptionCount();
		
	}
	@RequestMapping(value="/getBadImageCount")
	@ResponseBody
	public int getBadImageCount()
	{
		return kpiservice.getBadImageCount();
		
	}
	@RequestMapping(value="/getTotalImageCount")
	@ResponseBody
	public int getTotalImageCount()
	{
		return kpiservice.getTotalImageCount();
		
	}
	@RequestMapping(value="/getGoodImageCount")
	@ResponseBody
	public int getGoodImageCount()
	{
		return kpiservice.getGoodImageCount();
		
	}
	
	@RequestMapping(value="/getCFCount")
	@ResponseBody
	public int getCFCount(@RequestParam float start, @RequestParam float end)
	{
		return kpiservice.getCFCount(start, end);
		
	}
	@RequestMapping(value="/getChainIdentifiedCount")
	@ResponseBody
	public int getChainIdentifiedCount()
	{
		return kpiservice.getChainIdentifiedCount();
		
	}
}
