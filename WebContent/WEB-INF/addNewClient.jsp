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
	<h1>MENU</h1>
	<a href="../Car_Workshop/clients">Klienci</a>
	<br>
	<a href="">Zlecenia</a>
	<br>
	<a href="../Car_Workshop/employees">Pracownicy</a>
	<br>
	<a href="">Raporty</a>
	<br>
	<br>
	<h2>Dodaj nowego klienta:</h2>
	<br>
	<br>
	
	<form action="/Car_Workshop/addNewClient" method="post">
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
  Data Urodzin:<br>
  <input type="date" name="bday">
  <br>
  <input type="submit" value="Zapisz">
</form> 
	
</body>
</html>