<%--
  Created by IntelliJ IDEA.
  User: gkuz
  Date: 21.01.2019
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Title</title>
</head>
<body>
    <h2>This is meals page</h2>
    
    <table border="1" cellspacing="0" cellpadding="2">
        <caption>Table of user meals</caption>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach var="meal" items="${mealList}">
            <tr class="${meal.exceed ? 'exceed' : 'normal'}">
                <td class=""><javatime:format value="${meal.dateTime}" pattern="dd.MM.yyyy hh:mm"/></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meal?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meal?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <p><a href="meal?action=add">add meal</a></p>
</body>
</html>
