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
			
			  <li><a href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			  <c:if test="${role == 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			  </c:if>
			 
			</ul>
		</nav>

	  <div class="main-content">
		
		
		<div class="total-dashboard"> 
			<div class="panel panel-default">
            <div class="panel-heading">
                Dashboard
				
            </div>
            <div class="panel-body"> 
		
	  <div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-thumbs-o-up fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="pendingImages"></span></div>
											<div>Pending Image</div>
										</div>
									</div>
								</div>
								<a href="goodImage.html">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
					
					<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-thumbs-o-down fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="reviewPending"></span></div>
											<div>Pending Approval</div>
										</div>
									</div>
								</div>
								<a href="badImage.html">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-check-circle-o fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="approvedImages"></span></div>
											<div>Approved Image</div>
										</div>
									</div>
								</div>
								<a href="approvedImage.html">
									<div class="panel-footer">
										<span class="pull-left">View Details</span>
										<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
										<div class="clearfix"></div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-3">
											<i class="fa fa-bitbucket fa-5x"></i>
										</div>
										<div class="col-xs-9 text-right">
											<div style="font-size: 30px;"><span id="rejectedImages"></span></div>
											<div>Rejected Image</div>
										</div>
									</div>
								</div>
								<a href="rejectedImage.html">
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
	</div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
 <script>
 $(document).ready(function() {
	 var userid=${userid};
	 	getPendingImages(userid);
		 getRejectedImages(userid);
		 getApprovedImages(userid);
		 getReviewPendingImages(userid);
 	setInterval(function(){
			
 		 getPendingImages(userid);
 		 getRejectedImages(userid);
 		 getApprovedImages(userid);
 		 getReviewPendingImages(userid);
		}, 20000);
 
} );
 
 function getPendingImages(userid)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getPendingImages',
          data:{userId : userid},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#pendingImages").text(data); 
             
             
          }
      });

 }
 function getRejectedImages(userid)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getPendingImages',
          data:{userId : userid},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#rejectedImages").text(0); 
             
             
          }
      });

 }
 function getApprovedImages(userid)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getPendingImages',
          data:{userId : userid},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#approvedImages").text(0); 
             
             
          }
      });

 }
 function getReviewPendingImages(userid)
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getPendingImages',
          data:{userId : userid},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#reviewPending").text(0); 
             
             
          }
      });

 }
 </script>
</body>
</html>