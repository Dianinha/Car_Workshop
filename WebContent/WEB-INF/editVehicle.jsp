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
<title>Edytuj samochód - DIANINHA WORKSHOP</title>
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
			
	<form action="/Car_Workshop/editVehicle?carId=${param.carId}&ownerId=${param.ownerId}" method="post">
	
	<div class="form-group col-sm-2">
  		<label>Model: </label>
		<input type="text" name="model" value="${veh.model}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Marka: </label>
		<input type="text" name="brand" value="${veh.brand}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Rok produkcji: </label>
		<input type="number" name="year" min="1886" max="2018" value="${veh.productionYear}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Numer rejestracyjny: </label>
		<input type="text" name="regNum" value="${veh.registrationNumber}" class="form-control">
	</div>
	<div class="form-group col-sm-2">
  		<label>Data następnego przeglądu: </label>
		<input type="date" name="nextRev" value="${veh.nextReview}" class="form-control">
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