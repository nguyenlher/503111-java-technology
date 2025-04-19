<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Welcome</a>
            <div class="navbar-nav ms-auto">
                <span class="nav-item nav-link text-light">
                    Welcome, ${sessionScope.user.username}
                </span>
                <a class="nav-item nav-link" href="logout">Logout</a>
            </div>
        </div>
    </nav>
    
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12">
                <h2>Welcome to the Application</h2>
                <p>You have successfully logged in!</p>
            </div>
        </div>
    </div>
</body>
</html>
