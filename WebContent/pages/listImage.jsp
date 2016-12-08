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
	   <div class="view-content">
	   <h3>${heading}</h3>
						<table  id="imageTable" class="table table-hover">
							<thead>
							  <tr>
							  	<th>Image Id</th>
								<th>Image Name</th>
								<th>process Id</th>
								<th>Quality</th>
								
								<th></th>
							  </tr>
							</thead>
							<tbody>
						
							<c:forEach var="imageObj" items="${imageList}">
							
							  <tr>
							  	<td>${imageObj.imageId}</td>
							  	<td>${imageObj.imageName}</td>
								<td>${imageObj.batchId}</td>
								<td>${imageObj.classification}</td>
								<td><a  class="modal_workflow" href="${pageContext.request.contextPath}/modal_workflow/${imageObj.imageId}" data-toggle="modal" data-target="#myModal">Workflow</a>
								 <c:if test="${flag == 'TRUE'}">
								<a  class="modal_workflow" href="${pageContext.request.contextPath}/imageDetails/${imageObj.imageId}" data-toggle="modal" data-target="#myModal1">Details</a>
								</c:if>
								</td>
							  </tr>
							  </c:forEach>
							 
							</tbody>
				  </table>
				   <div class="modal fade" id="myModal" role="dialog">
					    <div class="modal-dialog modal-lg">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        
					        
					      </div>
					      
					    </div>
					  </div>
					  <div class="modal fade" id="myModal1" role="dialog">
					    <div class="modal-dialog modal-lg">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        
					        
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
 <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>	
 <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>	
<script>
 $(document).ready(function() {
	
    $('#imageTable').DataTable({
    	ordering: true
	
	});
    $('.modal').on('hidden.bs.modal', function(e)
    	    { 
    	        $(this).removeData();
    	    }) ;
  
} );
 </script>
</body>
</html>