<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.dto.OrdersResponse" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Orders for Seller</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Shipping Address</th>
                    <th>Billing Address</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Product ID</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<OrdersResponse> orders = (List<OrdersResponse>) request.getAttribute("orders");
                    if (orders != null && !orders.isEmpty()) {
                        for (OrdersResponse order : orders) {
                %>
                <tr>
                    <td><%= order.getOrder_id() %></td>
                    <td><%= order.getShippingAddress() %></td>
                    <td><%= order.getBillingAddress() %></td>
                    <td><%= order.getOrder_Date() %></td>
                    <td><%= order.getOrder_Status() %></td>
                    <td><%= order.getProductID() %></td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No orders found.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
