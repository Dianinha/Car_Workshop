<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/res/css/main.css" />" />
<%@ include file="fragments/libs.jspf"%>
<title>Edytuj pracownika</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"%>
	<h2>Edytuj dane pracownika:</h2>
	<br>
	<p>
		Aktualne dane: <br> Imię:
		<c:out value="${empl.name}" />
		Nazwisko:
		<c:out value="${empl.surname}" />
		Adres:
		<c:out value="${empl.address}" />
		Telefon:
		<c:out value="${empl.phoneNumber}" />
		Notatka:
		<c:out value="${empl.note}" />
		Koszt:
		<c:out value="${empl.costPerHour}" />
	</p>
	<br>
	<form action="/Car_Workshop/editEmployee?id=${empl.id}" method="post" id="editEmpl">
		Imię:<br> 
		<input type="text" name="name" value="${empl.name}">
		<br>
		Nazwisko:<br>
		<input type="text" name="surname" value="${empl.surname}">
        <br>
        Adres: <br>
        <input type="text" name="address" value="${empl.address}" style="width: 460px;">
        <br> 
        Telefon:<br>
        <input type="number" name="phone" min="000000000" max="999999999" value="${empl.phoneNumber}">
        <br>
        Notatka: <br>
		<textarea name="note" form="editEmpl">${empl.note}</textarea>
		<br>
        Koszt roboczogodziny:<br>
        <input type="number" name="cost" step="0.01" value="${empl.costPerHour}">
        <br>
       <input type="submit" value="Zapisz">
	</form>
	<br>
	<br>
	<br>
	<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>