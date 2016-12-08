package com.service.impl;

import java.util.List;

import com.dao.BatchDAO;
import com.service.BatchService;
import com.bean.Batch;

public class BatchServiceImpl implements BatchService {
	
	private BatchDAO batchDAO;
	
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}

	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	

	@Override
	public void getTotalImageCount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getDuration() {
		// TODO Auto-generated method stub

	}

	@Override
	public int createBatch(Batch batch) {
		// TODO Auto-generated method stub
		return batchDAO.createBatch(batch);
	}

	@Override
	public int updateBatch(Batch batch) {
		// TODO Auto-generated method stub
		batchDAO.updateBatch(batch);
		return 0;
	}

	@Override
	public List<Batch> viewAllBatch() {
		// TODO Auto-generated method stub
		return batchDAO.viewAllBatch();
	}

	@Override
	public Batch viewBatch(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Batch viewCurrentBatch() {
		// TODO Auto-generated method stub
		return batchDAO.viewCurrentBatch();
	}

}
