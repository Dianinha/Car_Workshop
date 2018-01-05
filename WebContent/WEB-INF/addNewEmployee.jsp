<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<title>Dodaj pracownika</title>
<%@ include file="fragments/libs.jspf"%>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>
	<h2>Dodaj nowego pracownika:</h2>
	<form action="/Car_Workshop/addNewEmployee" method="post" id="addEmpl">
  Imię:<br>
  <input type="text" name="name"><br>
  Nazwisko:<br>
  <input type="text" name="surname">
  <br>
  Adres:<br>
  <input type="text" name="address">
  <br>
  Telefon:<br>
  <input type="number" name="phone" min="000000000" max="999999999">
  <br>
  Notatka: <br>
  <textarea name="note" form="addEmpl">Dodaj notatkę...</textarea>
  <br>
  Koszt roboczogodziny:<br>
  <input type="number" name="cost" step="0.01">
  <br>
  <input type="submit" value="Zapisz">
</form> 
	<%@ include file="fragments/footer.jspf"  %>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>