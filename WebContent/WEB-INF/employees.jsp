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
<title>Pracownicy - DIANINHA WORKSHOP</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"%>
 <div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
	<h2>Lista pracowników:</h2>
	</div>
		</div>
		
	<div class="row">
			<div class="col mt-2">
         
				<div class="table-responsive table-striped">
				 <table class="table">
		<tr>
			<th>IMIĘ</th>
			<th>NAZWISKO</th>
			<th>ADRES</th>
			<th>TELEFON</th>
			<th>NOTATKA</th>
			<th>KOSZT/H</th>
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
				<td><a href="../Car_Workshop/employees?id=${empl.id}">Zobacz zlecenia</a></td>
				<td><a href="../Car_Workshop/editEmployee?id=${empl.id}"><button
							type="button" class="btn special" name="edit">✎ Edytuj</button></a></td>
				<td><a href="../Car_Workshop/deleteEmployee?id=${empl.id}"><button
							type="button" class="btn listed" name="delete}">✘ Usuń</button></a></td>
			</tr>

		</c:forEach>
		</tbody>
	</table>
	 </div>
    </div>
    </div>
    <div class="row">
    <div class="col-sm mb-4">
        <a href="../Car_Workshop/addNewEmployee">
            <button type="button" class="btn special" name="addEmployye">◤ Dodaj nowego pracownika</button>
        </a>
        <br>
        <br>
        
        </div>
        </div>
</div>
<c:if test="${not empty showOrders}">
<div class="container-fluid">
  <div class="row">
			<div class="col mt-2">
                <h2>Lista zleceń ${empl.name} ${empl.surname}:</h2> 
            </div>
  </div>
     
                <div class="row">
			<div class="col">
         
				<div class="table-responsive table-striped">
				 <table class="table">
                    <thead>
                        <tr>
                            <th>DATA ZLECENIA</th>
                            <th>ROZPOCZĘCIE</th>
                            <th>STATUS</th>
                            <th>POJAZD</th>
                            <th></th>

                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td><c:out value="${order.acceptance}" /></td>
                                <td><c:out value="${order.repairStartTime}" /></td>
                                <td><c:out value="${order.showStatus()}" /></td>
                                <td><c:out value="${order.vehicle.brand} ${order.vehicle.model}" /></td>
                                <td><a href="../Car_Workshop/orders?id=${order.id}#orderInfo">
                                        Szczegóły</a></td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
                </div></div></div>
                
           
        

        <div class="row mb-1">
            <div class="col-sm mb-4">
                <a href="../Car_Workshop/addNewOrder">
                    <button type="button" class="btn special" name="addOrder">◤ Dodaj
                        nowe zlecenie</button>
                </a>
            </div>
        </div>
    </div>
    
    <div class="container-fluid">
  <div class="row"> 
<div class="col-sm alert alert-warning">
    
    <strong>Uwaga!</strong> Brak zleceń tego pracownika! SEKCJA DO DODANIA
  
</div>


        
</div>
</div>

    </c:if>
	<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>