<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-danger text-white">
                        <h4 class="mb-0">Error</h4>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">An error occurred</h5>
                        <p class="card-text">
                            <% if (exception != null) { %>
                                <%= exception.getMessage() %>
                            <% } else { %>
                                Please try again later or contact support if the problem persists.
                            <% } %>
                        </p>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Back to Login</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 