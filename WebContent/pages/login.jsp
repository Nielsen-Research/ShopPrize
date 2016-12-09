<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Demo</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/demo.css">
</head>
<body class="login">

		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
                        <h3 class="panel-title">Login</h3>
                    </div>
					<div class="panel-body">
					${message} 
					<form role="form"  method="post" action="login" modelAttribute="loginBean">
                            <fieldset>
                                <div class="form-group">
								User ID
                                    <input class="form-control" name="userid" type="text" autofocus>
                                </div>
                                <div class="form-group">
								Password
                                    <input class="form-control" name="password" type="password" value="">
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                                <input class="btn btn-lg btn-success btn-block" type="submit" value="Login" />
                               
                            </fieldset>
                        </form>
					</div>
				</div>
			</div>
		</div>
	
</body>
</html>
