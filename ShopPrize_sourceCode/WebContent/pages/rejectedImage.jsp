<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp" />  
		<nav class="topnav">
			<ul class="topnav" id="myTopnav">
			  <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
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
	   <div class="view-content">
	   <h3>Rejected Images</h3>
						<table  id="imageTable" class="table table-hover">
							<thead>
							  <tr>
								<th>Image Name</th>
								<th>process Id</th>
								<th>Quality</th>
								<th>Status</th>
								<th></th>
							  </tr>
							</thead>
							<tbody>
							  <tr>
								<td>reciept 1</td>
								<td>123456</td>
								<td>good</td>
								<td>Pending validation</td>
								<td><a href="#">Workflow</a></td>
							  </tr>
							  <tr>
								<td>reciept 1</td>
								<td>123456</td>
								<td>good</td>
								<td>Pending Review</td>
								<td><a href="#">Workflow</a></td>
							  </tr>
							  <tr>
								<td>reciept 1</td>
								<td>123456</td>
								<td>good</td>
								<td>Pending validation</td>
								<td><a href="#">Workflow</a></td>
							  </tr>
							  <tr>
								<td>reciept 1</td>
								<td>123456</td>
								<td>good</td>
								<td>Rejected</td>
								<td><a href="#">Workflow</a></td>
							  </tr>
							   <tr>
								<td>reciept 1</td>
								<td>123456</td>
								<td>good</td>
								<td>Approved</td>
								<td><a href="#">Workflow</a></td>
							  </tr>
							</tbody>
				  </table>
				  
				  <div class="workflow">
			
					<div class="panel panel-primary">
                                    <div class="panel-heading">
                                      
                                           Workflow
                                        
                                    </div>
                                   
                                        <div class="panel-body">
										
											<table  id="imageTable" class="table table-hover">
														<thead>
														  <tr>
															<th>Step's</th>
															<th>UserId</th>
															<th>Role</th>
															<th>Status</th>
															<th>Remarks</th>
															
														  </tr>
														</thead>
														<tbody>
														  <tr>
															<td>1</td>
															<td>311600</td>
															<td>VALIDATOR</td>
															<td>validated</td>
															<td><textarea class="form-control" rows="1"></textarea></td>
															
														  </tr>
														  <tr>
															<td>2</td>
															<td>123456</td>
															<td>REVIEWER</td>
															<td>sent back</td>
															<td><textarea class="form-control" rows="1"></textarea></td>
														</tr>
														 <tr>
															<td>3</td>
															<td>311600</td>
															<td>VALIDATOR</td>
															<td>validated</td>
															<td><textarea class="form-control" rows="1"></textarea></td>
														</tr>
														 <tr>
															<td>4</td>
															<td>123456</td>
															<td>REVIEWER</td>
															<td>Approved</td>
															<td><textarea class="form-control" rows="1"></textarea></td>
														</tr>
														  
														</tbody>
											  </table>
                                        
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
	
	
	});
} );
 </script>
</body>
</html>