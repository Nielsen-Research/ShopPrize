<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
							<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Receipt Details</h4>
					        </div>
					        <div class="modal-body" style="overflow: scroll;height:450px;">
					        			
											<img  class="image" id="image"  style="height:300px; width:300px;" src="${image.imageLocation}"/>
										
								
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Receipt Id</label>
						                  <input class="form-control" id="receiptId"  name="receiptId" type="text" value="${receiptInfo.receiptId}"readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Purchase Date</label>
						                  <input class="form-control" id="purchaseDate" name="purchaseDate" type="text" value="${receiptInfo.purchaseDate}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Purchase Time</label>
						                  <input class="form-control" id="purchaseTime" name="purchaseTime" type="text" value="${receiptInfo.purchaseTime}" readonly>
						                </div>
						                <div class="form-group">
						                  <label class="control-label" for="focusedInput">Purchase Code</label>
						                  <input class="form-control" id="purchaseCode" name="purchaseCode" type="text" value="${receiptInfo.purchaseCode}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Store Name</label>
						                  <input class="form-control" id="storeName" name="storeName" type="text" value="${receiptInfo.storeName}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Store Phone #1</label>
						                  <input class="form-control" id="storePhone1" name="storePhone1" type="text" value="${receiptInfo.storePhone}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Store Phone #2</label>
						                  <input class="form-control" id="storePhone2" name="storePhone2" type="text" value="${receiptInfo.storePhone2}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Address</label>
						                  <input class="form-control" id="address" name="address" type="text" value="${receiptInfo.address}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Total Amount</label>
						                  <input class="form-control" id="totalAmount" name="totalAmount" type="text" value="${receiptInfo.totalAmount}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Discount</label>
						                  <input class="form-control" id="discount" name="discount" type="text" value="${receiptInfo.discount}" readonly>
						                </div>
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Discount Description</label>
						                  <input class="form-control" id="discountDesc" name="discountDesc" type="text" value="${receiptInfo.discountDescription}" readonly>
						                </div>
									<div class="form-group">
						                  <label class="control-label" for="focusedInput">Total No. of Item</label>
						                  <input class="form-control" id="totalNoOfItem" name="totalNoOfItem"  type="text" value="${receiptInfo.totalNoOfItem}" readonly>
						                </div> 
										<h4>Items Details</h4>
										<div class="items">
										<c:forEach items="${receiptInfo.itemList}" var="item">
										<div class="item">
										<div class="element" style="width:18%;float:left; margin-left:5px;">
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Item Description</label>
						                  <input class="form-control" id="itemDesc[]" name="itemDesc[]" type="text" value="${item.itemDescription}" readonly>
						                </div>
										</div>
										<div class="element" style="width:18%; float:left;margin-left:5px;">
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Item Quantity</label>
						                  <input class="form-control" id="itemQty[]" name="itemQty[]" type="text" value="${item.itemQuantity}" readonly>
						                </div>
										</div>
										<div class="element" style="width:18%; float:left;margin-left:5px;">
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Item Total</label>
						                  <input class="form-control" id="itemTotal[]" name="itemTotal[]" type="text"value="${item.itemTotal}" readonly>
						                </div>
										</div>
										<div class="element" style="width:18%; float:left;margin-left:5px;">
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Discount Description</label>
						                  <input class="form-control" id="itemDiscountDesc[]" name="itemDiscountDesc[]" type="text" value="${item.rawItemDiscountDesc}" readonly>
						                </div>
										</div>
										<div class="element" style="width:18%; float:left;margin-left:5px;">
										<div class="form-group">
						                  <label class="control-label" for="focusedInput">Item Discount</label>
						                  <input class="form-control" id="itemDiscount[]" name="itemDiscount[]" type="text" value="${item.rawItemDiscount}" readonly>
						                </div>
										</div>
										
						                </div>
						                </c:forEach>
										</div>
										
										
								    
									
								
							
					        
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/wheelzoom.js"></script>
  	<script>
 $(document).ready(function() {
	 wheelzoom(document.querySelector('img.image'));
 } );
	 </script>
</body>  
</html>