<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<%@ include file="fragments/libs.jspf"%>
<title>Insert title here</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>
	<div>
	<form action="/Car_Workshop/editOrder" method="post" id="editOrder">
	Wybierz pracownika:
 <select class="form-control" name="employeeId">
 <c:forEach items="${employees}" var="empl">
 <option value="${empl.id}" <c:if test="${empl.id== order.assignedWorker.id}"> selected="selected"</c:if>>
 <c:out value="${empl.name} ${empl.surname}" />
</option>
</c:forEach>
</select> 
<br>
Wybierz pojazd:
<select class="form-control" name="vehId">
 <c:forEach items="${vehicles}" var="veh">
 <option value="${veh.id}" <c:if test="${veh.id== order.vehicle.id}"> selected="selected"</c:if>>
 <c:out value="${veh.brand} ${veh.model}" />
</option>
</c:forEach>
</select> 
<br>
Opisz problem: 
<br>
<textarea name="problem" form="editOrder"> ${order.problemDescription} </textarea>
<br>
Opisz rozwiązanie: 
<br>
<textarea name="solution" form="editOrder">${order.repairDescription} </textarea>
<br>
Podaj koszt użytych części:
<input type="number" name="partsCost" step="0.01" value="${order.partsCost}">
<br>
Podaj czas pracy:
<input type="number" name="repairTime" step="0.01" value="${order.repairTime}">
<br>
Koszt pracownika: <c:out value="${order.costPerHour}"/>
  <input type="submit" value="Zapisz">
</form> 
</div>
	<%@ include file="fragments/footer.jspf"  %>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>