package com.dao;

import java.util.List;

import com.bean.Batch;

public interface BatchDAO {
	
	public int createBatch(Batch batch);
	public boolean updateBatch(Batch batch);
	public List<Batch> viewAllBatch();
	public Batch viewBatch(int batchId);
	public Batch viewCurrentBatch();

}
