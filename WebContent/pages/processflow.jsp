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
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			 
			</ul>
		</nav>

	  <div class="main-content">
	  
			<div class="flowchart-container">
					  <div class="section">
				<div class="gap-top">
				</div>
				
				<div class="gap-top">
				</div>
				<div  id="node3" class="node3">
				
				<div class="top"><span class="badge">2</span><span>Good Image</span></div>
				<div class="bottom"><i class="fa fa-thumbs-up"></i></div>
				
				</div>
				<div id="node4" class="node4">
				
				<div class="top"><span class="badge">2</span><span>OCR Extraction</span></div>
					<div class="bottom"><i class="fa fa-magic"></i></div>
				
				</div>
				<div class="gap-top">
				</div>
				<div class="gap-top">
				</div>

			</div>

			<div class="section">
				<div id="node1" class="node1">
					
					<div class="top"><span class="badge">2</span><span>Pre-Processing</span></div>
					<div class="bottom"><i class="fa fa-cog"></i>	</div>
				
				</div>
				
				<div id="node2" class="node2">
				
				<div class="top"><span class="badge">2</span><span>Image Classification</span></div>
					<div class="bottom"><img style="-webkit-filter: invert(100%); filter: invert(100%);" src="${pageContext.request.contextPath}/resources/img/category_settings.png"/></div>
				
				</div>
				<div class="gap">
				</div>
				<div id="node7"  class="node7">
			
				<div class="top"><span class="badge">2</span><span>Data Validation</span></div>
				<div class="bottom"><i class="fa fa-file-text-o"></i></div>
				
				
				</div>
				<div id="node8" class="node8">
			
				<div class="top"><span class="badge">2</span><span>Data Varification</span></div>
				<div class="bottom"><img style="-webkit-filter: invert(100%); filter: invert(100%);" src="${pageContext.request.contextPath}/resources/img/document_text_accept.png"/></div>
				
				</div>
				<div id="node9" class="node9">
				
				<div class="top"><span class="badge">2</span><span>XML Generation</span></div>
				<div class="bottom"><img style="-webkit-filter: invert(100%); filter: invert(100%);" src="${pageContext.request.contextPath}/resources/img/Untitled.png"/></div>
				
				</div>
				
			</div>

			<div class="section">
				<div class="gap-bottom">
				</div>
				
				<div class="gap-top">
				</div>
				<div id="node5" class="node5">
				
				<div class="top"><span class="badge">2</span><span>Bad Image</span></div>
				<div class="bottom"><i class="fa fa-thumbs-down"></i></div>
				
				</div>
				<div id="node6"  class="node6">
				
				<div class="top"><span class="badge">2</span><span>Data Capture</span></div>
				<div class="bottom"><i class="fa fa-edit"></i></div>
				
				</div>
				<div class="gap-bottom">
				</div>
				<div class="gap-bottom">
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
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jsPlumb-2.1.7.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flowchart.js"></script>
	
</body>
</html>