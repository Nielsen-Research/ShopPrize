package com.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Image;
import com.bean.Item;
import com.bean.KPI;
import com.bean.ReceiptInfo;
import com.bean.User;
import com.bean.Workflow;
import com.bean.WorkflowInfo;
import com.service.ImageService;
import com.service.KpiService;
import com.service.ReceiptInfoService;
import com.service.WorkflowService;
import com.ui.WorkflowUI;

@Controller
public class ImageController {
	
	private static final Logger logger =LoggerFactory.getLogger(ImageController.class);
	
	
	@Autowired
	ImageService imageservice;
	
	@Autowired
	ReceiptInfoService receiptinfoservice;
	
	@Autowired
	WorkflowService workflowService;
	
	@Autowired
	KpiService kpiservice;
	
	@RequestMapping(value="/getImageCountByClassification")
	@ResponseBody
	public int getImageCountByClassification(@RequestParam String classification, @RequestParam int batchId)
	{
		System.out.println(classification);
		System.out.println(batchId);
		int count=imageservice.getImageCountByClassification(classification, batchId);
		
		return count;
		
	}
	
	
	
	@RequestMapping(value="/getImageCountByStatus")
	@ResponseBody
	public int getImageCountByStatus(@RequestParam String status, @RequestParam int batchId)
	{
		
		List<Image> imageList=imageservice.getImagesbyBatchAndStatus(batchId, status);
		System.out.println("=================>"+imageList.size());
		return imageList.size();
		
	}

	
	@RequestMapping(value="/captureReceiptData",method=RequestMethod.POST)
	public ModelAndView captureReceiptData(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			String startTime=request.getParameter("startTime");
			String endTime=getTime();
			String receiptId=request.getParameter("receiptId");
			String purchaseDate=request.getParameter("purchaseDate");
			String purchaseTime=request.getParameter("purchaseTime");
			String purchaseCode=request.getParameter("purchaseCode");
			String storeName=request.getParameter("storeName");
			String storePhone1=request.getParameter("storePhone1");
			String storePhone2=request.getParameter("storePhone2");
			String address=request.getParameter("address");
			String totalAmount=request.getParameter("totalAmount");
			String discount=request.getParameter("discount");
			String discountDesc=request.getParameter("discountDesc");
			String totalNoOfItem=request.getParameter("totalNoOfItem");
			String itemDesc[] = request.getParameterValues("itemDesc[]");
			String itemQty[] = request.getParameterValues("itemQty[]");
			String itemTotal[] = request.getParameterValues("itemTotal[]");
			String itemDiscountDesc[] = request.getParameterValues("itemDiscountDesc[]");
			String itemDiscount[] = request.getParameterValues("itemDiscount[]");
			
			ReceiptInfo receipt=new ReceiptInfo();
			receipt.setImageId(Integer.parseInt(request.getParameter("imageId")));
			if(receiptId!="")
			receipt.setReceiptId(receiptId);
			if(purchaseDate!="")
			receipt.setPurchaseDate(purchaseDate);
			if(purchaseTime!="")
			receipt.setPurchaseTime(purchaseTime);
			if(purchaseCode!="")
			receipt.setPurchaseCode(purchaseCode);
			if(storeName!="")
			receipt.setStoreName(storeName);
			if(storePhone1!="")
			receipt.setStorePhone(storePhone1);
			if(address!="")
			receipt.setStorePhone2(storePhone2);
			if(address!="")
			receipt.setAddress(address);
			if(totalAmount!="")
			receipt.setTotalAmount(totalAmount);
			if(totalNoOfItem!="")
			receipt.setTotalNoOfItem(totalNoOfItem);
			if(discountDesc!="")
			receipt.setDiscountDescription(discountDesc);
			if(discount!="")
			receipt.setDiscount(discount);
			List<Item> itemList=new ArrayList<Item>();
			if(itemDesc!=null)
			{
			int itemCount=itemDesc.length;
			for(int i=0;i<itemCount; i++)
			{
				Item item=new Item();
				if(itemDesc[i]!="")
				item.setItemDescription(itemDesc[i]);
				if(itemQty[i]!="")
				item.setItemQuantity(itemQty[i]);
				if(itemTotal[i]!="")
				item.setItemTotal(itemTotal[i]);
				if(itemDiscount[i]!="")
				item.setRawItemDiscount(itemDiscount[i]);
				if(itemDiscountDesc[i]!="")
				item.setRawItemDiscountDesc(itemDiscountDesc[i]);
				itemList.add(item);
			}
			
			}
			receipt.setItemList(itemList);
			logger.debug(receipt.getReceiptId());
			//code for saving receipt object
			receiptinfoservice.AddReceiptInfo(receipt);
			WorkflowInfo workflowInfo=new WorkflowInfo();
			workflowInfo.setCurrentUser(Integer.parseInt(request.getParameter("currentUser")));
			workflowInfo.setRemarks(request.getParameter("remark"));
			workflowInfo.setStatus("SUBMITTED");
			workflowInfo.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflowService.updateWorkflowInfo(workflowInfo);
			
			Workflow workflow=new Workflow();
			workflow.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflow.setCurrent_user(Integer.parseInt(request.getParameter("nextUser")));
			workflow.setStatus("PENDING APPROVAL");
			workflow.setTimeSpend(getTimeDiff(startTime,endTime));
			workflowService.updateWorkflow(workflow);
			WorkflowInfo newWorkflowInfo=new WorkflowInfo();
			newWorkflowInfo.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			newWorkflowInfo.setCurrentUser(Integer.parseInt(request.getParameter("nextUser")));
			newWorkflowInfo.setStatus("PENDING");
			workflowService.createWorkflowInfo(newWorkflowInfo);
			return new ModelAndView("redirect:/qualityCheckHome");
		}else{
			return new ModelAndView("redirect:/login");
			
		}
	}
	
	@RequestMapping(value="/updateReceiptData",method=RequestMethod.POST)
	public ModelAndView updateReceiptData(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			int receiptInfoId=Integer.parseInt(request.getParameter("receiptInfoId"));
			String startTime=request.getParameter("startTime");
			String endTime=getTime();
			String receiptId=request.getParameter("receiptId");
			String purchaseDate=request.getParameter("purchaseDate");
			String purchaseTime=request.getParameter("purchaseTime");
			String purchaseCode=request.getParameter("purchaseCode");
			String storeName=request.getParameter("storeName");
			String storePhone1=request.getParameter("storePhone1");
			String storePhone2=request.getParameter("storePhone2");
			String address=request.getParameter("address");
			String totalAmount=request.getParameter("totalAmount");
			String discount=request.getParameter("discount");
			String discountDesc=request.getParameter("discountDesc");
			String totalNoOfItem=request.getParameter("totalNoOfItem");
			String itemDesc[] = request.getParameterValues("itemDesc[]");
			String itemQty[] = request.getParameterValues("itemQty[]");
			String itemTotal[] = request.getParameterValues("itemTotal[]");
			String itemDiscountDesc[] = request.getParameterValues("itemDiscountDesc[]");
			String itemDiscount[] = request.getParameterValues("itemDiscount[]");
			String ocredOutput = request.getParameter("ocredOutput");
			ReceiptInfo receipt=new ReceiptInfo();
			receipt.setImageId(Integer.parseInt(request.getParameter("imageId")));
			if(receiptId!="")
			receipt.setReceiptId(receiptId);
			if(purchaseDate!="")
			receipt.setPurchaseDate(purchaseDate);
			if(purchaseTime!="")
			receipt.setPurchaseTime(purchaseTime);
			if(purchaseCode!="")
			receipt.setPurchaseCode(purchaseCode);
			if(storeName!="")
			receipt.setStoreName(storeName);
			if(storePhone1!="")
			receipt.setStorePhone(storePhone1);
			if(address!="")
			receipt.setStorePhone2(storePhone2);
			if(address!="")
			receipt.setAddress(address);
			if(totalAmount!="")
			receipt.setTotalAmount(totalAmount);
			if(totalNoOfItem!="")
			receipt.setTotalNoOfItem(totalNoOfItem);
			if(discountDesc!="")
			receipt.setDiscountDescription(discountDesc);
			if(discount!="")
			receipt.setDiscount(discount);
			List<Item> itemList=new ArrayList<Item>();
			if(itemDesc!=null)
			{
			int itemCount=itemDesc.length;
			for(int i=0;i<itemCount; i++)
			{
				Item item=new Item();
				if(itemDesc[i]!="")
				item.setItemDescription(itemDesc[i]);
				if(itemQty[i]!="")
				item.setItemQuantity(itemQty[i]);
				if(itemTotal[i]!="")
				item.setItemTotal(itemTotal[i]);
				if(itemDiscount[i]!="")
				item.setRawItemDiscount(itemDiscount[i]);
				if(itemDiscountDesc[i]!="")
				item.setRawItemDiscountDesc(itemDiscountDesc[i]);
				itemList.add(item);
			}
			
			}
			receipt.setItemList(itemList);
			receipt.setOcredOutput(ocredOutput);
			receipt.setReceiptInfoId(receiptInfoId);
			logger.debug(receipt.getReceiptId());
			//code for saving receipt object
			receiptinfoservice.UpdateReceiptInfo(receipt);
			WorkflowInfo workflowInfo=new WorkflowInfo();
			workflowInfo.setCurrentUser(Integer.parseInt(request.getParameter("currentUser")));
			workflowInfo.setRemarks(request.getParameter("remark"));
			workflowInfo.setStatus("SUBMITTED");
			workflowInfo.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflowService.updateWorkflowInfo(workflowInfo);
			
			Workflow workflow=new Workflow();
			workflow.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflow.setCurrent_user(Integer.parseInt(request.getParameter("nextUser")));
			workflow.setStatus("PENDING APPROVAL");
			workflow.setTimeSpend(getTimeDiff(startTime,endTime));
			workflowService.updateWorkflow(workflow);
			WorkflowInfo newWorkflowInfo=new WorkflowInfo();
			newWorkflowInfo.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			newWorkflowInfo.setCurrentUser(Integer.parseInt(request.getParameter("nextUser")));
			newWorkflowInfo.setStatus("PENDING");
			workflowService.createWorkflowInfo(newWorkflowInfo);
			return new ModelAndView("redirect:/qualityCheckHome");
		}else{
			return new ModelAndView("redirect:/login");
			
		}
	}
	@RequestMapping(value="/badImage/{id}")
	public ModelAndView badImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=imageservice.getImagesByClassification("BAD", id);
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Bad Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/goodImage/{id}")
	public ModelAndView goodImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=imageservice.getImagesByClassification("GOOD", id);
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Good Images");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/approvedImage/{id}")
	public ModelAndView approvedImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=imageservice.getImagesbyBatchAndStatus(id, "APPROVED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Approved Images");
			model.addObject("flag", "TRUE");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/pendingImage/{id}")
	public ModelAndView pendingImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=imageservice.getImagesbyBatchAndStatus(id, "PENDING");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Pending Images");
			
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/rejectedImage/{id}")
	public ModelAndView rejectedImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			List<Image> imageList=imageservice.getImagesbyBatchAndStatus(id, "REJECTED");
			ModelAndView model = new ModelAndView("listImage");
			model.addObject("imageList", imageList);
			model.addObject("heading", "Rejected Images");
			model.addObject("flag", "TRUE");
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	@RequestMapping(value="/qualityCheckHome")
	public ModelAndView qualityCheckHome(HttpServletRequest request, HttpServletResponse response)
	{
		List<Image> imageList=null;
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			if(session.getAttribute("role").equals("REVIEWER"))
			{
				imageList=workflowService.getPendingImages((int) session.getAttribute("userid"),"PENDING APPROVAL");
			}
			else
			{
				imageList=workflowService.getPendingImages((int) session.getAttribute("userid"),"PENDING");
			}
			
			ModelAndView model = new ModelAndView("qualityCheckHome");
			model.addObject("imageList", imageList);
		
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/reviewImage/{id}")
	public ModelAndView reviewImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			//code for getting the workflow details
			List<WorkflowUI> workflowList=workflowService.getWorkflowDetails(id);
			Workflow workflow=workflowService.getWorkflowDetailByImageId(id);
			ReceiptInfo receiptInfo=receiptinfoservice.getReceiptInfoByImageId(id);
			//end
			ModelAndView model = new ModelAndView("reviewImage");
			Image image=imageservice.getImageById(id);
			model.addObject("image", image);
			model.addObject("workflowList", workflowList);
			model.addObject("workflow", workflow);
			model.addObject("receiptInfo", receiptInfo);
			model.addObject("startTime",getTime());
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/dataCapture/{id}")
	public ModelAndView dataCapture(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			//code for getting the workflow details
			List<WorkflowUI> workflowList=workflowService.getWorkflowDetails(id);
			Workflow workflow=workflowService.getWorkflowDetailByImageId(id);
			
			//end
			ModelAndView model = new ModelAndView("dataCapture");
			Image image=imageservice.getImageById(id);
			model.addObject("image", image);
			model.addObject("workflowList", workflowList);
			model.addObject("workflow", workflow);
			model.addObject("startTime",getTime());
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/verifyImage/{id}")
	public ModelAndView verifyImage(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			//code for getting the workflow details
			List<WorkflowUI> workflowList=workflowService.getWorkflowDetails(id);
			Workflow workflow=workflowService.getWorkflowDetailByImageId(id);
			ReceiptInfo receiptInfo=receiptinfoservice.getReceiptInfoByImageId(id);
			//end
			ModelAndView model = new ModelAndView("verifyImage");
			Image image=imageservice.getImageById(id);
			model.addObject("image", image);
			model.addObject("workflowList", workflowList);
			model.addObject("workflow", workflow);
			model.addObject("receiptInfo", receiptInfo);
			model.addObject("startTime",getTime());
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	
	@RequestMapping(value="/imageDetails/{id}")
	public ModelAndView imageDetails(HttpServletRequest request, HttpServletResponse response,@PathVariable int id)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			
			ReceiptInfo receiptInfo=receiptinfoservice.getReceiptInfoByImageId(id);
			
			ModelAndView model = new ModelAndView("modal_viewImage");
			Image image=imageservice.getImageById(id);
			model.addObject("image", image);
			model.addObject("receiptInfo", receiptInfo);
			
			return model;
		}
		else{
			return new ModelAndView("redirect:/login");
			
		}
		
	}
	
	@RequestMapping(value="/submitImage",method=RequestMethod.POST)
	public ModelAndView submitImage(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession(); 
		if(session.getAttribute("userid")!=null)
		{
			
			//===============================
			int receiptInfoId=Integer.parseInt(request.getParameter("receiptInfoId"));
			String startTime=request.getParameter("startTime");
			String endTime=getTime();
			String receiptId=request.getParameter("receiptId");
			String purchaseDate=request.getParameter("purchaseDate");
			String purchaseTime=request.getParameter("purchaseTime");
			String purchaseCode=request.getParameter("purchaseCode");
			String storeName=request.getParameter("storeName");
			String storePhone1=request.getParameter("storePhone1");
			String storePhone2=request.getParameter("storePhone2");
			String address=request.getParameter("address");
			String totalAmount=request.getParameter("totalAmount");
			String discount=request.getParameter("discount");
			String discountDesc=request.getParameter("discountDesc");
			String totalNoOfItem=request.getParameter("totalNoOfItem");
			String itemDesc[] = request.getParameterValues("itemDesc[]");
			String itemQty[] = request.getParameterValues("itemQty[]");
			String itemTotal[] = request.getParameterValues("itemTotal[]");
			String itemDiscountDesc[] = request.getParameterValues("itemDiscountDesc[]");
			String itemDiscount[] = request.getParameterValues("itemDiscount[]");
			String ocredOutput = request.getParameter("ocredOutput");
			ReceiptInfo receipt=new ReceiptInfo();
			receipt.setImageId(Integer.parseInt(request.getParameter("imageId")));
			if(receiptId!="")
			receipt.setReceiptId(receiptId);
			if(purchaseDate!="")
			receipt.setPurchaseDate(purchaseDate);
			if(purchaseTime!="")
			receipt.setPurchaseTime(purchaseTime);
			if(purchaseCode!="")
			receipt.setPurchaseCode(purchaseCode);
			if(storeName!="")
			receipt.setStoreName(storeName);
			if(storePhone1!="")
			receipt.setStorePhone(storePhone1);
			if(address!="")
			receipt.setStorePhone2(storePhone2);
			if(address!="")
			receipt.setAddress(address);
			if(totalAmount!="")
			receipt.setTotalAmount(totalAmount);
			if(totalNoOfItem!="")
			receipt.setTotalNoOfItem(totalNoOfItem);
			if(discountDesc!="")
			receipt.setDiscountDescription(discountDesc);
			if(discount!="")
			receipt.setDiscount(discount);
			List<Item> itemList=new ArrayList<Item>();
			if(itemDesc!=null)
			{
				int itemCount=itemDesc.length;
				for(int i=0;i<itemCount; i++)
				{
					Item item=new Item();
					if(itemDesc[i]!="")
					item.setItemDescription(itemDesc[i]);
					if(itemQty[i]!="")
					item.setItemQuantity(itemQty[i]);
					if(itemTotal[i]!="")
					item.setItemTotal(itemTotal[i]);
					if(itemDiscount[i]!="")
					item.setRawItemDiscount(itemDiscount[i]);
					if(itemDiscountDesc[i]!="")
					item.setRawItemDiscountDesc(itemDiscountDesc[i]);
					itemList.add(item);
				}
			
			}
			receipt.setItemList(itemList);
			receipt.setOcredOutput(ocredOutput);
			receipt.setReceiptInfoId(receiptInfoId);
			logger.debug(receipt.getReceiptId());
			//code for saving receipt object
			receiptinfoservice.UpdateReceiptInfo(receipt);
			//==================================
			
		
			WorkflowInfo workflowInfo=new WorkflowInfo();
			workflowInfo.setCurrentUser(Integer.parseInt(request.getParameter("currentUser")));
			workflowInfo.setRemarks(request.getParameter("remark"));
			workflowInfo.setStatus(request.getParameter("status"));
			workflowInfo.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflowService.updateWorkflowInfo(workflowInfo);
			Workflow workflow=new Workflow();
			workflow.setWorkflowId(Integer.parseInt(request.getParameter("workflowId")));
			workflow.setCurrent_user(Integer.parseInt(request.getParameter("currentUser")));
			workflow.setStatus(request.getParameter("status"));
			workflow.setTimeSpend(getTimeDiff(startTime,endTime));
			workflowService.updateWorkflow(workflow);
			KPI kpi=new KPI();
			kpi.setTime(kpiservice.calculateTimeSpend(Integer.parseInt(request.getParameter("imageId"))));
			kpi.setOcred(kpiservice.isOcred(Integer.parseInt(request.getParameter("imageId"))));
			kpi.setModifiedChar(kpiservice.characterModifiedOrEntered(Integer.parseInt(request.getParameter("imageId"))));
			kpi.setStatus(request.getParameter("status"));
			kpi.setImageId(Integer.parseInt(request.getParameter("imageId")));
			kpi.setTranscription(kpiservice.getTranscription(Integer.parseInt(request.getParameter("imageId"))));
			kpiservice.createKPI(kpi);
			
			return new ModelAndView("redirect:/qualityCheckHome");
		}else{
			return new ModelAndView("redirect:/login");
			
		}
	}
	
	public String getTime()
	{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
			return dateFormat.format(new Date());
			
	}
	public long getTimeDiff(String startTime,String endTime)
	{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			long diff=0;
			Date d1;
			Date d2;
			try {
				d1 = dateFormat.parse(startTime);
				d2= dateFormat.parse(endTime);
				diff = d2.getTime() - d1.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//in milliseconds
			
			return diff;
	}
}
