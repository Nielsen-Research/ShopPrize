package com.ui;

import com.fasterxml.jackson.annotation.JsonView;
import com.util.Views;

public class BatchUI {
	
	
	@JsonView(Views.Public.class)
	int processId;
	@JsonView(Views.Public.class)
	String startDate;
	@JsonView(Views.Public.class)
	String endDate;
	@JsonView(Views.Public.class)
	String duration;
	@JsonView(Views.Public.class)
	int totalImage;
	@JsonView(Views.Public.class)
	int totalProcessedImage;
	@JsonView(Views.Public.class)
	String status;
	
	public int getTotalImage() {
		return totalImage;
	}
	public void setTotalImage(int totalImage) {
		this.totalImage = totalImage;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getTotalProcessedImage() {
		return totalProcessedImage;
	}
	public void setTotalProcessedImage(int totalProcessedImage) {
		this.totalProcessedImage = totalProcessedImage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
