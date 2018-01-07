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
<title>Edytuj pracownika - DIANINHA WORKSHOP</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

<div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
	<h2>Edytuj dane pracownika:</h2>
	</div>
		</div>
		
	<div class="row">
			<div class="col">
	<h4>Aktualne dane:</h4>
	</div>
		</div>
		
		<div class="row">
    <div class="col">
    
            <div class="table-responsive table-striped">
				<table class="table">
					<tbody>
			<tr>
            <td>Imię: </td><td><c:out value="${empl.name}" /></td>
            </tr>
            <tr>
            <td>Nazwisko: </td><td><c:out value="${empl.surname}" /></td>
            </tr>
            <tr>
            <td>Adres: </td><td><c:out value="${empl.address}" /></td>
            </tr>
			<tr>
            <td>Imię: </td><td><c:out value="${empl.name}" /></td>
            </tr>
            <tr>
            <td>Telefon: </td><td><c:out value="${empl.phoneNumber}" /></td>
            </tr>
            <tr>
            <td>Notatka: </td><td><c:out value="${empl.note}" /></td>
            </tr>
            <tr>
            <td>Koszt: </td><td><c:out value="${empl.costPerHour}" /></td>
            </tr>

	</tbody>
            </table>
            
             </div>
            </div>
            </div>
          
           <div class="row">
			<div class="col mb-4">  
	<form action="/Car_Workshop/editEmployee?id=${empl.id}" method="post" id="editEmpl">
		<div class="form-group col-sm-2">
  			<label>Imię:</label>
  			<input type="text" name="name" value="${empl.name}" class="form-control">
  		</div>
  		<div class="form-group col-sm-2">
  			<label>Nazwisko:</label>
  			<input type="text" name="surname" value="${empl.surname}" class="form-control">
  		</div>
  		<div class="form-group col-sm-6">
  			<label>Adres:</label>
  			<input type="text" name="address" value="${empl.address}" class="form-control">
  		</div>
  		<div class="form-group col-sm-2">
  			<label>Telefon:</label>
  			<input type="number" name="phone" min="000000000" max="999999999" value="${empl.phoneNumber}" class="form-control">
  		</div>
		<div class="form-group col-sm-6">
  			<label>Notatka:</label>
  			<textarea name="note" form="editEmpl" class="form-control">${empl.note}</textarea>
  		</div>
		<div class="form-group col-sm-4">
  			<label>Koszt roboczogodziny:</label>
  			<input type="number" name="cost" step="0.01" value="${empl.costPerHour}" class="form-control">
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