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
			  <li><a  href="${pageContext.request.contextPath}/qualityCheckHome">Quality Check</a></li>
			  </c:if>
			  <li><a href="${pageContext.request.contextPath}/kpihome">KPI</a></li>
			 
			</ul>
		</nav>

	  <div class="main-content">
	
	   <div class="view-content">
		<div id="current-process" class="current-process">
		<h3>Current Process</h3>
			<table  id="imageTable1" class="table table-hover">
			<thead>
			  <tr>
				<th>Process ID</th>
				<th>start Date/time</th>
				<th>Total # of Image</th>
				<th># of Image Processed</th>
				<th>Status</th>
				<th>Duration</th>
				<th></th>
			  </tr>
			</thead>
			
  </table>
			
		</div>
		<div class="Completed-Process">
		<h3>Completed Process</h3>
		<table  id="imageTable2" class="table table-hover">
			<thead>
			  <tr>
				<th>Process ID</th>
				<th>start Date/time</th>
				<th>End Date/time</th>
				<th>Total # of Image</th>
				<th></th>
			  </tr>
			</thead>
			<tbody>
			<c:forEach var="batch" items="${batchlist}">
			  <tr>
				<td>${batch.batchId}</td>
				<td>${batch.startDate}</td>
				<td>${batch.endDate}</td>
				<td>${batch.totalImage}</td>
				<td><a href="${pageContext.request.contextPath}/processDetails/${batch.batchId}"><i class="fa fa fa-server" style="font-size:24px;color:#3498db"></i></a></td>
			  </tr>
			 </c:forEach>
			</tbody>
  </table>
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
	 
	 var url='${pageContext.request.contextPath}/checkCurrentBatch';
	 $.ajax({
		 
		 type : "POST",
         url : url,
         success : function(data) {
        	 console.log("SUCCESS: ", JSON.stringify(data));
             if(JSON.stringify(data)=="\"\"")
            	 {
            	
             }
             else{
            	 var value=[[ data.processId,data.startDate,data.totalImage,data.totalProcessedImage,data.status,data.duration]]
            	 console.log("SUCCESS: ", data.processId);
            	 $(".current-process").css( "display","block");
            	  $('#imageTable1').DataTable({
            	   data:value,
            	   "searching": false,
               	"ordering": false,
                   "info":     false,
               	"paging": false
            	  });
            	
             }
         }
     });
	 
	 
    
    $('#imageTable2').DataTable({
    	"searching": true,
    	"ordering": true,
        "info":     true,
    	"paging": true
    	});
} );
 </script>
</body>
</html>