<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <head>
        <title><c:out value="${title}"></c:out></title> 
         <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div align="center">
            <h1><c:out value="${title}"></c:out></h1><br/>
            Please Fill out the New Contact Information Form: <br/><br/> 
            <form:form action="save" method="POST" modelAttribute="contactInformation">
                <table>
                    <form:hidden path="id"/>
                    <tr>
                        <td>Landline: </td>
                        <td><form:input path="landline" maxlength="20" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Mobile Number: </td>
                        <td><form:input path="mobileNumber" maxlength="20" required="required" /></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><form:input path="email" maxlength="30" required="required" /></td>
                    </tr>
                </table><br/>
                <input type="submit" value="Submit Form">
            </form:form>
        </div>
    </body>
</html>