<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Thrifty Finds</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <div class="form-container">
            <h1>Login</h1>
            
            <!-- Display success message if available -->
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-success">
                    ${sessionScope.message}
                    <c:remove var="message" scope="session" />
                </div>
            </c:if>
            
            <!-- Display error message if available -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="${username}" required>
                    <c:if test="${not empty usernameError}">
                        <span class="error">${usernameError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                    <c:if test="${not empty passwordError}">
                        <span class="error">${passwordError}</span>
                    </c:if>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
            
            <div class="form-footer">
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></p>
            </div>
        </div>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
