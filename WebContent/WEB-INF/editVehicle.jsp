<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"  %>
	<h2>Edytuj samochód:</h2>
	<form action="/Car_Workshop/editVehicle?carId=${param.carId}&ownerId=${param.ownerId}" method="post">
  Model:<br>
  <input type="text" name="model" value="${veh.model}"><br>
  Marka:<br>
  <input type="text" name="brand" value="${veh.brand}">
  <br>
  Rok produkcji:<br>
  <input type="number" name="year" min="1886" max="2018" value="${veh.productionYear}">
  <br>
  Numer rejestracyjny:<br>
   <input type="text" name="regNum" value="${veh.registrationNumber}">
  <br>
  Data następnego przeglądu:<br>
  <input type="date" name="nextRev" value="${veh.nextReview}">
  <br>
  <input type="submit" value="Zapisz">
</form> 
	<%@ include file="fragments/footer.jspf"  %>
	
	<%@ include file="fragments/footer.jspf"  %>
</body>
</html>