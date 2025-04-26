<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Search Results - Thrifty Finds</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <div class="page-header">
            <h1>Search Results for "${searchQuery}"</h1>
            <p class="search-result-count">Found ${resultCount} result<c:if test="${resultCount != 1}">s</c:if></p>
        </div>
        
        <div class="product-grid">
            <c:choose>
                <c:when test="${empty searchResults}">
                    <div class="no-products">
                        <p>No products found matching your search.</p>
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Browse All Products</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${searchResults}">
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
