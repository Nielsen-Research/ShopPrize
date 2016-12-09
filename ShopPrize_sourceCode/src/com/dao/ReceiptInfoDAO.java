package com.dao;

import com.bean.ReceiptInfo;

public interface ReceiptInfoDAO {

	public void AddReceiptInfo(ReceiptInfo receiptinfo);
	public void UpdateReceiptInfo(ReceiptInfo receiptinfo);
	public ReceiptInfo getReceiptInfoByImageId(int imageId);
	
}
