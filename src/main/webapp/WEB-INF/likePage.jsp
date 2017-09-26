<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>

	* {
		font-family: sans-serif;
	}

	#container {
		width: 1000px;
		margin: 0px auto;
	}

	table {
	    font-family: arial, sans-serif;
	    border-collapse: collapse;
	    width: 100%;
	}

	td, th {
	    border: 1px solid #dddddd;
	    text-align: left;
	    padding: 8px;
	}

	tr:nth-child(even) {
	    background-color: #dddddd;
	}
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Like Status</title>
</head>
<body>
<h1>Like Status</h1>

       	<h3><a href="/users/${post.user.id }" />${post.user.alias }</a> says:</h3>
		<fieldset>${post.content }</fieldset>
		
		
		<br>
		<br>

			<h3>People who liked this post:</h3>
			<table>
			  <tr>
			    <th>Alias</th>
			    <th>Name</th>
			  </tr>
 			<c:forEach items="${post.users}" var="user">
  			<tr>
			    <td><a href="/users/${user.id }"/>${user.alias }</a></td>
			    <td>${user.name}</td>

			</tr>
			</c:forEach>
			</table>
			<br>
			
			<a href="/">Back</a>

</body>
</html>