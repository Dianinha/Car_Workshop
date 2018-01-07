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
<title>Dodaj nowego klienta - DIANINHA WORKSHOP</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

<div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
	<h2>Dodaj nowego klienta:</h2>
	</div>
		</div>
		
	<div class="row">
			<div class="col mb-4">
			
	<form action="/Car_Workshop/addNewClient" method="post" >
	<div class="form-group col-sm-2">
  <label>Imię:</label>
  <input type="text" name="name" class="form-control">
  </div>
  <div class="form-group col-sm-2">
  <label>Nazwisko:</label>
  <input type="text" name="surname" class="form-control">
  </div>
  <div class="form-group col-sm-4">
  <label>Adres:</label>
  <input type="text" name="address" class="form-control">
  </div>
  <div class="form-group col-sm-2">
  <label>Telefon:</label>
  <input type="number" name="phone" min="000000000" max="999999999" class="form-control">
  </div>
  <div class="form-group col-sm-4">
  <label>Data Urodzin:</label>
  <input type="date" name="bday" class="form-control">
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