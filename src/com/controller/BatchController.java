package com.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Batch;
import com.bean.User;
import com.service.BatchService;
import com.service.ImageService;
import com.service.ReceiptInfoService;
import com.service.WorkflowService;
import com.thread.ProcessThread;
import com.ui.BatchUI;
import com.util.PropertyReader;

@Controller
public class BatchController {
	
	//create batch process
	//view batch process
	//view all batch process
	
	@Autowired
	BatchService batchservice;
	
	@Autowired
	ImageService imageservice;
	
	@Autowired
	WorkflowService workflowservice;
	
	@Autowired
	ReceiptInfoService receiptinfoservice;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/createBatch")
	public ModelAndView createBatch(HttpServletRequest request, HttpServletResponse response)
	{
		
	
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			//custom filter only to return files and ignore directories
			FileFilter fileFilter = new FileFilter(){
				@Override
			    public boolean accept(File pathname) {
			        return pathname.isFile();
			    }
			}; 
			
			
			 	
			 	File[] images = new File(PropertyReader.getInstance().getProperty("image.input.path")).listFiles(fileFilter);
				int totalFiles = images.length;
				Batch batch=new Batch();
				batch.setCreated_by(Integer.parseInt(session.getAttribute("userid").toString()));
				batch.setTotalImage(totalFiles);
				batch.setStatus("INPROGRESS");
				int batchID=batchservice.createBatch(batch);
				System.out.println(batchID);
				String directory_name="process_"+batchID;
				String UPLOAD_DIRECTORY = PropertyReader.getInstance().getProperty("image.location");
				String image_location="/images/"+directory_name;
				boolean file = new File(UPLOAD_DIRECTORY).mkdir();
				ProcessThread thread=new ProcessThread();
				thread.setImages(images);
				thread.setProcessId(batchID);
				thread.setImageService(imageservice);
				thread.setBatchService(batchservice);
				thread.setWorkflowService(workflowservice);
				thread.setReceiptInfoService(receiptinfoservice);
				thread.setUPLOAD_DIRECTORY(UPLOAD_DIRECTORY);
				thread.setImage_location(image_location);
				thread.start();
				
			
		
				
		return new ModelAndView("redirect:/dashboard");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	
	}
	
	@RequestMapping(value="/viewAllBatch")
	public ModelAndView viewAllBatch(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			List<Batch> batchlist=batchservice.viewAllBatch();
			ModelAndView model = new ModelAndView("viewAllBatch");
			
			
			model.addObject("batchlist", batchlist);
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	}
	/*@RequestMapping(value="/viewAllBatch")
	public ModelAndView viewAllBatch(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<BatchUI> batchUIlist=new ArrayList<BatchUI>();
			List<Batch> batchlist=batchservice.viewAllBatch();
			ModelAndView model = new ModelAndView("viewAllBatch");
			for(Batch batch:batchlist)
			{
				BatchUI uiObj=new BatchUI();
				uiObj.setDuration(getDuration(batch));
				uiObj.setProcessId(batch.getBatchId());
				uiObj.setStartDate(batch.getStartDate());
				uiObj.setEndDate(batch.getStartDate());
				uiObj.setTotalImage(batch.getTotalImage());
				batchUIlist.add(uiObj);
			}
			
			model.addObject("batchUIlist", batchUIlist);
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	}*/
	@RequestMapping(value="/checkCurrentBatch")
	@ResponseBody
	public BatchUI CheckCurrentBatch()
	{
		BatchUI batchUI=null;
		Batch batch=batchservice.viewCurrentBatch();
		if(batch!=null)
		{
			System.out.println(batch.getBatchId());
			batchUI=new BatchUI();
			//batchUI.setDuration(getDuration(batch));
			batchUI.setProcessId(batch.getBatchId());
			batchUI.setStartDate(batch.getStartDate());
			batchUI.setEndDate(batch.getStartDate());
			batchUI.setTotalImage(batch.getTotalImage());
			batchUI.setStatus(batch.getStatus());
			//batchUI.setTotalProcessedImage(imageservice.getImageCountByStage(7, batch.getBatchId()));
		}
		
		return batchUI;
		
	}
	//calculate Duration of the batch process
	public String getDuration(Batch batch)
	{
		return "1 hr 30 min";
		
	}
	//calculate Date from string
	public Date getDate(String date)
	{
		return null;
		
	}
	
	
	@RequestMapping(value="/processflow")
	public ModelAndView processFlow(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			return new ModelAndView("processflow");
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
	}
	@RequestMapping(value="/processDetails/{id}")
	public ModelAndView processDetails(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			ModelAndView model = new ModelAndView("processDetails");
			model.addObject("batchId", id);
		
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}

}
