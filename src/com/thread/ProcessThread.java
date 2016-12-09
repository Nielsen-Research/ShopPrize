package com.thread;

import java.io.File;
import org.apache.commons.io.FileUtils;


import com.bean.Batch;
import com.bean.Image;
import com.bean.Workflow;
import com.ocr.ReceiptUtility;
import com.service.BatchService;
import com.service.ImageService;
import com.service.ReceiptInfoService;
import com.service.WorkflowService;



public class ProcessThread implements Runnable {
	
	 private Thread t;
	 private int processId;
	 private File[] images;
	 private String UPLOAD_DIRECTORY;
	 private ImageService imageService;
	 private WorkflowService workflowService;
	 private BatchService batchService;
	 private ReceiptInfoService receiptInfoService;
	 private String image_location;
	
	
	
	
	public ReceiptInfoService getReceiptInfoService() {
		return receiptInfoService;
	}

	public void setReceiptInfoService(ReceiptInfoService receiptInfoService) {
		this.receiptInfoService = receiptInfoService;
	}

	public BatchService getBatchService() {
		return batchService;
	}

	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}

	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	

	public String getImage_location() {
		return image_location;
	}

	public void setImage_location(String image_location) {
		this.image_location = image_location;
	}

	public File[] getImages() {
		return images;
	}

	public void setImages(File[] images) {
		this.images = images;
	}

	public String getUPLOAD_DIRECTORY() {
		return UPLOAD_DIRECTORY;
	}

	public void setUPLOAD_DIRECTORY(String uPLOAD_DIRECTORY) {
		UPLOAD_DIRECTORY = uPLOAD_DIRECTORY;
	}

	
	public void run() {
		
		for(File img : images){
			
				
			    
			    String name = new File(img.getName()).getName();
                try {
					//code for copy image
                	FileUtils.copyFile(img, new File(UPLOAD_DIRECTORY+image_location+"/"+img.getName()));
                	//===============
					 Image image=new Image();
                	 image.setBatchId(processId);
                	 image.setImageLocation(image_location+"/"+name);
                	 image.setImageName(name);
                	 image.setStageId(1);
                	 image.setStatus("INPROGRESS");
                	 int imageId=imageService.createImage(image);
                	 //function to identify image classification
                	 String classification=ReceiptUtility.ImgQualityCheck(UPLOAD_DIRECTORY+image.getImageLocation());
                	 imageService.updateClassification(imageId, classification);
                	 if(classification.equals("GOOD"))
                	 {
                		 
                		 System.out.println(image.getImageName());
                		 //pass image id to OCR function
                		 Image imageObj=imageService.getImageById(imageId);
                		
                		 boolean ocred=receiptInfoService.doOcr(imageObj);
                		 if(!ocred)
                		 {
                			 classification="BAD"; 
                		 }
                		 else{
                			 Workflow workflow=new Workflow();
                    		 workflow.setImageId(imageId);
                    		 int assign_to=workflowService.getUserIdByRole(2);
                    		 int review_by=workflowService.getUserIdByRole(4);
                    		 workflow.setAssignTo(assign_to);
                    		 workflow.setReviewBy(review_by);
                    		 workflow.setCurrent_user(assign_to);
                    		 workflow.setStatus("PENDING");
                    		 workflowService.startWorkflow(workflow);
                    		 imageService.updateStage(imageId, 5);
                		 }
                		 
                	 }
                	 if(classification.equals("BAD"))
                	 {
                		 Workflow workflow=new Workflow();
                		 workflow.setImageId(imageId);
                		 //get user for role EDITOR
                		 int assign_to=workflowService.getUserIdByRole(2);
                		 int review_by=workflowService.getUserIdByRole(4);
                		 workflow.setAssignTo(assign_to);
                		 workflow.setReviewBy(review_by);
                		 workflow.setCurrent_user(assign_to);
                		 workflow.setStatus("PENDING");
                		 workflowService.startWorkflow(workflow);
                		 imageService.updateStage(imageId, 5);
                	 }
                	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                	
         	}
		Batch batch=new Batch();
    	batch.setBatchId(processId);
    	batch.setStatus("COMPLETED");
		batchService.updateBatch(batch);
	 
	}
	   
	public void start () {
		if (t == null) {
	         t = new Thread (this, "batch");
	         t.start ();
	      }
		  
	     
	}
	
	

}
