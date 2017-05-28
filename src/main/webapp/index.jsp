<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Main</title>
	<link href="assets/css/bootstrap-united.css" rel="stylesheet" />
	<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
	<style>
		body {
			height: 100%;
			margin: 0;
			background: url(assets/img/books.jpg);
			background-size: 1920px 1080px;
			background-repeat: no-repeat;
			display: compact;
		}
	</style>
</head>
<body>
<div class="navbar navbar-default">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
	</div>

	<div class="navbar-collapse collapse navbar-responsive-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a href="#">Home</a></li>
			<c:choose>
				<c:when test="${curTeacher == null}">
					<li><a href="signup.html">Signup</a></li>
					<li><a href="login.html">Login</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="students.html">Students</a></li>
					<li><a href="addStudent.html">Add Student</a></li>
					<li><a href="logout.html">Logout</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- /.nav-collapse -->
</div>
<div class="container">
	<div class="jumbotron">
		<c:choose>
			<c:when test="${curTeacher == null}">
				<div>
					<h1>Welcome to Online Web Academy!</h1>
					<p>To get started, you need to enter your user name and the password for registration.
						Or login to access your students, if you are already registered.</p>
				</div>
				<a class="btn btn-primary" href="signup.html">Signup >> </a>
				<a class="btn btn-primary" href="login.html">Login >> </a>
			</c:when>
			<c:otherwise>
				<div>
					<h1>Welcome to Online Web Academy!</h1>
					<p>You have successfully passed a logination if you want to enter under other login, you have to log out</p>
				</div>
				<a class="btn btn-primary" href="logout.html">Logout >> </a>
				<a class="btn btn-primary" href="addStudent.html">Add student >> </a>
				<a class="btn btn-primary" href="students.html">Students >> </a>
			</c:otherwise>
		</c:choose>
	</div>

	<div></div>
</div>
<script src="jquery-1.8.3.js">
</script>

<script src="bootstrap/js/bootstrap.js">
</script>

</body>
</html>