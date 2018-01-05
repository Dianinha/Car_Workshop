<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/res/css/main.css" />" />
<title>Pracownicy</title>
<%@ include file="fragments/libs.jspf"%>

</head>
<body>
	<%@ include file="fragments/menu.jspf"%>
 <div class="container">
 <div class="row mb-5">
  <div class="col-sm">
	<h2>Lista pracowników:</h2>
	<br>
	<table class="table">
		<tr>
			<th>IMIĘ</th>
			<th>NAZWISKO</th>
			<th>ADRES</th>
			<th>TELEFON</th>
			<th>NOTATKA</th>
			<th>KOSZT ROBOCZOGODZINY</th>
			<th></th>
			<th></th>
			<th></th>

		</tr>
		  <tbody>
		<c:forEach items="${empls}" var="empl">
			<tr>
				<td><c:out value="${empl.name}" /></td>
				<td><c:out value="${empl.surname}" /></td>
				<td><c:out value="${empl.address}" /></td>
				<td><c:out value="${empl.phoneNumber}" /></td>
				<td><c:out value="${empl.note}" /></td>
				<td><c:out value="${empl.costPerHour}" /></td>
				<td><a href="">Zobacz zlecenia</a></td>
				<td><a href="../Car_Workshop/editEmployee?id=${empl.id}"><button
							type="button" class="btn" name="edit">Edytuj</button></a></td>
				<td><a href="../Car_Workshop/deleteEmployee?id=${empl.id}"><button
							type="button" class="btn" name="delete}">Usuń</button></a></td>
			</tr>

		</c:forEach>
		</tbody>
	</table>
	 </div>
    </div>
    <div class="row mb-5">
    <div class="col-sm">
        <a href="../Car_Workshop/addNewEmployee">
            <button type="button" class="btn" name="addEmployye">Dodaj nowego pracownika</button>
        </a>
        <br>
        <br>
        </div>
        </div>
    
    
</div>
	<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>