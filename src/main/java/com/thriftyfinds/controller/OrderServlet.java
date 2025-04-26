package com.thriftyfinds.controller;

import com.thriftyfinds.dao.OrderDAO;
import com.thriftyfinds.dao.ProductDAO;
import com.thriftyfinds.model.Order;
import com.thriftyfinds.model.Product;
import com.thriftyfinds.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling orders
 */
@WebServlet(urlPatterns = { "/order/*", "/my-orders" })
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Check if user is logged in
        if (currentUser == null) {
            session.setAttribute("message", "Please log in to continue.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String pathInfo = request.getPathInfo();
        String servletPath = request.getServletPath();

        if ("/my-orders".equals(servletPath)) {
            // Show user's orders
            List<Order> orders = orderDAO.getOrdersByBuyer(currentUser.getId());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/my-orders.jsp").forward(request, response);
            return;
        }

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String[] pathParts = pathInfo.split("/");

        if (pathParts.length < 2) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String action = pathParts[1];

        switch (action) {
            case "checkout":
                // Show checkout form for a product
                if (pathParts.length >= 3) {
                    try {
                        int productId = Integer.parseInt(pathParts[2]);
                        Product product = productDAO.getProductById(productId);

                        if (product == null || !product.isAvailable()) {
                            session.setAttribute("message", "This product is no longer available.");
                            response.sendRedirect(request.getContextPath() + "/home");
                            return;
                        }

                        // Prevent buying own products
                        if (product.getSellerId() == currentUser.getId()) {
                            session.setAttribute("message", "You cannot buy your own product.");
                            response.sendRedirect(request.getContextPath() + "/product/" + productId);
                            return;
                        }

                        request.setAttribute("product", product);
                        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
                        return;
                    } catch (NumberFormatException e) {
                        response.sendRedirect(request.getContextPath() + "/home");
                        return;
                    }
                }
                break;

            case "confirm":
                // Show order confirmation page
                if (pathParts.length >= 3) {
                    try {
                        int orderId = Integer.parseInt(pathParts[2]);
                        Order order = orderDAO.getOrderById(orderId);

                        if (order == null || order.getBuyerId() != currentUser.getId()) {
                            session.setAttribute("message", "Order not found.");
                            response.sendRedirect(request.getContextPath() + "/my-orders");
                            return;
                        }

                        request.setAttribute("order", order);
                        request.getRequestDispatcher("/order-confirmation.jsp").forward(request, response);
                        return;
                    } catch (NumberFormatException e) {
                        response.sendRedirect(request.getContextPath() + "/my-orders");
                        return;
                    }
                }
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/home");
                break;
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        // Check if user is logged in
        if (currentUser == null) {
            session.setAttribute("message", "Please log in to continue.");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("place_order".equals(action)) {
            // Process the order
            try {
                int productId = Integer.parseInt(request.getParameter("product_id"));
                Product product = productDAO.getProductById(productId);

                if (product == null || !product.isAvailable()) {
                    session.setAttribute("message", "This product is no longer available.");
                    response.sendRedirect(request.getContextPath() + "/home");
                    return;
                }

                // Prevent buying own products
                if (product.getSellerId() == currentUser.getId()) {
                    session.setAttribute("message", "You cannot buy your own product.");
                    response.sendRedirect(request.getContextPath() + "/product/" + productId);
                    return;
                }

                // Create new order
                Order order = new Order();
                order.setProductId(productId);
                order.setBuyerId(currentUser.getId());
                order.setShippingAddress(request.getParameter("shipping_address"));
                order.setContactPhone(request.getParameter("contact_phone"));
                order.setPaymentMethod(request.getParameter("payment_method"));
                order.setTotalAmount(product.getPrice());

                // Save the order
                Order savedOrder = orderDAO.createOrder(order);

                if (savedOrder != null) {
                    session.setAttribute("message", "Order placed successfully!");
                    response.sendRedirect(request.getContextPath() + "/order/confirm/" + savedOrder.getId());
                } else {
                    session.setAttribute("message", "Failed to place order. Please try again.");
                    response.sendRedirect(request.getContextPath() + "/order/checkout/" + productId);
                }

            } catch (NumberFormatException e) {
                session.setAttribute("message", "Invalid product ID.");
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}