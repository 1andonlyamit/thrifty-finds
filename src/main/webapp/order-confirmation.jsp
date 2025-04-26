<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <title>Order Confirmation - Thrifty Finds</title>
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

                    <div class="confirmation-container">
                        <div class="confirmation-header">
                            <h1>Order Confirmation</h1>
                            <div class="order-number">
                                <span>Order #${order.id}</span>
                                <span class="order-date">
                                    <fmt:formatDate value="${order.orderDate}" pattern="MMMM d, yyyy" />
                                </span>
                            </div>
                        </div>

                        <div class="confirmation-details">
                            <div class="product-summary">
                                <h2>Item Purchased</h2>
                                <div class="product-card confirmation-card">
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
                                            <fmt:formatNumber value="${order.productPrice}" pattern="#,##0.00" />
                                        </p>
                                        <p class="product-seller">Seller: ${order.sellerUsername}</p>
                                    </div>
                                </div>
                            </div>

                            <div class="order-info">
                                <div class="shipping-info">
                                    <h2>Shipping Information</h2>
                                    <p><strong>Address:</strong> ${order.shippingAddress}</p>
                                    <p><strong>Contact:</strong> ${order.contactPhone}</p>
                                </div>

                                <div class="payment-info">
                                    <h2>Payment Information</h2>
                                    <p><strong>Method:</strong> ${order.paymentMethod}</p>
                                    <p><strong>Total:</strong> $
                                        <fmt:formatNumber value="${order.totalAmount}" pattern="#,##0.00" />
                                    </p>
                                </div>

                                <div class="order-status">
                                    <h2>Order Status</h2>
                                    <p class="status-badge status-${order.status}">${order.status}</p>
                                </div>
                            </div>
                        </div>

                        <div class="confirmation-actions">
                            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Continue
                                Shopping</a>
                            <a href="${pageContext.request.contextPath}/my-orders" class="btn btn-secondary">View My
                                Orders</a>
                        </div>
                    </div>
                </main>

                <jsp:include page="/WEB-INF/includes/footer.jsp" />
            </body>

            </html>