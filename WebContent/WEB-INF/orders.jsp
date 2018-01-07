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
<title>Zamówienia - DIANINHA WORKSHOP</title>
</head>
<body>
	<%@ include file="fragments/menu.jspf"%>


	<div class="container-fluid">
		<div class="row">
			<div class="col mt-5">
				<h2>Lista zleceń:</h2> 
			</div>
		</div>
		
		<div class="row">
			<div class="col mt-2">
				
	
         Filtruj:
        <a href="../Car_Workshop/orders?status=1"><button type="button" class="btn listed" name="filter1">niezaapceptowane</button> </a>
        <a href="../Car_Workshop/orders?status=2"><button type="button" class="btn listed" name="filter1">zaapceptowane</button> </a>
        <a href="../Car_Workshop/orders?status=3"><button type="button" class="btn listed" name="filter1">w realizacji</button> </a>
        <a href="../Car_Workshop/orders?status=4"><button type="button" class="btn listed" name="filter1">gotowe</button> </a>
        <a href="../Car_Workshop/orders?status=5"><button type="button" class="btn listed" name="filter1">anulowane</button> </a>
        <a href="../Car_Workshop/orders?status=0"><button type="button" class="btn special" name="filter1">wszystkie</button> </a>
         <br>
         <c:if test="${not empty message}">
         <div class="alert alert-warning mt-3">
         <c:out value="${message}" /></div>
         </c:if>
         
         
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
								<td><a href="../Car_Workshop/orders?id=${order.id}#orderInfo">
										Szczegóły</a></td>
								<td><a href="../Car_Workshop/changeOrderStatus?orderId=${order.id}&currentStatus=${order.getStatusIinInt()}">Zmień status</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		
	
	
		<div class="row">
			<div class="col mb-4">
			
				<a href="../Car_Workshop/addNewOrder">
					<button type="button" class="btn special" name="addOrder">◤ Dodaj
						nowe zlecenie</button>
				</a>
			</div>
		</div>
	
 <c:if test="${not empty showDetails}">
     
  <div class="row">
    <div class="col mb-3" id="orderInfo">
    
            <h2>Szczegóły zlecenia:</h2>
    </div>
    </div>
    
    <div class="row">
    <div class="col">
    
            <div class="table-responsive table-striped">
				<table class="table">
					<tbody>
            <tr>
            <td>DATA AKCEPTACJI:</td><td> <c:out value="${dOrder.acceptance}" /></td>
            </tr>
            <tr>
            <td>DATA ROZPOCZĘCIA:</td><td> <c:out value="${dOrder.repairStartTime}" /></td>
            </tr>
            <tr>
            <td>DATA ZAKOŃCZENIA:</td><td> <c:out value="${dOrder.repairEndTime}" /></td>
            </tr>
            <tr>
            <td>PRZYPISANY PRACOWNIK:</td><td> <c:out value="${dOrder.assignedWorker.surname} ${dOrder.assignedWorker.name}" /></td>
            </tr>
            <tr>
            <td>STATUS:</td><td> <c:out value="${dOrder.showStatus()}" /></td>
            </tr>
            <tr>
            <td>POJAZD:</td><td> <c:out value="${dOrder.vehicle.brand} ${dOrder.vehicle.model}" /></td>
            </tr>
            <tr>
            <td>OPIS PROBLEMU:</td><td> <c:out value="${dOrder.problemDescription}" /></td>
            </tr> 
            <tr>
            <td>OPIS ROZWIĄZANIA:</td><td> <c:out value="${dOrder.repairDescription}" /></td>
            </tr>
            <tr>
            <td>KOSZT NAPRAWY:</td><td> <c:out value="${dOrder.repairCost} PLN" /></td>
            </tr>
            <tr>
            <td>KOSZT CZĘŚCI:</td><td> <c:out value="${dOrder.partsCost} PLN" /></td>
            </tr>
            <tr>
            <td>KOSZT ROBOCZOGODZINY:</td><td> <c:out value="${dOrder.costPerHour} PLN/h" /></td>
            </tr>
            <tr>
            <td>CZAS NAPRAWY:</td><td> <c:out value="${dOrder.repairTime} h" /></td>
            </tr>
            
            </tbody>
            </table>
            
             </div>
            </div>
            </div>
            
            <div class="row">
    <div class="col mb-4">
           
            <a href="../Car_Workshop/editOrder?dOrderId=${dOrder.id}"><button type="button" class="btn special" name="edit">✎ Edytuj</button></a>
             <a href="../Car_Workshop/deleteOrder?dOrderId=${dOrder.id}"><button type="button" class="btn listed" name="delete}">✘ Anuluj zlecenie</button></a>
            
            	</div></div>
           
    </div>
   
   </c:if>
   </div>
 
  

	<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>
</body>
</html>