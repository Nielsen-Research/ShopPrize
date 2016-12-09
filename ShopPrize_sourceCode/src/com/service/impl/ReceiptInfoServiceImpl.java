package com.service.impl;

import com.bean.Image;
import com.bean.ReceiptInfo;

import com.dao.ReceiptInfoDAO;
import com.ocr.ReceiptScanner;
import com.service.ReceiptInfoService;

public class ReceiptInfoServiceImpl implements ReceiptInfoService {

	private ReceiptInfoDAO receiptInfoDAO;
	
	

	
	public ReceiptInfoDAO getReceiptInfoDAO() {
		return receiptInfoDAO;
	}

	public void setReceiptInfoDAO(ReceiptInfoDAO receiptInfoDAO) {
		this.receiptInfoDAO = receiptInfoDAO;
	}

	@Override
	public void AddReceiptInfo(ReceiptInfo receiptinfo) {
		// TODO Auto-generated method stub
		receiptInfoDAO.AddReceiptInfo(receiptinfo);
	}

	@Override
	public void UpdateReceiptInfo(ReceiptInfo receiptinfo) {
		// TODO Auto-generated method stub
		receiptInfoDAO.UpdateReceiptInfo(receiptinfo);
	}

	@Override
	public ReceiptInfo getReceiptInfoByImageId(int imageId) {
		// TODO Auto-generated method stub
		return receiptInfoDAO.getReceiptInfoByImageId(imageId);
	}

	@Override
	public boolean doOcr(Image image) {
		// TODO Auto-generated method stub
				//code for generating OCR from image
		
				String ocredOutput="Demo ocred output";
				
				//====END====
				//code for creating receiptInfo object
				
				ReceiptInfo receipt=ReceiptScanner.ReceiptProcess(image);
				
				AddReceiptInfo(receipt);
				
				//====END====
				return true;
	}

	

}
