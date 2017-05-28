<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Students</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="datepicker/css/datepicker.css" rel="stylesheet" />
    <link href="assets/css/bootstrap-united.css" rel="stylesheet" />

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
                <li><a href="/">Home</a></li>
                <c:choose>
                    <c:when test="${curTeacher == null}">
                        <li><a href="signup.html">Signup</a></li>
                        <li><a href="login.html">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="active"><a href="students.html">Students</a></li>
                        <li><a href="addStudent.html">Add Student</a></li>
                        <li><a href="logout.html">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!-- /.nav-collapse -->
    </div>

    <script src="jquery-1.8.3.js">

    </script>

    <script src="bootstrap/js/bootstrap.js">

    </script>

    <script src="datepicker/js/bootstrap-datepicker.js">

    </script>


    <div class="container">
        <div class="jumbotron">
            <div>
                <h1>Your student table</h1>
                <p>You can see students and delete its from here</p>
            </div>
        </div>

        <div></div>
    </div>

    <div class="col-lg-6 col-lg-offset-3">
        <div class="well">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10">
                        <table class="table table-stripped">
                            <a class="btn btn-primary" href="<c:url value='/addStudent.html' />">Add new student</a>
                            <c:if test="${fn:length(student) > 0}">
                                <tr>
                                    <th>No</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>Date of Birth</th>
                                    <th>Email address</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${student}" var="student" varStatus="itr">
                                    <tr>
                                        <td>${itr.index +1 }</td>
                                        <td>${student.firstName}</td>
                                        <td>${student.lastName}</td>
                                        <td><fmt:formatDate value="${student.dateOfBirth}" pattern="dd/MM/yyyy" /></td>
                                        <td>${student.emailAddress}</td>
                                        <td><a class="btn btn-danger" style="padding: 0"  href="students/${student.id}.html">
                                            Delete</a></td>
                                        <td><a class="btn btn-primary" style="padding: 0" href="signup.html">edit</a></td>
                                        <td><a href="<c:url value='/view-${book.name}-book' />">view</a></td>
                                    </tr
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
