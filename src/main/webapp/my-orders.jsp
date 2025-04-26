<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <title>My Orders - Thrifty Finds</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            </head>

            <body>
                <jsp:include page="/WEB-INF/includes/header.jsp" />

                <main class="container">
                    <h1>My Orders</h1>

                    <!-- Display message if available -->
                    <c:if test="${not empty sessionScope.message}">
                        <div class="alert alert-info">
                            ${sessionScope.message}
                            <c:remove var="message" scope="session" />
                        </div>
                    </c:if>

                    <c:choose>
                        <c:when test="${empty orders}">
                            <div class="no-orders">
                                <p>You haven't placed any orders yet.</p>
                                <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Browse
                                    Products</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="orders-list">
                                <c:forEach var="order" items="${orders}">
                                    <div class="order-card">
                                        <div class="order-header">
                                            <div class="order-id">
                                                <span>Order #${order.id}</span>
                                                <span class="order-date">
                                                    <fmt:formatDate value="${order.orderDate}" pattern="MMMM d, yyyy" />
                                                </span>
                                            </div>
                                            <div class="order-status status-${order.status}">
                                                ${order.status}
                                            </div>
                                        </div>

                                        <div class="order-product">
                                            <div class="product-image">
                                                <c:choose>
                                                    <c:when test="${not empty order.productImage}">
                                                        <img src="${pageContext.request.contextPath}/uploads/${order.productImage}"
                                                            alt="${order.productTitle}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="no-image">No Image</div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="product-info">
                                                <h3>${order.productTitle}</h3>
                                                <p class="product-price">$
                                                    <fmt:formatNumber value="${order.productPrice}"
                                                        pattern="#,##0.00" />
                                                </p>
                                                <p class="product-seller">Seller: ${order.sellerUsername}</p>
                                            </div>
                                        </div>

                                        <div class="order-details">
                                            <div class="shipping-address">
                                                <h4>Shipping Address</h4>
                                                <p>${order.shippingAddress}</p>
                                            </div>
                                            <div class="payment-details">
                                                <h4>Payment</h4>
                                                <p>Method: ${order.paymentMethod}</p>
                                                <p>Total: $
                                                    <fmt:formatNumber value="${order.totalAmount}" pattern="#,##0.00" />
                                                </p>
                                            </div>
                                        </div>

                                        <div class="order-actions">
                                            <a href="${pageContext.request.contextPath}/order/confirm/${order.id}"
                                                class="btn btn-secondary">View Details</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </main>

                <jsp:include page="/WEB-INF/includes/footer.jsp" />
            </body>

            </html>