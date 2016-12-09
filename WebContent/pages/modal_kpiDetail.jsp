<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">KPI Details</h4>
        </div>
        <div class="modal-body">
       
       
   <form class="form-horizontal">
    <div class="form-group">
      <label class="control-label col-sm-4" for="email">Image Id</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.imageId}" readonly>
      </div>
    </div>
   <!-- <div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Chain Name</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" readonly>
      </div>
    </div> -->
	<div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Ocred</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.isOcred()}" readonly>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Character Entered/Modified</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.modifiedChar}" readonly>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Transcription</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.transcription}" readonly>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Time Spent</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.time}"  readonly>
      </div>
    </div>
	<div class="form-group">
      <label class="control-label col-sm-4" for="pwd">Status</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" value="${kpi.status}" readonly>
      </div>
    </div>
	
  </form>
</div>
</body>  
</html>