<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Product - Thrifty Finds</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />
    
    <main class="container">
        <div class="form-container">
            <h1>Add a Product</h1>
            
            <!-- Display error message if available -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">
                    ${errorMessage}
                </div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/add-product" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" value="${title}" required>
                    <c:if test="${not empty titleError}">
                        <span class="error">${titleError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="5">${description}</textarea>
                </div>
                
                <div class="form-group">
                    <label for="price">Price ($)</label>
                    <input type="number" id="price" name="price" step="0.01" min="0.01" value="${price}" required>
                    <c:if test="${not empty priceError}">
                        <span class="error">${priceError}</span>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <label for="image">Product Image</label>
                    <input type="file" id="image" name="image" accept="image/jpeg, image/png" required>
                    <small class="form-text">Upload a JPG or PNG image (Max size: 10MB)</small>
                    <c:if test="${not empty imageError}">
                        <span class="error">${imageError}</span>
                    </c:if>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Add Product</button>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </main>
    
    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
