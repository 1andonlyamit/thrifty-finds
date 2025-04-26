<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="site-header">
    <div class="container">
        <div class="header-content">
            <div class="logo">
                <a href="${pageContext.request.contextPath}/">
                    <h1>Thrifty Finds</h1>
                </a>
            </div>
            
            <div class="search-form">
                <form action="${pageContext.request.contextPath}/search" method="get">
                    <input type="text" name="q" placeholder="Search products..." required>
                    <button type="submit" class="btn btn-search">Search</button>
                </form>
            </div>
            
            <nav class="main-nav">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                            <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/add-product">Sell Item</a></li>
                            <li>
                                <div class="user-menu">
                                    <span>Welcome, ${sessionScope.username}!</span>
                                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout">Logout</a>
                                </div>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </div>
</header>
