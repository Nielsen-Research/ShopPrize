<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp" />  
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a  href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
			  <c:if test="${role == 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/userHome">User</a></li>
			  </c:if>
			    <c:if test="${role != 'ADMIN'}">
			  <li><a  href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			  </c:if>
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			 
			</ul>
		</nav>

	  <div class="main-content">
		
		 <div class="view-content">
		 <h3>Data Capture</h3>
			<div class="left-block">
			<img  class="image" id="image" src="${image.imageLocation}">
			</div>
			<div class="right-block">
			<h4>Reciept Data</h4>
			<form method="post" action="${pageContext.request.contextPath}/captureReceiptData">
			<input type="hidden" id="imageId" name="imageId" value="${image.imageId}">
			<input type="hidden" id="currentUser" name="currentUser" value="${userid}">
			<input type="hidden" id="nextUser" name="nextUser" value="${workflow.reviewBy}">
			<input type="hidden" id="workflowId" name="workflowId" value="${workflow.workflowId}">
			<input type="hidden" id="remark" name="remark" value="">
			<input type="hidden" id="startTime" name="startTime" value="${startTime}">
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Receipt Id</label>
                  <input class="form-control" id="receiptId"  name="receiptId" type="text" >
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Purchase Date</label>
                  <input class="form-control" id="purchaseDate" name="purchaseDate" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Purchase Time</label>
                  <input class="form-control" id="purchaseTime" name="purchaseTime" type="text">
                </div>
                <div class="form-group">
                  <label class="control-label" for="focusedInput">Purchase Code</label>
                  <input class="form-control" id="purchaseCode" name="purchaseCode" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Store Name</label>
                  <input class="form-control" id="storeName" name="storeName" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Store Phone #1</label>
                  <input class="form-control" id="storePhone1" name="storePhone1" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Store Phone #2</label>
                  <input class="form-control" id="storePhone2" name="storePhone2" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Address</label>
                  <input class="form-control" id="address" name="address" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Total Amount</label>
                  <input class="form-control" id="totalAmount" name="totalAmount" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Discount</label>
                  <input class="form-control" id="discount" name="discount" type="text">
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Discount Description</label>
                  <input class="form-control" id="discountDesc" name="discountDesc" type="text">
                </div>
                <div class="form-group">
                  <label class="control-label" for="focusedInput">Total No. of Item</label>
                  <input class="form-control" id="totalNoOfItem" name="totalNoOfItem" type="text">
                </div>
				<button id="add" style="float:right">ADD ITEM</button>
				<h4>Items Details</h4>
				<div class="items">
				
				</div>
				
				<div class="form-group" style="float:right; margin-top:5px;">
				
                      <button type="reset" class="btn btn-default">Reset</button>
                      <button type="submit" class="btn btn-primary">Submit</button>
                </div>
			    
			</form>
			</div>
				<div class="workflow">
			
					<div class="panel panel-primary">
                                    <div class="panel-heading">
                                      
                                        <a data-toggle="collapse" href="#collapse1" style="color:white">Workflow</a>   
                                        
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">
										
											<table  id="imageTable" class="table table-hover">
														<thead>
														  <tr>
															<th>UserId</th>
															<th>Name</th>
															<th>Role</th>
															<th>Status</th>
															<th>Remarks</th>
															
														  </tr>
														</thead>
														<tbody>
														
														<c:forEach var="workflow" items="${workflowList}">
														  <tr>
															<td>${workflow.userId}</td>
															<td>${workflow.userName}</td>
															<td>${workflow.role}</td>
															<td>${workflow.status}</td>
															<td><textarea class="form-control" rows="1" readonly>${workflow.remark}</textarea></td>
															
														  </tr>
														 </c:forEach>
														  
														</tbody>
											  </table>
                                        
                                    </div>
									</div>
					</div>
								
				</div>
		 </div>
		  
	  </div>
	  
	  <footer class="main-footer">
	  <p>Copyright &copy; 2016 <a href="#">TCS</a></p>
	  </footer>
	</div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/wheelzoom.js"></script>
  <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>	
 <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>	
  <script>
  wheelzoom(document.querySelector('img.image'));
 $(document).ready(function() {
	
    $('#imageTable').DataTable({
	"searching": false,
	"ordering": false,
    "info":     false,
	"paging": false
	
	
	});var x = 1;
	var y= 1;
	  $("#add").click(function(e){
	  x++;
		e.preventDefault();
		var htmlElement='<div class="item">'
				+'<div class="element" style="width:18%;float:left; margin-left:5px;">'
				+'<div class="form-group">'
               + '<label class="control-label" for="focusedInput">Item Description</label>'
              +  '<input class="form-control" id="itemDesc[]" name="itemDesc[]" type="text">'
              +'</div>'
				+'</div>'
				+'<div class="element" style="width:18%; float:left;margin-left:5px;">'
				+'<div class="form-group">'
              +  '<label class="control-label" for="focusedInput">Item Quantity</label>'
              +  '<input class="form-control" id="itemQty[]" name="itemQty[]" type="text">'
              +'</div>'
				+'</div>'
				+'<div class="element" style="width:18%; float:left;margin-left:5px;">'
				+'<div class="form-group">'
              +  '<label class="control-label" for="focusedInput">Item Total</label>'
              +  '<input class="form-control" id="itemTotal[]" name="itemTotal[]" type="text">'
             +'</div>'
				+'</div>'
				+'<div class="element" style="width:18%; float:left;margin-left:5px;">'
				+'<div class="form-group">'
              +  '<label class="control-label" for="focusedInput">Discount Description</label>'
              +  '<input class="form-control" id="itemDiscountDesc[]" name="itemDiscountDesc[]" type="text">'
              +'</div>'
				+'</div>'
				+'<div class="element" style="width:18%; float:left;margin-left:5px;">'
				+'<div class="form-group">'
              +  '<label class="control-label" for="focusedInput">Item Discount</label>'
              +  '<input class="form-control" id="itemDiscount[]" name="itemDiscount[]" type="text">'
             + '</div>'
				+'</div>'
				+'<div class="element" style="width:5%; float:left;margin-left:5px; padding-top:30px;">'
				
				+ '<a class="remove"><i class="fa fa-remove" style="font-size:24px;color:red"></i></a>'
              
				+'</div>'
				+'</div>';
		
		$(".items").append(htmlElement);
	 
	  });
	 
	  $(".items").on("click",".remove", function(e){ //user click on remove text
      e.preventDefault(); $(this).parent('div').parent('div').remove(); x--;
  })
    
    
    
} );
 </script>
</body>
</html>