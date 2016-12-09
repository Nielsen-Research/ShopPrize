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
			  <li><a class="active" href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			  </c:if>
			    <c:if test="${role == 'ADMIN'}">
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			 </c:if>
			</ul>
		</nav>

	  <div class="main-content">
	 <div class="view-content">
		
  <h3>Pending Image</h3>
		<table  id="imageTable" class="table table-hover">
			<thead>
			  <tr>
			 	<th>Image Id</th>
				<th>Image Name</th>
				<th>process Id</th>
				<th>Classification</th>
				<th></th>
			  </tr>
			</thead>
			<tbody>
			
			<c:forEach var="image" items="${imageList}">
			
			  <tr>
				<td>${image.imageId}</td>
				<td>${image.imageName}</td>
				<td>${image.batchId}</td>
				<td>${image.classification}</td>
				<c:if test="${role == 'REVIEWER'}">
				<td><a href="${pageContext.request.contextPath}/verifyImage/${image.imageId}"><i class="fa fa-pencil-square-o" style="font-size:20px;color:#3498db"></i></a></td>
				</c:if>
				<c:if test="${role == 'EDITOR'}">
					<c:if test="${image.classification == 'GOOD'}">
						<td><a href="${pageContext.request.contextPath}/reviewImage/${image.imageId}"><i class="fa fa-pencil-square-o" style="font-size:20px;color:#3498db"></i></a></td>
					</c:if>
					<c:if test="${image.classification == 'BAD'}">
						<td><a href="${pageContext.request.contextPath}/dataCapture/${image.imageId}"><i class="fa fa-pencil-square-o" style="font-size:20px;color:#3498db"></i></a></td>
					</c:if>
				</c:if>
			  </tr>
			  </c:forEach>
			</tbody>
  </table>
	  </div>
	  </div>
	  <footer class="main-footer">
	  <p>Copyright &copy; 2016 <a href="#">TCS</a></p>
	  </footer>
	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
 <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>	
 <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>	
 

 <script>
 $(document).ready(function() {
    $('#imageTable').DataTable();
} );
 </script>
</body>
</html>