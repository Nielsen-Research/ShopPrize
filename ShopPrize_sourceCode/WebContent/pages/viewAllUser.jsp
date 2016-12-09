<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp" />  
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a class="active" href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
			  <c:if test="${role == 'ADMIN'}">
			  <li><a class="active" href="${pageContext.request.contextPath}/userHome">User</a></li>
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
	  <div class="left-side-content">
				<ul>
				
				   <li><a  href="${pageContext.request.contextPath}/userHome">Create User</a></li>
				  <li><a  class="active" href="${pageContext.request.contextPath}/viewAllUser">View Users</a></li>
				 
				</ul>
		 </div>
	    <div class="right-side-content">
		<h3>View All User</h3>
		<table  id="imageTable" class="table table-hover">
			<thead>
			  <tr>
				<th>UserId</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Role</th>
				<th>Status</th>
				<th></th>
			  </tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${userlist}">
   				<tr>
				<td>${user.userid}</td>
				<td>${user.fname}</td>
				<td>${user.lname}</td>
				<td>${user.role}</td>
				<td>${user.status}</td>
				<td><a href="${pageContext.request.contextPath}/modifyUser/${user.userid}"><i class="fa fa-edit" style="font-size:20px;color:#3498db"></i></a><span class="space"/><a href="${pageContext.request.contextPath}/deleteUser/${user.userid}"><i class="fa fa-remove" style="font-size:24px;color:red"></i></a></td>
			  </tr>
			</c:forEach>
			  
			 
			</tbody>
  </table>
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
    
    
    
    
});
 

 </script>
</body>
</html>