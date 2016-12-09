<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp" />  
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a class="active" href="dashboard.html">Dashboard</a></li>
			  <li><a href="viewAllUser.html">User</a></li>
			  <li><a href="qualityCheckHome.html">Quality Check</a></li>
			  <li><a href="kpihome.html">KPI</a></li>
			 
			</ul>
		</nav>

	  <div class="main-content">
		  <div class="left-side-content">
				<ul>
				  <li><a  class="active" href="createBatch.html">Create Process</a></li>
				  <li><a  href="viewAllBatch.html">View All Process </a></li>
				 
				</ul>
		 </div>
		 <div class="right-side-content">
		  <h3>Create Batch Process</h3>
			<div class="col-lg-8">
			<form  method="post" action="createBatch"  enctype="multipart/form-data">
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Process Name</label>
                  <input class="form-control" id="processName" name="processName" type="text" >
                </div>
				<div class="form-group">
                  <label class="control-label" for="focusedInput">Image Location</label>
                  <input class="form-control" type="file" multiple="" directory="" webkitdirectory="" mozdirectory="" id="files" name="files">
                	

				  
                </div>
				
				
				<div class="form-group" style="float:right">
				      <button type="reset" class="btn btn-default">Reset</button>
                      <button type="submit" class="btn btn-primary">Start Process</button>
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