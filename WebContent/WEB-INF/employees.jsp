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
<h2>Lista pracowników:</h2>
<br>
<table style="border: 3px solid black; padding: 5px;">
        <tr>
            <th>id</th>
            <th>IMIĘ </th>
            <th>NAZWISKO</th>
            <th>ADRES</th>
            <th>TELEFON</th>
            <th>NOTATKA</th>
             <th>KOSZT ROBOCZOGODZINY</th>
            <th>ZOBACZ ZLECENIA</th>
            
        </tr>
        <c:forEach items="${empls}" var="empl">
            <tr>
                <td><c:out value="${empl.id}" /></td>
                <td><c:out value="${empl.name}" /></td>
                <td><c:out value="${empl.surname}" /></td>
                <td><c:out value="${empl.address}" /></td>
                <td><c:out value="${empl.phoneNumber}" /></td>
                <td><c:out value="${empl.note}" /></td>
                <td><c:out value="${empl.costPerHour}" /></td>
                <td><a href="">Zobacz zlecenia</a></td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>