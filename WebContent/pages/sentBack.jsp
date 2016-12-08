<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<title>Cashless property</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/demo.css">
</head>
<body>
	<div class="wrapper">
		<header class="main-header">
			<div class="header-left">
				<h1>CASHLESS PROPERTY</h1>
			</div>
			<div class="header-right">
			 <span class="welcomeName">Welcome ${name}</span><span class="space"/><a href="${pageContext.request.contextPath}/logout">Logout</a>
			</div>
			
		</header>
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a class="active" href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
			  <li><a href="${pageContext.request.contextPath}/userHome">User</a></li>
			  <li><a href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			 
			</ul>
		</nav>

	  <div class="main-content">
	  <div class="left-side-content">
				<ul>
				  <li><a  href="qualityCheckHome.html">Pending Image</a></li>
				  <li><a   class="active" href="reviewed.html">Reviewed and sent back</a></li>
				  <li><a  href="checkstatus.html">Status</a></li>
				 
				</ul>
		 </div>
	    <div class="right-side-content">
		<h3>Sent Back</h3>
		<table  id="imageTable" class="table table-hover">
			<thead>
			  <tr>
				<th>Image Name</th>
				<th>process Id</th>
				<th>Image</th>
				<th></th>
			  </tr>
			</thead>
			<tbody>
			  <tr>
				<td>reciept1</td>
				<td>12345</td>
				<td><img src="#"/></td>
				<td><a href="reviewImage.html"><i class="fa fa-file-text-o" style="font-size:24px;color:#3498db"></i></a></td>
			  </tr>
			  <tr>
				<td>reciept1</td>
				<td>12345</td>
				<td><img src="#"/></td>
				<td><a href="reviewImage.html"><i class="fa fa-file-text-o" style="font-size:24px;color:#3498db"></i></a></td>
			  </tr>
			  <tr>
				<td>reciept1</td>
				<td>12345</td>
				<td><img src="#"/></td>
				<td><a href="reviewImage.html"><i class="fa fa-file-text-o" style="font-size:24px;color:#3498db"></i></a></td>
			  </tr>
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
} );
 </script>
</body>
</html>