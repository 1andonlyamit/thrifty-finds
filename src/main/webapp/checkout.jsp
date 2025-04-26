<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html>

            <head>
                <title>Checkout - Thrifty Finds</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            </head>

            <body>
                <jsp:include page="/WEB-INF/includes/header.jsp" />

                <main class="container">
                    <h1>Checkout</h1>

                    <!-- Display error message if available -->
                    <c:if test="${not empty sessionScope.message}">
                        <div class="alert alert-error">
                            ${sessionScope.message}
                            <c:remove var="message" scope="session" />
                        </div>
                    </c:if>

                    <div class="checkout-container">
                        <div class="product-summary">
                            <h2>Order Summary</h2>
                            <div class="product-card checkout-card">
                                <div class="product-image">
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
                                <div class="product-info">
                                    <h3>${product.title}</h3>
                                    <p class="product-price">$
                                        <fmt:formatNumber value="${product.price}" pattern="#,##0.00" />
                                    </p>
                                    <p class="product-seller">Seller: ${product.sellerUsername}</p>
                                </div>
                            </div>
                        </div>

                        <div class="checkout-form">
                            <h2>Shipping Information</h2>
                            <form action="${pageContext.request.contextPath}/order" method="post">
                                <input type="hidden" name="action" value="place_order">
                                <input type="hidden" name="product_id" value="${product.id}">

                                <div class="form-group">
                                    <label for="shipping_address">Shipping Address:</label>
                                    <textarea id="shipping_address" name="shipping_address" rows="3"
                                        required></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="contact_phone">Contact Phone:</label>
                                    <input type="tel" id="contact_phone" name="contact_phone" required>
                                </div>

                                <div class="form-group">
                                    <label for="payment_method">Payment Method:</label>
                                    <select id="payment_method" name="payment_method" required>
                                        <option value="">Select Payment Method</option>
                                        <option value="credit_card">Credit Card</option>
                                        <option value="paypal">PayPal</option>
                                        <option value="cash_on_delivery">Cash on Delivery</option>
                                    </select>
                                </div>

                                <div class="order-total">
                                    <h3>Total: $
                                        <fmt:formatNumber value="${product.price}" pattern="#,##0.00" />
                                    </h3>
                                </div>

                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">Place Order</button>
                                    <a href="${pageContext.request.contextPath}/product/${product.id}"
                                        class="btn btn-secondary">Cancel</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </main>

                <jsp:include page="/WEB-INF/includes/footer.jsp" />
            </body>

            </html>