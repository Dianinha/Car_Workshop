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
<title>Edytuj zlecenia - DIANINHA WORKSHOP</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

<div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
	<h2>Edytuj dane klienta:</h2>
	</div>
		</div>
	<div class="row">
			<div class="col mb-4">
	<form action="/Car_Workshop/editOrder" method="post" id="editOrder">
	
	<div class="form-group col-sm-2">
  		<label>Wybierz pracownika:</label>
  		 <select class="form-control" name="employeeId" class="form-control">
 			<c:forEach items="${employees}" var="empl">
 			<option value="${empl.id}" <c:if test="${empl.id== order.assignedWorker.id}"> selected="selected"</c:if>>
 			<c:out value="${empl.name} ${empl.surname}" />
			</option>
			</c:forEach>
			</select>
  	</div>
  	<div class="form-group col-sm-2">
  		<label>Wybierz pojazd:</label>
  		<select class="form-control" name="vehId" class="form-control">
 			<c:forEach items="${vehicles}" var="veh">
 			<option value="${veh.id}" <c:if test="${veh.id== order.vehicle.id}"> selected="selected"</c:if>>
 			<c:out value="${veh.brand} ${veh.model}" />
			</option>
			</c:forEach>
		</select> 
		</div>
	<div class="form-group col-sm-4">
  		<label>Opisz problem:</label>
		<textarea name="problem" form="editOrder" class="form-control"> ${order.problemDescription} </textarea>
	</div>
	<div class="form-group col-sm-4">
  		<label>Opisz rozwiązanie: </label>
		<textarea name="solution" form="editOrder" class="form-control">${order.repairDescription} </textarea>
	</div>
	<div class="form-group col-sm-2">
  		<label>Podaj koszt użytych części: </label>
		<input type="number" name="partsCost" step="0.01" value="${order.partsCost}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Podaj czas pracy: </label>
		<input type="number" name="repairTime" step="0.01" value="${order.repairTime}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Koszt pracownika: </label>
		<c:out value="${order.costPerHour}"/>
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