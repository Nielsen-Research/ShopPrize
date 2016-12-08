package com.bean;

public class BatchInfo {
	
	int batchid;
	double duration;//in miliseconds
	int numberOfImageProcessed;
	int numberOfGoodImage;
	int numberOfBadImage;
	int numberOFImageRejected;
	public int getBatchid() {
		return batchid;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public int getNumberOfImageProcessed() {
		return numberOfImageProcessed;
	}
	public void setNumberOfImageProcessed(int numberOfImageProcessed) {
		this.numberOfImageProcessed = numberOfImageProcessed;
	}
	public int getNumberOfGoodImage() {
		return numberOfGoodImage;
	}
	public void setNumberOfGoodImage(int numberOfGoodImage) {
		this.numberOfGoodImage = numberOfGoodImage;
	}
	public int getNumberOfBadImage() {
		return numberOfBadImage;
	}
	public void setNumberOfBadImage(int numberOfBadImage) {
		this.numberOfBadImage = numberOfBadImage;
	}
	public int getNumberOFImageRejected() {
		return numberOFImageRejected;
	}
	public void setNumberOFImageRejected(int numberOFImageRejected) {
		this.numberOFImageRejected = numberOFImageRejected;
	}
	
	

}
