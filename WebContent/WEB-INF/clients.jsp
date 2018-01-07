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
<title>Klienci - DIANINHA WORKSHOP</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

    <div class="container-fluid">
  <div class="row">
			<div class="col mt-5">
        <h2>Lista klientów:</h2>
      </div>
		</div>
		
		<div class="row">
			<div class="col mt-2 sm-2">
        <form action="../Car_Workshop/clients" method="post" class="form-inline">
  <div class="form-group mb-2">Szukaj:</div>
  <div class="form-group mx-sm-1 mb-2"><input type="search" class="form-control" name="searchClients"></div>
  <div class="form-group mx-0 mb-2"><input type="submit" value="⇢ Szukaj" class="btn special"></div>
</form>
		</div>
		</div>
		
		
		<div class="row">
			<div class="col mt-2">
         
				<div class="table-responsive table-striped">
				 <table class="table">
            <thead>
            <tr>
                <th>IMIĘ</th>
                <th>NAZWISKO</th>
                <th>ADRES</th>
                <th>TELEFON</th>
                <th>DATA URODZIN</th>
                <th></th>
                <th></th>
                <th></th>

            </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td><c:out value="${client.name}" /></td>
                    <td><c:out value="${client.surname}" /></td>
                    <td><c:out value="${client.address}" /></td>
                    <td><c:out value="${client.phoneNumber}" /></td>
                    <td><c:out value="${client.birthday}" /></td>
                    <td><a href="../Car_Workshop/clients?id=${client.id}#cars">Zobacz
                            samochody</a></td>
                    <td><a href="../Car_Workshop/editClientForm?id=${client.id}"><button
                                type="button" class="btn special" name="edit">✎ Edytuj</button></a></td>
                    <td><a href="../Car_Workshop/deleteClient?id=${client.id}"><button
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
        <a href="../Car_Workshop/addNewClient">
            <button type="button" class="btn special" name="addClient">◤ Dodaj nowego klienta</button>
        </a>
        </div>
        </div>
        

    
    </div>
    
    
    <c:if test="${not empty showCars}">
        <div class="container-fluid">
  <div class="row">
    <div class="col-sm" id="cars">
            <h2>Samochody klienta: ${owner.name} ${owner.surname}</h2>
            
            <div class="row">
			<div class="col mt-2">
         
				<div class="table-responsive table-striped">
				 <table class="table">
            <thead>
                <tr>
                    
                    <th>MODEL</th>
                    <th>MARKA</th>
                    <th>ROK PRODUKCJI</th>
                    <th>NR REJESTRACYJNY</th>
                    <th>NASTĘPNY PRZEGLĄD</th>
                    <th></th>
                    <th></th>
                    <th>ZMIEŃ WŁAŚCICIELA</th>
                     <th></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${vehicles}" var="veh">
                    <tr>
                        
                        <td><c:out value="${veh.model}" /></td>
                        <td><c:out value="${veh.brand}" /></td>
                        <td><c:out value="${veh.productionYear}" /></td>
                        <td><c:out value="${veh.registrationNumber}" /></td>
                        <td><c:out value="${veh.nextReview}" /></td>
                        <td><a href="../Car_Workshop/editVehicle?carId=${veh.id}&ownerId=${owner.id}"><button
                                    type="button" class="btn special" name="edit">✎ Edytuj</button></a></td>
                        <td><a href="../Car_Workshop/deleteVeh?id=${veh.id}"><button
                                    type="button" class="btn listed" name=name="delete">✘ Usuń</button></a></td>
                        <td><form action="../Car_Workshop/changeOwner" class="form-inline">
                                <select class="form-control mx-sm-1" name="customers" >
                                    <c:forEach items="${clients}" var="client">
                                        <option value="${veh.id}_${client.id}"><c:out
                                                value="${client.name}" />
                                            <c:out value="${client.surname}" /></option>
                                    </c:forEach>
                                </select> 
                                <input type="submit" value="Zmień" class="btn mx-0 special">
                                <td><a href="../Car_Workshop/historyVeh?id=${veh.id}">Historia napraw</a></td>
                            </form></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            </div></div></div>

            </div>
            </div>
            
            
  <div class="row">
    <div class="col-sm mb-4">
<a href="../Car_Workshop/addNewVehicle?ownerId=${owner.id}">
            <button type="button" class="btn special" name="addVeh">◤  Dodaj samochód</button>
        </a>
</div>
    </div>
       
        
        </div>
        
         </c:if>
   
    <c:if test="${not empty noCars}">
<div class="container-fluid">
  <div class="row"> 
<div class="col-sm alert alert-warning">
    
    <strong>Uwaga!</strong> Brak samochodów tego klienta w bazie.
  
</div>


        
</div>
</div>
      </c:if>

        
        
<%@ include file="fragments/footer.jspf"%>
	<%@ include file="fragments/scripts.jspf"%>