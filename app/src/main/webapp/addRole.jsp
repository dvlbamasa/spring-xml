<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <head>
        <title><c:out value="${title}"></c:out></title> 
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1><c:out value="${title}"></c:out></h1><br/>
      	Please Fill out the Role Information Form: <br/><br/>
      	<form action = "addRole" method = "POST">
	        Role Name:<br/>
	        <input type = "text" name = "role_name" value=""required><br/><br/>
          <c:if test="${fn:length(requestScope.persons) > 0}">
            Check the Persons you want to add to this role:<br/>
            <c:forEach items="${requestScope.persons}" var="person">
              <input type="checkbox" name="personsCheckBox" value="<c:out value='${person.id}'/>"/><c:out value="${person.name.firstName }"/> <c:out value="${person.name.lastName}"/><br/>
            </c:forEach>
          </c:if>
          <br/>
	        <input type = "submit" value = "Add Role" />
     	</form>
    </body>
</html>
