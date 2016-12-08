package com.bean;

public class Stage {
	
	int stageid;
	String stage; 
	
	/* 1. Pre Processing
	 * 2. Image Classification
	 * 3. OCR
	 * 4. Image Validation
	 * 5. Manual Data Capture
	 * 6. Image Verification
	 * 7. Template Generation
	 */
	
	public int getStageid() {
		return stageid;
	}
	public void setStageid(int stageid) {
		this.stageid = stageid;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	

}
