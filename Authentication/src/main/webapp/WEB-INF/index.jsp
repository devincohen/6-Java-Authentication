<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/style.css"/>
	<!-- For any Bootstrap that uses JS -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
    <title>Login</title>
	
</head>
<body>
	<div class="row align-iems-center">
		<div class="col padded">
			<form:form action="/newUser" method="post" modelAttribute="newUser">
				<div class="row g-3 align-items-center">
					<div class="col-auto">
						<form:label path="userName" class="col-form-label" >Name: </form:label>
					</div>
					<div class="col-auto">
						<form:input path="userName" class="form-control"/>
					</div>
				</div>
				<div>
					<form:label path="email" class="form-label" >Email: </form:label>
					<form:input path="email" class="form-control"/>
				</div>
				<div>
					<form:label path="password" class="form-label" >Password: </form:label>
					<form:input path="password" class="form-control"/>
				</div>
				<div>
					<form:label path="confirm" class="form-label" >Confirm: </form:label>
					<form:input path="confirm" class="form-control"/>
				</div>
				<input type="submit" value="Submit" class="btn btn-success">
			</form:form>
			<c:forEach var="error" items="${registerErrors}">
				<p>${error.defaultMessage}</p>
			</c:forEach>
		</div>
		<div class="col padded">
	 		<form:form action="/newLogin" method="post" modelAttribute="newLogin">
				
				<div>
					<form:label path="email" class="form-label" >Email: </form:label>
					<form:input path="email" class="form-control"/>
				</div>
				<div>
					<form:label path="password" class="form-label" >Password: </form:label>
					<form:input path="password" class="form-control"/>
				</div>
					<input type="submit" value="Submit" class="btn btn-success">
				
			</form:form> 
			<c:forEach var="error" items="${loginErrors}">
				<p>${error.defaultMessage}</p>
			</c:forEach>
		</div>
	</div>
</body>
</html>