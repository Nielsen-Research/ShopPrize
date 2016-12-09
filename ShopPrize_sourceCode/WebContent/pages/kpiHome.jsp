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
			  <li><a href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			 </c:if>
			 <c:if test="${role == 'ADMIN'}">
			  <li><a class="active"href="${pageContext.request.contextPath}/kpiHome">KPI</a></li>
			 </c:if>
			</ul>
		</nav>

	  <div class="main-content">
		<div class="left-side-content">
				<ul>
				  <li><a  class="active" href="${pageContext.request.contextPath}/kpiHome">Home</a></li>
				  <li><a  href="${pageContext.request.contextPath}/kpiInfo">Kpi Detail</a></li>
				 
				</ul>
		 </div>
		 <div class="right-side-content">
		 </br>
		
		<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="totalReceipt"></span></div>
											<div>Receipts Processed</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						
			<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="goodImage"></span></div>
											<div>GOOD Image</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
			<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="badImage"></span></div>
											<div>BAD Image</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
			
			<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="approvedImage"></span></div>
											<div>Approved Image</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
			<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="rejectedImage"></span></div>
											<div>Rejected Image</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<!-- <div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="chainName"></span></div>
											<div>Chain Name</div>
										</div>
									</div>
								</div>
								
							</div>
						</div> -->
			<!-- <div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="fullTranscription"></span></div>
											<div>Full Transcription</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="partialTranscription"></span></div>
											<div>Partial Transcription</div>
										</div>
									</div>
								</div>
								
							</div>
						</div> -->
						
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="chainIdentified"></span></div>
											<div>Chain Identified</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						 <div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="ocred"></span></div>
											<div>OCRED</div>
										</div>
									</div>
								</div>
								
							</div>
						</div> 
						</div>
						<div class="right-side-content">
						<h3>OCR-Confidence Factor</h3>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="ocrcf0025">0</span></div>
											<div>0%-25%</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="ocrcf2650">0</span></div>
											<div>26%-50%</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="ocrcf5175">0</span></div>
											<div>51%-75%</div>
										</div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<div class="row">
										
										<div style="text-align:center">
											<div style="font-size: 30px;"><span id="ocrcf76100">0</span></div>
											<div>76%-100%</div>
										</div>
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
	
	 getApprovedImageCount();
	getOcredCount();
	 getRejectedImageCount();
	 getTotalImageCount();
	 getGoodImageCount();
	 getBadImageCount();
	 getChainNameCount();
	/*  getFullTranscriptionCount();
	 getPartialTranscriptionCount(); */
	 getChainIdentifiedCount();
	 getCF0025Count();
		 getCF2650Count();
		 getCF5175Count();
		 getCF76100Count();
	 
 	setInterval(function(){
			
 		 getApprovedImageCount();
 		 getOcredCount();
 		 getRejectedImageCount();
 		 getTotalImageCount();
 		 getGoodImageCount();
 		 getBadImageCount();
 		 getChainNameCount();
 		/*  getFullTranscriptionCount();
 		 getPartialTranscriptionCount(); */
 		 getChainIdentifiedCount();
 		 getCF0025Count();
 		 getCF2650Count();
 		 getCF5175Count();
 		 getCF76100Count();
		}, 20000);
 
} );
 
 function getOcredCount()
 { 
   $.ajax({
  
 	type : "POST",
          url : '${pageContext.request.contextPath}/getOcredCount',
         
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#ocred").text(data); 
             
             
          }
      });

 }
 function getApprovedImageCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getApprovedImageCount',
     success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#approvedImage").text(data); 
             
             
          }
      });

 }
 function getGoodImageCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getGoodImageCount',
     success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#goodImage").text(data); 
             
             
          }
      });

 }
 function getBadImageCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getBadImageCount',
     success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#badImage").text(data); 
             
             
          }
      });

 }
 function getTotalImageCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getTotalImageCount',
     success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#totalReceipt").text(data); 
             
             
          }
      });

 }
 function getRejectedImageCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getRejectedImageCount',
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#rejectedImage").text(data); 
             
             
          }
      });

 }
 function getChainNameCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getChainNameCount',
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#chainName").text(0); 
             
             
          }
      });

 }
 function getFullTranscriptionCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getFullTranscriptionCount',
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#fullTranscription").text(data); 
             
             
          }
      });

 }
 function getPartialTranscriptionCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getPartialTranscriptionCount',
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#partialTranscription").text(data); 
             
             
          }
      });

 }
 function getChainIdentifiedCount()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getChainIdentifiedCount',
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#chainIdentified").text(data); 
             
             
          }
      });

 }
 function getCF0025Count()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getCFCount',
 	data:{start : -1,end: 0.25},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#ocrcf0025").text(data); 
             
             
          }
      });

 }
 function getCF2650Count()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getCFCount',
 	data:{start : 0.25,end: 0.5},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#ocrcf2650").text(data); 
             
             
          }
      });

 }
 function getCF5175Count()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getCFCount',
 	data:{start : 0.5,end: 0.75},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#ocrcf5175").text(data); 
             
             
          }
      });

 }
 function getCF76100Count()
 { 
   $.ajax({
  
 	type : "POST",
 	url : '${pageContext.request.contextPath}/getCFCount',
 	data:{start : 0.75,end: 1},
          success : function(data) {
         	console.log("SUCCESS: ", data);
             $("#ocrcf76100").text(data); 
             
             
          }
      });

 }
 </script>
</body>
</html>