<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.OrdersResponse" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Roboto', sans-serif;
        }
        .container {
            margin-top: 30px;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .order-header {
            background-color: #007bff;
            color: #fff;
            padding: 15px;
            font-size: 1.25rem;
            border-bottom: 2px solid #0056b3;
        }
        .order-body {
            padding: 20px;
            background-color: #fff;
        }
        .order-body p {
            margin: 0;
            padding: 10px 0;
            border-bottom: 1px solid #e9ecef;
        }
        .order-body p:last-child {
            border-bottom: none;
        }
        .order-body strong {
            color: #007bff;
        }
        @media (max-width: 576px) {
            .order-header {
                font-size: 1rem;
                padding: 10px;
            }
            .order-body {
                padding: 15px;
            }
            .order-body p {
                padding: 8px 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mb-4">Your Orders</h2>
        <% List<OrdersResponse> orders = (List<OrdersResponse>) request.getAttribute("orders");
           if (orders != null && !orders.isEmpty()) {
        %>
            <% for (OrdersResponse order : orders) { %>
                <div class="card mb-4">
                    <div class="order-header">
                        Order ID: <%= order.getOrder_id() %>
                    </div>
                    <div class="order-body">
                        <p><strong>Shipping Address:</strong> <%= order.getShippingAddress() %></p>
                        <p><strong>Billing Address:</strong> <%= order.getBillingAddress() %></p>
                        <p><strong>Order Date:</strong> <%= order.getOrder_Date() %></p>
                        <p><strong>Order Status:</strong> <%= order.getOrder_Status() %></p>
                    </div>
                </div>
            <% } %>
        <% } else { %>
            <p class="text-center">No orders found.</p>
        <% } %>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
