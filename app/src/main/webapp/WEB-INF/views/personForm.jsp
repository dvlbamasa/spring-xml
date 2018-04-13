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
    	<h1><c:out value="${title}"></c:out></h1><br/>
    	<c:if test="${prompt ne null}">
         	<font color=red><c:out value="${prompt}"></c:out></font><br/><br/>
     	</c:if>
     	<div align="center">
	    	Please Fill out the Person Information Form: <br/><br/>
	      	<form:form action = "savePerson" method = "POST" modelAttribute="person">
	      		<table>
	      			<form:hidden path="id"/>
	      			<tr>
	      				<td>First Name: </td>
	      				<td><form:input path="name.firstName" /></td>
	      			</tr>
	      			<tr>
	      				<td>Middle Name: </td>
	      				<td><form:input path="name.middleName" /></td>
	      			</tr>
	      			<tr>
	      				<td>Last Name: </td>
	      				<td><form:input path="name.lastName" /></td>
	      			</tr>
	      			<tr>
	      				<td>Gender: </td>
	      				<td>
	      					<form:radiobutton path="gender" value="male" label="Male" />
	      					<form:radiobutton path="gender" value="female" label="Female" />
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>Birthday (YYYY-MM-DD): </td>
	      				<td><form:input path="birthday" /></td>
	      			</tr>
	      			<tr>
	      				<td>GWA: </td>
	      				<td><form:input path="gwa" /></td>
	      			</tr>
	      			<tr>
	      				<td>Currently Employed: </td>
	      				<td>
	      					<form:radiobutton path="currentlyEmployed" value="true" label="Yes" />
	      					<form:radiobutton path="currentlyEmployed" value="false" label="No" />
	      				</td>
	      			</tr>
	      			<tr>
	      				<td>Date Hired (YYYY-MM-DD): </td>
	      				<td><form:input path="dateHired" /></td>
	      			</tr>
	      			<tr>
	      				<td>Street Number: </td>
	      				<td><form:input path="address.streetNo" /></td>
	      			</tr>
	      			<tr>
	      				<td>Barangay: </td>
	      				<td><form:input path="address.barangay" /></td>
	      			</tr>
	      			<tr>
	      				<td>Municipality: </td>
	      				<td><form:input path="address.municipality" /></td>
	      			</tr>
	      			<tr>
	      				<td>Zip Code: </td>
	      				<td><form:input path="address.zipCode" /></td>
	      			</tr>
	      			<tr>
	      				<td>Landline: </td>
	      				<td><form:input path="contactInformation.landline" /></td>
	      			</tr>
	      			<tr>
	      				<td>Mobile Number: </td>
	      				<td><form:input path="contactInformation.mobileNumber" /></td>
	      			</tr>
	      			<tr>
	      				<td>Email: </td>
	      				<td><form:input path="contactInformation.email" /></td>
	      			</tr>
	      			<tr>
	      				<td colspan="2"><input type="submit"></td>
	      			</tr>

	      		</table>
		        
		        Check the Roles you want to set to this person:<br/>

		        <c:forEach items="${requestScope.roles}" var="role">
		        	<input type="checkbox" name="rolesCheckBox" value="<c:out value='${role.id}'/>"/><c:out value="${role.name}"/><br/>
		        </c:forEach>
		        <br/>


		    </form:form>
		</div>
    </body>
</html>