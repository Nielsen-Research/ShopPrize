package com.dao;

import java.util.List;

import com.bean.KPI;

public interface KpiDAO {
	
	public void createKpi(KPI kpi);
	public KPI getKpi(int KpiId);
	public void updateKpi(KPI kpi);
	public List<KPI> getAllKPIByBatch(int batchId);
	public List<KPI> getAllKPI();
	
	public int getOcredCount();
	public int getApprovedImageCount();
	public int getRejectedImageCount();
	public int getChainNameCount();
	public int getFullTranscriptionCount();
	public int getPartialTranscriptionCount();
	public int getTotalImageCount();
	public int getBadImageCount();
	public int getGoodImageCount();
	public int getCFCount(float start, float end);
	public int getChainIdentifiedCount();

}
