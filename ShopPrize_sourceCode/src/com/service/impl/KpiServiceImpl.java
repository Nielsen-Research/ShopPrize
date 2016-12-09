package com.service.impl;

import java.util.List;

import com.bean.Image;
import com.bean.Item;
import com.bean.KPI;
import com.bean.ReceiptInfo;
import com.bean.Workflow;
import com.dao.ImageDAO;
import com.dao.KpiDAO;
import com.dao.ReceiptInfoDAO;
import com.dao.WorkflowDAO;

import com.service.KpiService;

public class KpiServiceImpl implements KpiService {

private ReceiptInfoDAO receiptInfoDAO;
	
private ImageDAO imageDAO;

private WorkflowDAO workflowDAO;
private KpiDAO kpiDAO;


public KpiDAO getKpiDAO() {
	return kpiDAO;
}


public void setKpiDAO(KpiDAO kpiDAO) {
	this.kpiDAO = kpiDAO;
}


public WorkflowDAO getWorkflowDAO() {
	return workflowDAO;
}


public void setWorkflowDAO(WorkflowDAO workflowDAO) {
	this.workflowDAO = workflowDAO;
}

public ImageDAO getImageDAO() {
	return imageDAO;
}

public void setImageDAO(ImageDAO imageDAO) {
	this.imageDAO = imageDAO;
}

	
	public ReceiptInfoDAO getReceiptInfoDAO() {
		return receiptInfoDAO;
	}

	public void setReceiptInfoDAO(ReceiptInfoDAO receiptInfoDAO) {
		this.receiptInfoDAO = receiptInfoDAO;
	}
	
	@Override
	public String getChainName(int chainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculateTimeSpend(int imageId) {
		// TODO Auto-generated method stub
		Workflow workflow=workflowDAO.getWorkflowByImage(imageId);
		long diffMinutes = workflow.getTimeSpend() / (60 * 1000) % 60;
		 long timeDifSeconds = workflow.getTimeSpend() / 1000;
         long seconds=timeDifSeconds % 60;
         String timeSpend=diffMinutes+" "+"mins " + seconds +"seconds";
		
		return timeSpend;
	}

	@Override
	public int characterModifiedOrEntered(int imageId) {
		// TODO Auto-generated method stub
		ReceiptInfo receiptInfo=receiptInfoDAO.getReceiptInfoByImageId(imageId);
		int charNo=0;
		if(isOcred(imageId).equals("NO"))
		{
		
			if(receiptInfo.getReceiptId()!=null)
			charNo=charNo+receiptInfo.getReceiptId().length();
			if(receiptInfo.getAddress()!=null)
			charNo=charNo+receiptInfo.getAddress().length();
			if(receiptInfo.getPurchaseCode()!=null)
			charNo=charNo+receiptInfo.getPurchaseCode().length();
			if(receiptInfo.getPurchaseDate()!=null)
			charNo=charNo+receiptInfo.getPurchaseDate().length();
			if(receiptInfo.getPurchaseTime()!=null)
			charNo=charNo+receiptInfo.getPurchaseTime().length();
			if(receiptInfo.getStoreName()!=null)
			charNo=charNo+receiptInfo.getStoreName().length();
			if(receiptInfo.getStorePhone()!=null)
			charNo=charNo+receiptInfo.getStorePhone().length();
			if(receiptInfo.getStorePhone2()!=null)
			charNo=charNo+receiptInfo.getStorePhone2().length();
			if(receiptInfo.getTotalAmount()!=null)
			charNo=charNo+receiptInfo.getTotalAmount().length();
			if(receiptInfo.getTotalNoOfItem()!=null)
			charNo=charNo+receiptInfo.getTotalNoOfItem().length();
			
			List<Item> itemList=receiptInfo.getItemList();
			for(Item item:itemList)
			{
				if(item.getItemDescription()!=null)
				charNo=charNo+item.getItemDescription().length();
				if(item.getItemQuantity()!=null)
				charNo=charNo+item.getItemQuantity().length();
				if(item.getItemTotal()!=null)
				charNo=charNo+item.getItemTotal().length();
				if(item.getRawItemDiscount()!=null)
				charNo=charNo+item.getRawItemDiscount().length();
				if(item.getRawItemDiscountDesc()!=null)
				charNo=charNo+item.getRawItemDiscountDesc().length();
			}	
		}
		return charNo;
	}

	@Override
	public String isOcred(int imageId) {
		// TODO Auto-generated method stub
		String result="NO";
		Image image=imageDAO.viewImage(imageId);
		if(image.getClassification().equals("GOOD"))
		{
			result="YES";
		}
		return result;
	}

	@Override
	public String getTranscription(int imageId) {
		// TODO Auto-generated method stub
		
		ReceiptInfo receiptInfo=receiptInfoDAO.getReceiptInfoByImageId(imageId);
		if(receiptInfo.getReceiptId()!=null &&
				receiptInfo.getAddress()!=null &&
				receiptInfo.getPurchaseCode()!=null &&
				receiptInfo.getPurchaseDate()!=null &&
				receiptInfo.getPurchaseTime()!=null &&
				receiptInfo.getStoreName()!=null &&
				receiptInfo.getStorePhone()!=null &&
				receiptInfo.getStorePhone2()!=null &&
				receiptInfo.getTotalAmount()!=null &&
				receiptInfo.getTotalNoOfItem()!=null )
		{
			List<Item> itemList=receiptInfo.getItemList();
			String transcript="PARTIAL";
			for(Item item:itemList)
			{
				if(item.getItemDescription()!=null &&
							item.getItemQuantity()!=null &&
								item.getItemTotal()!=null &&
									item.getRawItemDiscount()!=null &&
										item.getRawItemDiscountDesc()!=null)
				{
					transcript="FULL";
				}
				else{
					transcript="PARTIAL";
					break;
				}
			}	
			
			return transcript;
		}
		else{
			return "PARTIAL";
		}
		
		
		
	}


	public void createKPI(KPI kpi) {
		// TODO Auto-generated method stub
		kpiDAO.createKpi(kpi);
	}


	@Override
	public List<KPI> viewAllKpi() {
		// TODO Auto-generated method stub
		return kpiDAO.getAllKPI();
	}


	@Override
	public KPI viewKpi(int kpiId) {
		// TODO Auto-generated method stub
		return kpiDAO.getKpi(kpiId);
	}


	@Override
	public int getOcredCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getOcredCount();
	}


	@Override
	public int getApprovedImageCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getApprovedImageCount();
	}


	@Override
	public int getRejectedImageCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getRejectedImageCount();
	}


	@Override
	public int getChainNameCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getChainNameCount();
	}


	@Override
	public int getFullTranscriptionCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getFullTranscriptionCount();
	}


	@Override
	public int getPartialTranscriptionCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getPartialTranscriptionCount();
	}


	@Override
	public int getTotalImageCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getTotalImageCount();
	}


	@Override
	public int getBadImageCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getBadImageCount();
	}


	@Override
	public int getGoodImageCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getGoodImageCount();
	}


	@Override
	public int getCFCount(float start, float end) {
		// TODO Auto-generated method stub
		return kpiDAO.getCFCount(start, end);
	}


	@Override
	public int getChainIdentifiedCount() {
		// TODO Auto-generated method stub
		return kpiDAO.getChainIdentifiedCount();
	}


	

}
