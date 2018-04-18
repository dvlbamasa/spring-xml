<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <head>
        <title><c:out value="${title}"></c:out></title> 
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div align="center">
          <h1><c:out value="${title}"></c:out></h1><br/>
          Please Fill out the Role Information Form: <br/><br/>
          <spring:url value="save" var="action" />
          <form:form action="${action}" method="post" modelAttribute="role">
            <table>
                <form:hidden path="id"/>
                <tr>
                  <td>Role Name: </td>
                  <td><form:input path="name" maxlength="20" required="required" /></td>
                </tr>
                <tr><td colspan="2">Check the Persons you want to set to this role:<br/></td></tr> 
                <tr><form:checkboxes path="persons" element="table" items="${persons}" itemValue="id" itemLabel="name.firstName"/></tr> 
            </table><br/>
            <input type="submit" value="Submit Form"/>
          </form:form>
        </div>
    </body>
</html>
