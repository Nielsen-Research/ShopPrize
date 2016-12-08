package com.service;

import com.bean.Image;
import com.bean.ReceiptInfo;

public interface ReceiptInfoService {
	
	public void AddReceiptInfo(ReceiptInfo receiptinfo);
	public void UpdateReceiptInfo(ReceiptInfo receiptinfo);
	public ReceiptInfo getReceiptInfoByImageId(int imageId);
	public boolean doOcr(Image image);
	
}
