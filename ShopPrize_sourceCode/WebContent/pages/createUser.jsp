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
				  <li><a class="active" href="${pageContext.request.contextPath}/userHome">Create User</a></li>
				  <li><a  href="${pageContext.request.contextPath}/viewAllUser">View Users</a></li>
				
				 
				</ul>
		 </div>
		 <div class="right-side-content">
		 <h3>Create User</h3>
			<div class="col-lg-8">
			<form method="post" action="createUser">
				
				<div class="form-group">
                  <label class="control-label" for="focusedInput">First Name</label>
                  <input class="form-control" id="fname" name="fname" type="text" >
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Last Name</label>
                  <input class="form-control" id="lname" name="lname" type="text" >
                </div>
                <div class="form-group">
                  <label class="control-label" for="focusedInput">Password</label>
                  <input class="form-control" id="password" name="password" type="password" >
                </div>
				 <div class="form-group">
                    <label for="select" class="control-label">ROLE</label>
                    
                      <select class="form-control" id="role" name="role">
                      <option value="select">--Select Role--</option>
                        <option value="ADMIN">Admin</option>
                        <option value="EDITOR">Editor</option>
                        <option value="VALIDATOR">Validator</option>
                        <option value="REVIEWER">Reviewer</option>
                      </select>
                    
                  </div>
				<div class="form-group" style="float:right">
				      <button type="reset" class="btn btn-default">Reset</button>
                      <button type="submit" class="btn btn-primary">Create</button>
                </div>
                 
			<form>
			</div>
		</div>
	  </div>
	  
	  <footer class="main-footer">
	  <p>Copyright &copy; 2016 <a href="#">TCS</a></p>
	  </footer>
	</div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
 
</body>
</html>