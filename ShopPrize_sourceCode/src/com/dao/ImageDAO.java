package com.dao;

import java.util.List;

import com.bean.Image;

public interface ImageDAO {
	
	public int createImage(Image image);
	public int updateImage(Image image);
	public List<Image> viewAllImage();
	public Image viewImage(int imageId);
	public List<Image> getImages(int batchId);
	public List<Image> getImagesByClassification(String classification,int batchId);
	public String getCurrentStatus(int imageId);
	public int getImageCountByClassification(String classification,int batchId);
	public List<Image> getImagesByStage(int stageId,int batchId);
	public int getImageCountByStage(int stageId,int batchId);
	public boolean updateClassification(int imageId,String Classification);
	public boolean updateStage(int imageId,int stageId);
	public List<Image> getImagesbyBatchAndStatus(int batchId,String status);
}
