<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register - Thrifty Finds</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <div class="form-container">
            <h1>Create an Account</h1>
            
            <!-- Display error message if available -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="${username}" required>
                    <c:if test="${not empty usernameError}">
                        <span class="error">${usernameError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${email}" required>
                    <c:if test="${not empty emailError}">
                        <span class="error">${emailError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                    <c:if test="${not empty passwordError}">
                        <span class="error">${passwordError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                    <c:if test="${not empty confirmPasswordError}">
                        <span class="error">${confirmPasswordError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" value="${fullName}" required>
                    <c:if test="${not empty fullNameError}">
                        <span class="error">${fullNameError}</span>
                    </c:if>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
            
            <div class="form-footer">
                <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
            </div>
        </div>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
