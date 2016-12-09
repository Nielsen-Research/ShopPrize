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
				 <li><a   href="${pageContext.request.contextPath}/kpiHome">Home</a></li>
				  <li><a  class="active" href="${pageContext.request.contextPath}/kpiInfo">Kpi Detail</a></li>
				 
				</ul>
		 </div>
		 <div class="right-side-content">
		 <h3>KPI Information</h3>
		<table  id="imageTable" class="table table-hover">
			<thead>
			  <tr>
				<th>Image Id</th>
				<th># char entered</th>
				<th># char modified</th>
				<th>Ocred</th>
				<th>Transcription</th>
				<th>Time Spend</th>
				<th>Status</th>
				<th></th>
			  </tr>
			</thead>
			<tbody>
			<c:forEach var="kpi" items="${kpiList}">
			
			  <tr>
				<td>${kpi.imageId}</td>
				<td>${kpi.enteredChar}</td>
				<td>${kpi.modifiedChar}</td>
				<td>${kpi.isOcred()}</td>
				<td>${kpi.transcription}</td>
				<td>${kpi.time}</td>
				<td>${kpi.status}</td>
				<td><a  class="modal_workflow" hidden href="${pageContext.request.contextPath}/modal_kpi/${kpi.kpiId}" data-toggle="modal" data-target="#myModal">Details</a></td>
			  </tr>
			  </c:forEach>
			 
			  
			</tbody>
  </table>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
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
    $('#imageTable').DataTable();
} );
 </script>
</body>
</html>