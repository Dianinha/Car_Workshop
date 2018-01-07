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
<title>Edytuj klienta - DIANINHA WORKSHOP</title>
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
            <td>Imię: </td><td><c:out value="${client.name}" /></td>
            </tr>
            <tr>
            <td>Nazwisko: </td><td><c:out value="${client.surname}" /></td>
            </tr>
            <tr>
            <td>Adres: </td><td><c:out value="${client.address}" /></td>
            </tr>
            <tr>
            <td>Telefon: </td><td><c:out value="${client.phoneNumber}" /></td>
            </tr>
            <tr>
            <td>Data urodzin:</td><td> <c:out value="${client.birthday}" /></td>
            </tr>

	</tbody>
            </table>
            
             </div>
            </div>
            </div>
	
	 <div class="row">
			<div class="col mb-4">
	<form action="/Car_Workshop/editClientForm?id=${client.id}" method="post">
  <div class="form-group col-sm-2">
  <label>Imię:</label>
  <input type="text" name="name" value="${client.name}" class="form-control"></div>
  <div class="form-group col-sm-2">
  <label>Nazwisko:</label>
  <input type="text" name="surname" value="${client.surname}" class="form-control">
  </div>
  <div class="form-group col-sm-4">
  <label>Adres:</label>
  <input type="text" name="address" value="${client.address}" style="width: 460px; " class="form-control"></div>
  <div class="form-group col-sm-2">
  <label>Telefon:</label>
  <input type="number" name="phone" min="000000000" max="999999999" value="${client.phoneNumber}" class="form-control">
  </div>
  <div class="form-group col-sm-4">
  <label>Data Urodzin:</label>
  <input type="date" name="bday" value="${client.birthday}" class="form-control">
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