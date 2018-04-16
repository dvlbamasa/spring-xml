<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
	<head>
      	<title><c:out value="${title}"></c:out></title> 
     	 <link rel="stylesheet" href="style.css">
   	</head>
    <body>
    	<h1 align="center"><c:out value="${title}"></c:out></h1><br/>
    	<c:if test="${prompt ne null}">
         	<font color=red><c:out value="${prompt}"></c:out></font><br/><br/>
     	</c:if>
     	<div align="center">
	    	Please Fill out the Person Information Form: <br/><br/>
	      	<form:form action="save" method="POST" modelAttribute="person">
	      		<table>
	      			<form:hidden path="id"/>
	      			<tr>
	      				<td>First Name: </td>
	      				<td><form:input path="name.firstName" maxlength="20" required="required" /></td>
	      			</tr>
	      			<tr>
	      				<td>Middle Name: </td>
	      				<td><form:input path="name.middleName" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Last Name: </td>
	      				<td><form:input path="name.lastName" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Gender: </td>
	      				<td>
	      					<form:radiobutton path="gender" value="MALE" label="Male" required="required"/>
	      					<form:radiobutton path="gender" value="FEMALE" label="Female" required="required"/>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>Birthday (YYYY-MM-DD): </td>
	      				<td><form:input path="birthday" value="${fn:substring(person.birthday,0,10)}" maxlength="10" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>GWA: </td>
	      				<td><form:input path="gwa" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Currently Employed: </td>
	      				<td>
	      					<form:radiobutton path="currentlyEmployed" value="true" label="Yes" required="required"/>
	      					<form:radiobutton path="currentlyEmployed" value="false" label="No" required="required"/>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>Date Hired (YYYY-MM-DD): </td>
	      				<td><form:input path="dateHired" value="${fn:substring(person.dateHired,0,10)}" maxlength="10" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Street Number: </td>
	      				<td><form:input path="address.streetNo" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Barangay: </td>
	      				<td><form:input path="address.barangay" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Municipality: </td>
	      				<td><form:input path="address.municipality" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Zip Code: </td>
	      				<td><form:input path="address.zipCode" required="required" /></td>
	      			</tr>
	      			<tr>
	      				<td>Landline: </td>
	      				<td><form:input path="contactInformation.landline" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Mobile Number: </td>
	      				<td><form:input path="contactInformation.mobileNumber" maxlength="20" required="required"/></td>
	      			</tr>
	      			<tr>
	      				<td>Email: </td>
	      				<td><form:input path="contactInformation.email" maxlength="30" required="required"/></td>
	      			</tr>

	      			<tr><td colspan="2">Check the Roles you want to set to this person:<br/></td></tr>
	      				
	      			<c:forEach items="${roles}" var="role">
	      				<tr>
			                <td><form:checkbox path="roles" value="${role}" label="${role.id}" /></td>
			                <td><c:out value="${role.name}" /></td>
			            </tr>
	      			</c:forEach>
	      		</table><br/>
	      		<input type="submit" value="Submit Form">
		    </form:form>
		</div>
    </body>
</html>