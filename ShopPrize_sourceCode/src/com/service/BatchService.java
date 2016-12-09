package com.service;

import java.util.List;

import com.bean.Batch;

public interface BatchService {
	
	public int createBatch(Batch batch);
	public int updateBatch(Batch batch);
	public void getTotalImageCount();
	public void getDuration();
	public List<Batch> viewAllBatch();
	public Batch viewBatch(int batchId);
	public Batch viewCurrentBatch();
	

}
