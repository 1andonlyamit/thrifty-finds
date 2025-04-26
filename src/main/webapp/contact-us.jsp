<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - Thrifty Finds</title>
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
        <section class="contact-hero text-center bg-light p-5 rounded-3 mb-5">
            <h1 class="display-4 fw-bold text-primary">Contact Us</h1>
            <p class="lead mb-4">We'd love to hear from you! Connect with the ThriftyFinds team.</p>
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="contact-info d-flex flex-wrap justify-content-center gap-4 mb-4">
                        <div class="contact-item">
                            <i class="fas fa-envelope fa-2x text-primary mb-2"></i>
                            <p class="mb-0">support@thriftyfinds.com</p>
                        </div>
                        <div class="contact-item">
                            <i class="fas fa-phone fa-2x text-primary mb-2"></i>
                            <p class="mb-0">(555) 123-4567</p>
                        </div>
                        <div class="contact-item">
                            <i class="fas fa-map-marker-alt fa-2x text-primary mb-2"></i>
                            <p class="mb-0">123 Thrifty Street, Cityville</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="row g-5">
            <!-- Contact Form -->
            <div class="col-lg-7">
                <div class="card border-0 shadow-sm">
                    <div class="card-body p-4 p-md-5">
                        <h2 class="fw-bold mb-4">Send Us a Message</h2>
                        
                        <c:if test="${not empty message}">
                            <div class="alert alert-${messageType} alert-dismissible fade show" role="alert">
                                ${message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        
                        <form action="${pageContext.request.contextPath}/contact" method="post" id="contactForm">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="name" class="form-label">Your Name</label>
                                    <input type="text" class="form-control" id="name" name="name" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="email" class="form-label">Email Address</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="subject" class="form-label">Subject</label>
                                <select class="form-select" id="subject" name="subject" required>
                                    <option value="" selected disabled>Select a topic</option>
                                    <option value="General Inquiry">General Inquiry</option>
                                    <option value="Technical Support">Technical Support</option>
                                    <option value="Account Issues">Account Issues</option>
                                    <option value="Reporting a Problem">Reporting a Problem</option>
                                    <option value="Feedback">Feedback & Suggestions</option>
                                    <option value="Business">Business Opportunities</option>
                                </select>
                            </div>
                            
                            <div class="mb-4">
                                <label for="message" class="form-label">Your Message</label>
                                <textarea class="form-control" id="message" name="message" rows="5" required></textarea>
                            </div>
                            
                            <div class="form-check mb-4">
                                <input class="form-check-input" type="checkbox" id="newsletter" name="newsletter">
                                <label class="form-check-label" for="newsletter">
                                    Subscribe to our newsletter for tips on sustainable living and thrifty finds
                                </label>
                            </div>
                            
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">Send Message</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            
            <!-- FAQ and Additional Info -->
            <div class="col-lg-5">
                <!-- FAQ Section -->
                <div class="card border-0 shadow-sm mb-4">
                    <div class="card-body p-4">
                        <h3 class="fw-bold mb-3">Frequently Asked Questions</h3>
                        
                        <div class="accordion" id="accordionFAQ">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        How do I create an account?
                                    </button>
                                </h2>
                                <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionFAQ">
                                    <div class="accordion-body">
                                        Click on the "Register Now" button in the menu, fill out the required information, and follow the verification steps to create your ThriftyFinds account.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingTwo">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        How do I list an item for sale?
                                    </button>
                                </h2>
                                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionFAQ">
                                    <div class="accordion-body">
                                        After logging in, go to your profile and click "Post Item". Fill out the item details including photos, description, price, and your contact information.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingThree">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        Is ThriftyFinds available in my city?
                                    </button>
                                </h2>
                                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionFAQ">
                                    <div class="accordion-body">
                                        ThriftyFinds is available nationwide. You can filter items by location to find sellers in your area, or post items with your city to help local buyers find you.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Social Media and Support Hours -->
                <div class="card border-0 shadow-sm">
                    <div class="card-body p-4">
                        <h3 class="fw-bold mb-3">Connect With Us</h3>
                        
                        <div class="social-links mb-4">
                            <p class="mb-3">Follow us on social media:</p>
                            <div class="d-flex gap-3">
                                <a href="#" class="text-decoration-none">
                                    <i class="fab fa-facebook fa-2x text-primary"></i>
                                </a>
                                <a href="#" class="text-decoration-none">
                                    <i class="fab fa-instagram fa-2x text-danger"></i>
                                </a>
                                <a href="#" class="text-decoration-none">
                                    <i class="fab fa-twitter fa-2x text-info"></i>
                                </a>
                                <a href="#" class="text-decoration-none">
                                    <i class="fab fa-pinterest fa-2x text-danger"></i>
                                </a>
                            </div>
                        </div>
                        
                        <div class="support-hours">
                            <h4 class="h5 mb-3">Customer Support Hours</h4>
                            <ul class="list-unstyled">
                                <li><i class="fas fa-clock me-2 text-muted"></i> Monday - Friday: 9am - 6pm</li>
                                <li><i class="fas fa-clock me-2 text-muted"></i> Saturday: 10am - 4pm</li>
                                <li><i class="fas fa-clock me-2 text-muted"></i> Sunday: Closed</li>
                            </ul>
                            <p class="mt-3 mb-0">
                                We typically respond to email inquiries within 24 hours during business days.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Map Section -->
        <section class="map-section mt-5 p-3 bg-light rounded-3">
            <h3 class="text-center mb-4">Find Us</h3>
            <div class="ratio ratio-21x9" style="max-height: 400px;">
                <!-- Replace with your actual map embed code -->
                <div class="bg-secondary d-flex align-items-center justify-content-center">
                    <p class="text-white">Map will be displayed here</p>
                    <!-- You can replace this placeholder with an actual Google Maps embed code -->
                    <!-- <iframe src="https://www.google.com/maps/embed?pb=..." width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe> -->
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Form Validation Script -->
    <script>
        // Simple client-side form validation
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('contactForm');
            
            if (form) {
                form.addEventListener('submit', function(event) {
                    const name = document.getElementById('name').value.trim();
                    const email = document.getElementById('email').value.trim();
                    const message = document.getElementById('message').value.trim();
                    
                    let isValid = true;
                    
                    if (name === '') {
                        isValid = false;
                        document.getElementById('name').classList.add('is-invalid');
                    } else {
                        document.getElementById('name').classList.remove('is-invalid');
                    }
                    
                    if (email === '' || !email.includes('@')) {
                        isValid = false;
                        document.getElementById('email').classList.add('is-invalid');
                    } else {
                        document.getElementById('email').classList.remove('is-invalid');
                    }
                    
                    if (message === '') {
                        isValid = false;
                        document.getElementById('message').classList.add('is-invalid');
                    } else {
                        document.getElementById('message').classList.remove('is-invalid');
                    }
                    
                    if (!isValid) {
                        event.preventDefault();
                    }
                });
            }
        });
    </script>
</body>
</html>