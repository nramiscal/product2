<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	<title>Admin Dashboard</title>
</head>
	<body>
		<div id="container">
			<h1>Welcome ${currentUser.firstName}</h1>
			
		<form class="" action="/savepost" method="post">
            <input type="text" name="content" placeholder="">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="user_id" value="${currentUser.id}"/>
            <input type="submit" name="" value="Idea!">
        </form>

       	<c:forEach items="${posts}" var="post" varStatus="loop">
       	<h3><a href="/users/${post.user.id }">${post.user.alias }</a> says:</h3>
		<fieldset>${post.content }</fieldset>
		  		<c:choose>
 				  <c:when test="${post.user.id == currentUser.id}">
				   	<td><a href="/delete/${post.id}">Delete</a></td>
				   </c:when>
				</c:choose>
		<a href="/like/${post.id }/${currentUser.id}">Like</a><br>
		<a href="/${post.id }">${post.getNumUsersWhoLike() } people like this</a>
		</c:forEach>
        <br>
        <br>
        
        
        
        
<!--         // for post in posts, post.user_id.name says post.content
        // a href "/like" Like, # of people like --> 

			<br>
			    <form id="logoutForm" method="POST" action="/logout">
		    		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		    	    		<input type="submit" value="Logout" />
			    </form>
		</div>
	</body>
</html>