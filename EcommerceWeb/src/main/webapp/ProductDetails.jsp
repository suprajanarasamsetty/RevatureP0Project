<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-details {
            margin: 20px;
        }
        .product-image {
            max-width: 100%;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="container product-details">
        <%
            // Simulate fetching product details from database
            String productId = request.getParameter("id");
            // You would typically fetch this from a database
            // For example purposes, hardcoding product details
            String productName = "Product " + productId;
            String productDescription = "Detailed description for Product " + productId;
            String productPrice = "₹" + (10 * Integer.parseInt(productId));
            String productImage = "ProductImages/Dior Sauvage Eau de Toilette" + productId + ".jpg";
        %>
        <h1><%= productName %></h1>
        <img src="<%= productImage %>" alt="<%= productName %>" class="product-image">
        <p><%= productDescription %></p>
        <p>Price: <%= productPrice %></p>
        <a href="BuyerDashboard.jsp" class="btn btn-secondary">Back to Products</a>
    </div>
</body>
</html>
