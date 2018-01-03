<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MENU</h1>
	<a href="../Car_Workshop/clients">Klienci</a>
	<br>
	<a href="">Zlecenia</a>
	<br>
	<a href="../Car_Workshop/employees">Pracownicy</a>
	<br>
	<a href="">Raporty</a>
	<br>
	<br>
	<div>
		<h2>Lista klientów:</h2>
		<br>
		<table style="border: 3px solid black; padding: 5px;">
			<tr>
				<th>id</th>
				<th>IMIĘ</th>
				<th>NAZWISKO</th>
				<th>ADRES</th>
				<th>TELEFON</th>
				<th>DATA URODZIN</th>
				<th>ZOBACZ SAMOCHODY</th>
				<th></th>
				<th></th>

			</tr>
			<c:forEach items="${clients}" var="client">
				<tr>
					<td><c:out value="${client.id}" /></td>
					<td><c:out value="${client.name}" /></td>
					<td><c:out value="${client.surname}" /></td>
					<td><c:out value="${client.address}" /></td>
					<td><c:out value="${client.phoneNumber}" /></td>
					<td><c:out value="${client.birthday}" /></td>
					<td><a href="../Car_Workshop/clients?id=${client.id}">Zobacz
							samochody</a></td>
					<td><a href="../Car_Workshop/editClientForm?id=${client.id}"><button
								type="button" name="edit">Edytuj</button></a></td>
					<td><a href="../Car_Workshop/deleteClient?id=${client.id}"><button
								type="button" name="delete}">Usuń</button></a></td>
				</tr>

			</c:forEach>
		</table>

		<br> <a href="../Car_Workshop/addNewClient">
			<button type="button" name="addClient">Dodaj nowego klienta</button>
		</a>

	</div>
	<c:if test="${not empty showCars}">
		<div>
			<h2>Samochody klienta: ${owner.name} ${owner.surname}</h2>
			<br>
			<table style="border: 3px solid black; padding: 5px;">
				<tr>
					<th>id</th>
					<th>MODEL</th>
					<th>MARKA</th>
					<th>ROK PRODUKCJI</th>
					<th>NR REJESTRACYJNY</th>
					<th>DATA NASTĘPNEGO PRZEGLĄDU</th>
					<th></th>
					<th></th>
					<th>Zmień właściciela</th>

				</tr>
				<c:forEach items="${vehicles}" var="veh">
					<tr>
						<td><c:out value="${veh.id}" /></td>
						<td><c:out value="${veh.model}" /></td>
						<td><c:out value="${veh.brand}" /></td>
						<td><c:out value="${veh.productionYear}" /></td>
						<td><c:out value="${veh.registrationNumber}" /></td>
						<td><c:out value="${veh.nextReview}" /></td>
						<td><a href="../Car_Workshop/editVehicle?id=${veh.id}"><button
									type="button" name="edit">Edytuj</button></a></td>
						<td><a href="../Car_Workshop/deleteVeh?id=${veh.id}"><button
									type="button" name="delete}">Usuń</button></a></td>
						<td><form action="../Car_Workshop/changeOwner">
								<select name="customers">
									<c:forEach items="${clients}" var="client">
										<option value="${veh.id}_${client.id}"><c:out
												value="${client.name}" />
											<c:out value="${client.surname}" /></option>
									</c:forEach>
								</select> 
								<input type="submit" value="Zmień">
							</form></td>
					</tr>

				</c:forEach>
			</table>

		</div>
	</c:if>
	<c:if test="${not empty noCars}">
		<br>
    Brak samochodów tego klienta w bazie.
    </c:if>
<br>
<a href="../Car_Workshop/addNewVehicle?ownerId=${owner.id}">
            <button type="button" name="addVeh">Dodaj samochód</button>
        </a>
</body>
</html>