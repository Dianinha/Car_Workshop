<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<title>Insert title here</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>
<br>
<br>
<h2>Aktualnie w naprawie:</h2>
<br>
<table style="border: 3px solid black; padding: 5px;">
        <tr>
            <th>ZLECENIE</th>
            <th>PRACOWNIK </th>
            <th>SAMOCHÓD</th>
            <th>PODGLĄD</th>
            
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td><c:out value="${order.id}" /></td>
                <td><c:out value="${order.getAssignedWorker().getSurname()}" /></td>
                <td><c:out value="${orger.getVehicle().getBrand()}" /></td>
                <td><a href="">Zobacz zlecenie</a></td>
            </tr>

        </c:forEach>
    </table>
    <%@ include file="fragments/footer.jspf"  %>
</body>
</html>