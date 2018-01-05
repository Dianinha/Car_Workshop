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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"%>


	<div class="container">
		<div class="row">
			<div class="col-sm">
				<h2>Lista zleceń:</h2> 
				Filtruj:
        <a href="../Car_Workshop/orders?status=1"><button type="button" class="btn" name="filter1">niezaapceptowane</button> </a>
        <a href="../Car_Workshop/orders?status=2"><button type="button" class="btn" name="filter1">zaapceptowane</button> </a>
        <a href="../Car_Workshop/orders?status=3"><button type="button" class="btn" name="filter1">w realizacji</button> </a>
        <a href="../Car_Workshop/orders?status=4"><button type="button" class="btn" name="filter1">gotowe</button> </a>
        <a href="../Car_Workshop/orders?status=5"><button type="button" class="btn" name="filter1">anulowane</button> </a>
        <a href="../Car_Workshop/orders?status=0"><button type="button" class="btn" name="filter1">wszystkie</button> </a>
         <br>
         <c:if test="${not empty message}">
         <c:out value="${message}" />
         </c:if>
         
         
				<br>
				<table class="table">
					<thead>
						<tr>
							<th>DATA ZLECENIA</th>
							<th>ROZPOCZĘCIE</th>
							<th>PRACOWNIK</th>
							<th>STATUS</th>
							<th>POJAZD</th>
							<th></th>
							<th></th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td><c:out value="${order.acceptance}" /></td>
								<td><c:out value="${order.repairStartTime}" /></td>
								<td><c:out value="${order.assignedWorker.surname}" /></td>
								<td><c:out value="${order.showStatus()}" /></td>
								<td><c:out value="${order.vehicle.brand} ${order.vehicle.model}" /></td>
								<td><a href="../Car_Workshop/orders?id=${order.id}">
										Szczegóły</a></td>
								<td><a href="../Car_Workshop/changeOrderStatus?orderId=${order.id}&currentStatus=${order.getStatusIinInt()}">Zmień status</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row mb-1">
			<div class="col-sm">
				<a href="../Car_Workshop/addNewOrder">
					<button type="button" class="btn" name="addOrder">Dodaj
						nowe zlecenie</button>
				</a>
			</div>
		</div>
	</div>
 <c:if test="${not empty showDetails}">
        <div class="container">
  <div class="row">
    <div class="col-sm">
            <h2>Szczegóły zlecenia:</h2>
            <br>
            DATA AKCEPTACJI: <c:out value="${dOrder.acceptance}" />
            <br>
            DATA ROZPOCZĘCIA: <c:out value="${dOrder.repairStartTime}" />
            <br>
            DATA ZAKOŃCZENIA: <c:out value="${dOrder.repairEndTime}" />
            <br>
            PRZYPISANY PRACOWNIK: <c:out value="${dOrder.assignedWorker.surname} ${dOrder.assignedWorker.name}" />
            <br>
            STATUS: <c:out value="${dOrder.showStatus()}" />
            <br>
            POJAZD: <c:out value="${dOrder.vehicle.brand} ${dOrder.vehicle.model}" />
            <br>
            OPIS PROBLEMU: <c:out value="${dOrder.problemDescription}" />
            <br>
            OPIS ROZWIĄZANIA: <c:out value="${dOrder.repairDescription}" />
            <br>
            KOSZT NAPRAWY: <c:out value="${dOrder.repairCost} PLN" />
            <br>
            KOSZT CZĘŚCI: <c:out value="${dOrder.partsCost} PLN" />
            KOSZT ROBOCZOGODZINY: <c:out value="${dOrder.costPerHour} PLN/h" />
            CZAS NAPRAWY: <c:out value="${dOrder.repairTime} h" />
            <br>
            <a href="../Car_Workshop/editOrder?dOrderId=${dOrder.id}"><button type="button" class="btn" name="edit">Edytuj</button></a>
             <a href="../Car_Workshop/deleteOrder?dOrderId=${dOrder.id}"><button type="button" class="btn" name="delete}">Anuluj zlecenie</button></a>
            </div>
            </div>
        </div>
   
   </c:if>
   <br>
   <br>
   <br>

	<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>