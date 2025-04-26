<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home - Thrifty Finds</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <!-- Display success message if available -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success">
                ${sessionScope.message}
                <c:remove var="message" scope="session" />
            </div>
        </c:if>
        
        <div class="page-header">
            <h1>Browse Products</h1>
            
            <c:if test="${not empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/add-product" class="btn btn-primary">
                    <span class="icon">+</span> Add Product
                </a>
            </c:if>
        </div>
        
        <div class="product-grid">
            <c:choose>
                <c:when test="${empty products}">
                    <div class="no-products">
                        <p>No products found. Be the first to add a product!</p>
                        <c:if test="${not empty sessionScope.user}">
                            <a href="${pageContext.request.contextPath}/add-product" class="btn btn-primary">Add Product</a>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${products}">
                        <div class="product-card">
                            <div class="product-image">
                                <c:choose>
                                    <c:when test="${not empty product.imageFilename}">
                                        <img src="${pageContext.request.contextPath}/uploads/${product.imageFilename}" alt="${product.title}">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="no-image">No Image</div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="product-info">
                                <h3 class="product-title">
                                    <a href="${pageContext.request.contextPath}/product?id=${product.id}">${product.title}</a>
                                </h3>
                                <p class="product-price">$<fmt:formatNumber value="${product.price}" pattern="#,##0.00" /></p>
                                <p class="product-seller">by ${product.sellerUsername}</p>
                                <p class="product-date">
                                    <fmt:formatDate value="${product.createdAt}" pattern="MMM d, yyyy" />
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
