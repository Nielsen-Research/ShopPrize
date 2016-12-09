package com.bean;

import java.util.List;

public class ReceiptInfo {
	
	int receiptInfoId;
	int imageId;
	String receiptId;
	String purchaseDate;
	String purchaseTime;
	int chainId;
	String storeName;
	String storePhone;
	String storePhone2;
	String address;
	String totalAmount;
	String discountDescription;
	String discount;
	String purchaseCode;
	List<Item> itemList;
	String totalNoOfItem;
	String ocredOutput;
	
	public int getReceiptInfoId() {
		return receiptInfoId;
	}
	public void setReceiptInfoId(int receiptInfoId) {
		this.receiptInfoId = receiptInfoId;
	}
	
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	public int getChainId() {
		return chainId;
	}
	public void setChainId(int chainId) {
		this.chainId = chainId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public String getStorePhone2() {
		return storePhone2;
	}
	public void setStorePhone2(String storePhone2) {
		this.storePhone2 = storePhone2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public String getTotalNoOfItem() {
		return totalNoOfItem;
	}
	public void setTotalNoOfItem(String totalNoOfItem) {
		this.totalNoOfItem = totalNoOfItem;
	}
	public String getOcredOutput() {
		return ocredOutput;
	}
	public void setOcredOutput(String ocredOutput) {
		this.ocredOutput = ocredOutput;
	}
	
	
	
}
