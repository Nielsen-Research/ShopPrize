package com.service;

import java.util.List;

import com.bean.Image;

public interface ImageService {
	
	public void getImages(int batchId);
	public List<Image> getImagesByClassification(String classification,int batchId);
	public void getCurrentStatus(int imageId);
	public int getImageCountByClassification(String Classification,int batchId);
	public List<Image> getImagesByStage(int stageId,int batchId);
	public int getImageCountByStage(int stageId,int batchId);
	public int createImage(Image image);
	public void updateImage(Image image);
	public boolean updateClassification(int imageId,String Classification);
	public boolean updateStage(int imageId,int stageId);
	public Image getImageById(int imageId);
	public List<Image> getImagesbyBatchAndStatus(int batchId,String status);
}
