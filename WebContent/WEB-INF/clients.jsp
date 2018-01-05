<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<title>Klienci</title>

<link href="https://fonts.googleapis.com/css?family=Coda" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/cookieconsent2/3.0.3/cookieconsent.min.css" />

</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

    <div class="container">
  <div class="row">
    <div class="col-sm">
        <h2>Lista klientów:</h2>
        <br>
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
                    <td><a href="../Car_Workshop/clients?id=${client.id}">Zobacz
                            samochody</a></td>
                    <td><a href="../Car_Workshop/editClientForm?id=${client.id}"><button
                                type="button" class="btn" name="edit">Edytuj</button></a></td>
                    <td><a href="../Car_Workshop/deleteClient?id=${client.id}"><button
                                type="button" class="btn" name="delete}">Usuń</button></a></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
    </div>
    
        <div class="row mb-5">
    <div class="col-sm">
        <a href="../Car_Workshop/addNewClient">
            <button type="button" class="btn" name="addClient">Dodaj nowego klienta</button>
        </a>
        </div>
        </div>
        

    
    </div>
    <c:if test="${not empty showCars}">
        <div class="container">
  <div class="row">
    <div class="col-sm">
            <h2>Samochody klienta: ${owner.name} ${owner.surname}</h2>
            <br>
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
                                    type="button" class="btn" name="edit">Edytuj</button></a></td>
                        <td><a href="../Car_Workshop/deleteVeh?id=${veh.id}"><button
                                    type="button" class="btn" name="delete}">Usuń</button></a></td>
                        <td><form action="../Car_Workshop/changeOwner">
                                <select class="form-control" name="customers">
                                    <c:forEach items="${clients}" var="client">
                                        <option value="${veh.id}_${client.id}"><c:out
                                                value="${client.name}" />
                                            <c:out value="${client.surname}" /></option>
                                    </c:forEach>
                                </select> 
                                <input type="submit" value="Zmień" class="btn mt-3">
                            </form></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>

            </div>
            </div>
        </div>
   
    <c:if test="${not empty noCars}">
<div class="container">
  <div class="row"> 
<div class="col-sm alert alert-warning">
    
    <strong>Uwaga!</strong> Brak samochodów tego klienta w bazie.
  
</div>
</div>
</div>
      </c:if>
<div class="container">
  <div class="row">
    <div class="col-sm">
<a href="../Car_Workshop/addNewVehicle?ownerId=${owner.id}">
            <button type="button" class="btn" name="addVeh">Dodaj samochód</button>
        </a>
</div>
    </div>
        </div>
         </c:if>
        
<%@ include file="fragments/footer.jspf"  %>
    
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js" integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4" crossorigin="anonymous"></script>

</body>
</html>