<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="fragments/libs.jspf"%>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/res/css/main.css" />" />
<title>Dodaj nowe zlecenie - DIANINHA WORKSHOP</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>
	
	<div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
	<h2>Dodaj nowe zlecenie:</h2>
	</div>
		</div>
		
	<div class="row">
			<div class="col mb-4">
	<form action="/Car_Workshop/addNewOrder" method="post" id="newOrder">
	<div class="form-group col-sm-2">
  <label>Wybierz pracownika:</label>
 <select class="form-control" name="employeeId" class="form-control">
 <c:forEach items="${employees}" var="empl">
 <option value="${empl.id}">
 <c:out value="${empl.name} ${empl.surname}" />
</option>
</c:forEach>
</select> 
</div>
<div class="form-group col-sm-2">
  <label>Wybierz pojazd:</label>
<select class="form-control" name="vehId" class="form-control">
 <c:forEach items="${vehicles}" var="veh">
 <option value="${veh.id}">
 <c:out value="${veh.brand} ${veh.model}" />
</option>
</c:forEach>
</select> 
</div>
<div class="form-group col-sm-2">
  <label>Opisz problem:</label>
<textarea name="problem" form="newOrder" class="form-control"> </textarea>
</div>
  <input type="submit" value="✔ Zapisz" class="btn special">
</form>
 
</div>
</div>

</div>
	<%@ include file="fragments/footer.jspf"  %>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>