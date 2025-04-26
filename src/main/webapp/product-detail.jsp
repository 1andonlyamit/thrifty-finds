<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <title>${product.title} - Thrifty Finds</title>
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

                    <div class="product-detail">
                        <div class="product-detail-image">
                            <c:choose>
                                <c:when test="${not empty product.imageFilename}">
                                    <img src="${pageContext.request.contextPath}/uploads/${product.imageFilename}"
                                        alt="${product.title}">
                                </c:when>
                                <c:otherwise>
                                    <div class="no-image">No Image</div>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="product-detail-info">
                            <h1 class="product-title">${product.title}</h1>

                            <div class="product-meta">
                                <p class="product-price">$
                                    <fmt:formatNumber value="${product.price}" pattern="#,##0.00" />
                                </p>
                                <p class="product-seller">Seller: ${product.sellerUsername}</p>
                                <p class="product-date">
                                    Listed on
                                    <fmt:formatDate value="${product.createdAt}" pattern="MMMM d, yyyy" />
                                </p>
                            </div>

                            <div class="product-description">
                                <h2>Description</h2>
                                <p>${product.description}</p>
                            </div>

                            <div class="product-actions">
                                <c:choose>
                                    <c:when
                                        test="${sessionScope.user != null && sessionScope.user.id != product.sellerId && product.isAvailable()}">
                                        <!-- Buy Now button (only for logged-in users who are not the seller) -->
                                        <a href="${pageContext.request.contextPath}/order/checkout/${product.id}"
                                            class="btn btn-primary">Buy Now</a>

                                        <!-- Contact button -->
                                        <button class="btn btn-secondary">Contact Seller</button>
                                    </c:when>
                                    <c:when
                                        test="${sessionScope.user != null && sessionScope.user.id == product.sellerId}">
                                        <!-- This is the user's own product -->
                                        <p class="product-own-message">This is your product</p>
                                    </c:when>
                                    <c:when test="${!product.isAvailable()}">
                                        <!-- Product is not available -->
                                        <p class="product-sold-message">This product has been sold</p>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- User is not logged in -->
                                        <a href="${pageContext.request.contextPath}/login.jsp"
                                            class="btn btn-primary">Log in to Buy</a>

                                        <!-- Buy Now button (visible to all, will redirect to login) -->
                                        <a href="${pageContext.request.contextPath}/order/checkout/${product.id}"
                                            class="btn btn-primary">Buy Now</a>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Back to listings button -->
                                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Back to
                                    Listings</a>
                            </div>
                        </div>
                    </div>
                </main>

                <jsp:include page="/WEB-INF/includes/footer.jsp" />
            </body>

            </html>