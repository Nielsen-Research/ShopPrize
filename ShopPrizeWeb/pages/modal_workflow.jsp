<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
<div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Workflow</h4>
					        </div>
					        <div class="modal-body">
					         <div id="workflow" class="workflow">
			
					<div class="panel panel-primary">
                                   
                                   
                                        <div class="panel-body">
										
										<table  id="imageTable" class="table table-hover">
														<thead>
														  <tr>
															<th>UserId</th>
															<th>Name</th>
															<th>Role</th>
															<th>Status</th>
															<th>Remarks</th>
															
														  </tr>
														</thead>
														<tbody>
														
														<c:forEach var="workflow" items="${workflowList}">
														  <tr>
															<td>${workflow.userId}</td>
															<td>${workflow.userName}</td>
															<td>${workflow.role}</td>
															<td>${workflow.status}</td>
															<td><textarea class="form-control" rows="1" readonly>${workflow.remark}</textarea></td>
															
														  </tr>
														 </c:forEach>
														  
														</tbody>
											  </table>
                                        
                                    </div>
								</div>
								
</div>	
					        </div>
  
</body>  
</html>