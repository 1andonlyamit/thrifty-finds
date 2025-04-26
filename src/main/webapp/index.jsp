<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thrifty Finds - Buy and Sell Pre-loved Items</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <div class="welcome-banner">
            <h1>Welcome to Thrifty Finds</h1>
            <p>Your one-stop shop for buying and selling pre-loved items</p>
            <div class="cta-buttons">
                <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Browse Products</a>
                <c:if test="${empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">Register Now</a>
                </c:if>
            </div>
        </div>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
