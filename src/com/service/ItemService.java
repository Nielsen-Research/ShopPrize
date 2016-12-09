package com.service;

import com.bean.Item;

public interface ItemService {
	
	public void addItem(Item item);
	public void updateItem(Item item);
	public void viewItem(int itemId);
	public void viewAllItem(int itemInfoId);
	public void deleteItem(int itemId);
}
