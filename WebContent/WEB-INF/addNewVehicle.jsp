<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"  %>
	<br>
	<br>
	<h2>Dodaj samochód:</h2>
	<br>
	<br>
	
	<form action="/Car_Workshop/addNewVehicle?ownerId=${param.ownerId}" method="post">
  Model:<br>
  <input type="text" name="model"><br>
  Marka:<br>
  <input type="text" name="brand">
  <br>
  Rok produkcji:<br>
  <input type="number" name="year" min="1886" max="2018">
  <br>
  Numer rejestracyjny:<br>
   <input type="text" name="regNum">
  <br>
  Data następnego przeglądu:<br>
  <input type="date" name="nextRev">
  <br>
  <input type="submit" value="Zapisz">
</form> 
	<%@ include file="fragments/footer.jspf"  %>
</body>
</html>