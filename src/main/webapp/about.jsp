<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Thrifty Finds</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <main class="container my-5">
        <!-- Hero Section -->
        <section class="about-hero bg-light p-5 rounded-3 mb-5">
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <h1 class="display-4 fw-bold text-primary">About Thrifty Finds</h1>
                    <p class="lead">Your sustainable marketplace for pre-loved items</p>
                    <p class="mb-4">Connecting buyers and sellers in a community that values sustainability, affordability, and giving items a second life.</p>
                </div>
                <div class="col-lg-6 text-center">
                    <img src="https://img.freepik.com/free-photo/high-angle-clothes-bed-fast-fashion_23-2149726119.jpg?semt=ais_hybrid&w=740" 
     alt="ThriftyFinds Marketplace" 
     class="img-fluid rounded shadow" 
     onerror="this.src='${pageContext.request.contextPath}/images/placeholder.jpg'">
                    
                </div>
            </div>
        </section>

        <!-- Our Mission -->
        <section class="mb-5">
            <div class="row">
                <div class="col-lg-8 mx-auto text-center">
                    <h2 class="fw-bold mb-4">Our Mission</h2>
                    <p class="lead mb-4">At ThriftyFinds, we're on a mission to reduce waste, promote sustainability, and make quality items accessible to everyone.</p>
                    <div class="d-flex justify-content-around flex-wrap">
                        <div class="mission-item text-center p-3">
                            <i class="fas fa-recycle fa-3x text-success mb-3"></i>
                            <h5>Sustainability</h5>
                        </div>
                        <div class="mission-item text-center p-3">
                            <i class="fas fa-hand-holding-dollar fa-3x text-primary mb-3"></i>
                            <h5>Affordability</h5>
                        </div>
                        <div class="mission-item text-center p-3">
                            <i class="fas fa-users fa-3x text-warning mb-3"></i>
                            <h5>Community</h5>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- How It Works -->
        <section class="how-it-works bg-light p-5 rounded-3 mb-5">
            <h2 class="text-center fw-bold mb-5">How ThriftyFinds Works</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card h-100 border-0 shadow-sm">
                        <div class="card-body text-center">
                            <div class="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 70px; height: 70px;">
                                <i class="fas fa-user-plus fa-2x"></i>
                            </div>
                            <h4 class="card-title">1. Sign Up</h4>
                            <p class="card-text">Create your free account to start buying or selling pre-loved items in minutes.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 border-0 shadow-sm">
                        <div class="card-body text-center">
                            <div class="rounded-circle bg-success text-white d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 70px; height: 70px;">
                                <i class="fas fa-camera fa-2x"></i>
                            </div>
                            <h4 class="card-title">2. Post Items</h4>
                            <p class="card-text">List your items for sale or post what you're looking to buy from other members.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 border-0 shadow-sm">
                        <div class="card-body text-center">
                            <div class="rounded-circle bg-warning text-white d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 70px; height: 70px;">
                                <i class="fas fa-handshake fa-2x"></i>
                            </div>
                            <h4 class="card-title">3. Connect</h4>
                            <p class="card-text">Contact sellers directly, arrange meets, and give pre-loved items a new home.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Why Choose Us -->
        <section class="mb-5">
            <h2 class="text-center fw-bold mb-4">Why Choose ThriftyFinds</h2>
            <div class="row g-4">
                <div class="col-md-6">
                    <div class="d-flex align-items-start">
                        <div class="flex-shrink-0 me-3">
                            <i class="fas fa-check-circle text-success fa-2x"></i>
                        </div>
                        <div>
                            <h4>Easy to Use</h4>
                            <p>Our platform is designed to be intuitive and user-friendly, making buying and selling a breeze.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="d-flex align-items-start">
                        <div class="flex-shrink-0 me-3">
                            <i class="fas fa-search text-primary fa-2x"></i>
                        </div>
                        <div>
                            <h4>Advanced Filtering</h4>
                            <p>Find exactly what you're looking for with our category browsing and filtering options.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="d-flex align-items-start">
                        <div class="flex-shrink-0 me-3">
                            <i class="fas fa-shield-alt text-warning fa-2x"></i>
                        </div>
                        <div>
                            <h4>Secure Community</h4>
                            <p>User profiles and verification help build trust in our marketplace community.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="d-flex align-items-start">
                        <div class="flex-shrink-0 me-3">
                            <i class="fas fa-globe text-info fa-2x"></i>
                        </div>
                        <div>
                            <h4>Local Focus</h4>
                            <p>Connect with buyers and sellers in your city for convenient exchanges.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Team Section -->
        <section class="team bg-light p-5 rounded-3 mb-5">
            <h2 class="text-center fw-bold mb-5">Meet Our Team</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card text-center h-100 border-0 shadow-sm">
                        <div class="card-body">
                            <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 100px; height: 100px;">
                                <i class="fas fa-user fa-3x text-white"></i>
                            </div>
                            <h4>Amit Suthar</h4>
                            <p class="text-muted">Founder & CEO</p>
                            <p>Passionate about sustainable living and building communities.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center h-100 border-0 shadow-sm">
                        <div class="card-body">
                            <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 100px; height: 100px;">
                                <i class="fas fa-user fa-3x text-white"></i>
                            </div>
                            <h4>Zaahid Vohra</h4>
                            <p class="text-muted">Tech Lead</p>
                            <p>Creating the best user experience for our community members.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center h-100 border-0 shadow-sm">
                        <div class="card-body">
                            <div class="rounded-circle bg-secondary d-flex align-items-center justify-content-center mx-auto mb-3" style="width: 100px; height: 100px;">
                                <i class="fas fa-user fa-3x text-white"></i>
                            </div>
                            <h4>Taneem Mahudawala</h4>
                            <p class="text-muted">Community Manager</p>
                            <p>Helping our members connect and find what they're looking for.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Join Us CTA -->
        <section class="text-center">
            <div class="p-5 bg-primary text-white rounded-3">
                <h2 class="fw-bold mb-3">Ready to Join ThriftyFinds?</h2>
                <p class="lead mb-4">Start buying and selling pre-loved items today!</p>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-light btn-lg px-4 me-sm-3">Sign Up Now</a>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-light btn-lg px-4">Browse Items</a>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>