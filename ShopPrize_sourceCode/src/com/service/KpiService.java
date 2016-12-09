package com.service;

import java.util.List;

import com.bean.KPI;

public interface KpiService {
	
	public void createKPI(KPI kpi);
	public List<KPI> viewAllKpi();
	public KPI viewKpi(int kpiId);
	/*=========KPI Utility Function========
	 * 
	 * 1.get image status
	 * 2.is ocred
	 * 3.is full/partial transcription
	 * 4.get chain name
	 * 5.calculate time spend
	 * 6.calculate no of char modified
	 * 
	 * */
	
	public String getChainName(int chainId);
	public String calculateTimeSpend(int imageId);
	public int characterModifiedOrEntered(int imageId);
	public String isOcred(int imageId);
	public String getTranscription(int imageId);
	
	
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
