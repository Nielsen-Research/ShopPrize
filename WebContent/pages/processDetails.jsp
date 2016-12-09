<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp" />  
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a class="active" href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
			  <c:if test="${role == 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/userHome">User</a></li>
			  </c:if>
			 <c:if test="${role != 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			 </c:if>
			  <c:if test="${role == 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/kpiHome">KPI</a></li>
			 </c:if>
			</ul>
		</nav>
	  <div class="main-content">
		
		<div class="total-dashboard"> 
		<div class="panel panel-default">
	            <div class="panel-heading">
	                Process ID:${batchId}
					<span style="float:right;width: 100px; height:25px;">
					<a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/processflow">Process FLow</a>
					</span>
					<span style="float:right;width: 100px; height:25px;">
						<a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/viewAllBatch">View Batch</a>
					</span>
	            </div>
            <div class="panel-body">
		
				  
				  <div  class="col-lg-3 col-md-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-thumbs-o-up fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="goodImage"></span></div>
											<div>Good Image</div>
										</div>
									</div>
								</div>
								<a id="goodImageDetail" href="">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
					
					<div class="col-lg-3 col-md-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-thumbs-o-down fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="badImage"></span></div>
											<div>Bad Image</div>
										</div>
									</div>
								</div>
								<a id="badImageDetail" href="">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						
						<div class="col-lg-2 col-md-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-exclamation-circle fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="pendingImage"></span></div>
											<div>Pending Image</div>
										</div>
									</div>
								</div>
								<a id="pendingImageDetail" href="">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						
						<div class="col-lg-2 col-md-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-check-circle-o fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="approvedImage"></span></div>
											<div> Approved Image</div>
										</div>
									</div>
								</div>
								<a id="approvedImageDetail" href="">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-2 col-md-4">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-bitbucket fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="rejectedImage"></span></div>
											<div>Rejected Image</div>
										</div>
									</div>
								</div>
								<a id="rejectedImageDetail" href="">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
				</div>
				</div>
		</div>		
		  
		
	  </div>
	  
	  <footer class="main-footer">
	  <p>Copyright &copy; 2016 <a href="#">TCS</a></p>
	  </footer>
	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
 <script>
 $(document).ready(function() {
	 			var batchId=${batchId};
	 			$("a#badImageDetail").attr("href",'${pageContext.request.contextPath}/badImage/'+batchId);
            	$("a#goodImageDetail").attr("href",'${pageContext.request.contextPath}/goodImage/'+batchId);
            	$("a#approvedImageDetail").attr("href",'${pageContext.request.contextPath}/approvedImage/'+batchId);
            	$("a#rejectedImageDetail").attr("href",'${pageContext.request.contextPath}/rejectedImage/'+batchId);
            	$("a#pendingImageDetail").attr("href",'${pageContext.request.contextPath}/pendingImage/'+batchId);
            	 getGoodImageCountByBatch(batchId);
            	 getBADImageCountByBatch(batchId);
            	 getApprovedImageCountByBatch(batchId);
            	 getRejectedImageCountByBatch(batchId);
            	 getPendingImageCountByBatch(batchId)
            	 setInterval(function(){
            			
            		 getGoodImageCountByBatch(batchId);
            		 getBADImageCountByBatch(batchId);
            		 getApprovedImageCountByBatch(batchId);
            		 getRejectedImageCountByBatch(batchId);
            		 getPendingImageCountByBatch(batchId)
            		
            		}, 5000);
             
        
     });
	
	
	

 function getGoodImageCountByBatch(batchId)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getImageCountByClassification',
          data:{classification : "GOOD",batchId: batchId},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#goodImage").text(data); 
             
             
          }
      });

 }
 function getBADImageCountByBatch(batchId)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getImageCountByClassification',
          data:{classification : "BAD",batchId: batchId},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#badImage").text(data); 
             
             
          }
      });

 }
 function getApprovedImageCountByBatch(batchId)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getImageCountByStatus',
          data:{status : "APPROVED",batchId: batchId},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#approvedImage").text(data); 
             
             
          }
      });

 }
 function getPendingImageCountByBatch(batchId)
 { 
	 $.ajax({
		  
		 	type : "POST",
		          url : '${pageContext.request.contextPath}/getImageCountByStatus',
		          data:{status : "PENDING",batchId: batchId},
		          success : function(data) {
		         	console.log("SUCCESS: ", data);
		             $("#pendingImage").text(data); 
		             
		             
		          }
		      });

 }
 function getRejectedImageCountByBatch(batchId)
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getImageCountByStatus',
    data:{status : "REJECTED",batchId: batchId},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#rejectedImage").text(data); 
             
             
          }
      });

 }



 
 </script>
</body>
</html>