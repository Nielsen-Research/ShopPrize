package com.service.impl;


import com.dao.ImageDAO;
import com.service.ImageService;

import java.util.List;

import com.bean.Image;

public class ImageServiceImpl implements ImageService {
	
	private ImageDAO imageDAO;
	
	

	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

	@Override
	public void getImages(int batchId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Image> getImagesByClassification(String classification, int batchId) {
		// TODO Auto-generated method stub
		return imageDAO.getImagesByClassification(classification, batchId);
	}

	@Override
	public void getCurrentStatus(int imageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getImageCountByClassification(String classification, int batchId) {
		
		// TODO Auto-generated method stub
		return imageDAO.getImageCountByClassification(classification, batchId);
	}

	@Override
	public List<Image> getImagesByStage(int stageId, int batchId) {
		
		// TODO Auto-generated method stub
		return imageDAO.getImagesByStage(stageId, batchId);
	}

	@Override
	public int getImageCountByStage(int stageId, int batchId) {
		// TODO Auto-generated method stub
			return imageDAO.getImageCountByStage(stageId, batchId);
	}

	@Override
	public int createImage(Image image) {
		// TODO Auto-generated method stub
		return imageDAO.createImage(image);
	}

	@Override
	public void updateImage(Image image) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateClassification(int imageId, String Classification) {
		// TODO Auto-generated method stub
		return imageDAO.updateClassification(imageId, Classification);
	}

	@Override
	public boolean updateStage(int imageId, int stageId) {
		// TODO Auto-generated method stub
		return imageDAO.updateStage(imageId, stageId);
	}

	@Override
	public Image getImageById(int imageId) {
		// TODO Auto-generated method stub
		return imageDAO.viewImage(imageId);
	}

	@Override
	public List<Image> getImagesbyBatchAndStatus(int batchId, String status) {
		// TODO Auto-generated method stub
		return imageDAO.getImagesbyBatchAndStatus(batchId, status);
	}

}
