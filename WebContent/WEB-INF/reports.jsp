<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="fragments/libs.jspf"%>
<link type="text/css" rel="stylesheet" href="<c:url value="/res/css/main.css" />" />
<title>Insert title here</title>
</head>
<body>
<%@ include file="fragments/menu.jspf"  %>

<a href="../Car_Workshop/reports?type=1"><button type="button" class="btn" name="report1">Raport 1</button> </a>
        <a href="../Car_Workshop/reports?type=2"><button type="button" class="btn" name="report1">Raport 2</button> </a>
        <c:if test="${not empty reportType }">
        <c:if test="${reportType eq '1' }">
        
         Wybrano raport z przepracowanych roboczogodzin. Wybierz daty:
         
        
        </c:if>
        
         <c:if test="${reportType eq '2' }">
         
          Wybrano raport z zysków. Wybierz daty:  
         
         
         </c:if>
      
          <form action="/Car_Workshop/reports" method="post">
         Data rozpoczęcia:
            <input type="date" name="start">
            
             Data zakończenia:
            <input type="date" name="stop">
            <input type="submit" value="ok">
            </form> 
        
        </c:if>
        <c:if test="${not empty result1 }">
        
        <table class="table">
                    <thead>
                        <tr>
                            <th>PRACOWNIK</th>
                            <th>LICZBA GODZIN</th>

                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="entry" items="${result1}">
                        <tr>
                         <td><c:out value="${entry.key.name} ${entry.key.surname}"/></td>
                        <td> <c:out value="${entry.value}"/></td>
                         </tr>
                        </c:forEach>
                        
                        
                    </tbody>
                </table>
        
        
        
        
        
        </c:if>
         <c:if test="${not empty result2 }">
        Suma zysków z podanych dat to: <c:out value="${result2 }"></c:out>
        
        </c:if>


    <%@ include file="fragments/footer.jspf"  %>
    <%@ include file="fragments/scripts.jspf"%>
</body>
</html>