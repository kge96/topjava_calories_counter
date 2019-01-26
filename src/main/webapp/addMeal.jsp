<%--
  Created by IntelliJ IDEA.
  User: gkuz
  Date: 26.01.2019
  Time: 17:26
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
    <form method="post" action="meal" name="addMeal">
        <div class="${meal == null ? 'hide' : 'visible'}">
            Meal ID: <input type="number" readonly="readonly" name="id" value="<c:out value="${meal.id}"/>"/>
        </div>
        <div>Date: <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}"/>"/></div>
        <div>Description: <input type="text" name="description" value="<c:out value="${meal.description}"/>"/></div>
        <div>Calories: <input type="number" name="calories" value="<c:out value="${meal.calories}"/>"/></div>
        <input type="submit" value="Submit"/>
    </form>
</body>
</html>
