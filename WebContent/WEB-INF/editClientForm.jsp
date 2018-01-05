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
	<br>
	<br>
	<h2>Edytuj dane klienta:</h2>
	<br>
	<p>Aktualne dane:
	<br>
	 Imię: <c:out value="${client.name}" />  Nazwisko: <c:out value="${client.surname}" />
	 Adres: <c:out value="${client.address}" /> Telefon: <c:out value="${client.phoneNumber}" />
	  Data urodzin: <c:out value="${client.birthday}" /></p>
	
	<br>
	
	<form action="/Car_Workshop/editClientForm?id=${client.id}" method="post">
  Imię:<br>
  <input type="text" name="name" value="${client.name}"><br>
  Nazwisko:<br>
  <input type="text" name="surname" value="${client.surname}">
  <br>
  Adres:<br>
  
  <input type="text" name="address" value="${client.address}" style="width: 460px; "><br>
  Telefon:<br>
  <input type="number" name="phone" min="000000000" max="999999999" value="${client.phoneNumber}">
  <br>
  Data Urodzin:<br>
  <input type="date" name="bday" value="${client.birthday}">
  <br>
  <input type="submit" value="Zapisz">
</form> 
<%@ include file="fragments/footer.jspf"  %>	
</body>
</html>