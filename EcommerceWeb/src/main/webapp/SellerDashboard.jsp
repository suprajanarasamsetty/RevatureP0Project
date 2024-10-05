<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }
        .navbar {
            border-bottom: 1px solid #dee2e6;
        }
        .navbar-brand {
            font-weight: bold;
            font-size: 24px;
        }
        .navbar-nav .nav-link {
            font-size: 16px;
            margin-left: 15px;
            padding: 10px 15px;
            border-radius: 4px;
            transition: background-color 0.3s, color 0.3s;
        }
        .navbar-nav .nav-link.home {
            background-color: #28a745;
            color: #fff;
        }
        .navbar-nav .nav-link.home:hover {
            background-color: #218838;
            color: #fff;
        }
        .navbar-nav .nav-link.manage-products {
            background-color: #007bff;
            color: #fff;
        }
        .navbar-nav .nav-link.manage-products:hover {
            background-color: #0056b3;
            color: #fff;
        }
        .navbar-nav .nav-link.orders {
            background-color: #ffc107;
            color: #333;
        }
        .navbar-nav .nav-link.orders:hover {
            background-color: #e0a800;
            color: #333;
        }
        .navbar-nav .nav-link.profile {
            background-color: #17a2b8;
            color: #fff;
        }
        .navbar-nav .nav-link.profile:hover {
            background-color: #138496;
            color: #fff;
        }
        .navbar-nav .nav-link.logout {
            background-color: #dc3545;
            color: #fff;
        }
        .navbar-nav .nav-link.logout:hover {
            background-color: #c82333;
            color: #fff;
        }
        .dashboard {
            padding: 30px;
            margin: 20px auto;
            max-width: 1200px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .dashboard h2 {
            font-size: 28px;
            font-weight: 600;
            color: #333;
        }
        .dashboard p {
            font-size: 18px;
            color: #666;
            line-height: 1.6;
        }
        .card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card-header {
            background-color: #007bff;
            color: #fff;
            font-weight: 600;
            font-size: 18px;
            border-bottom: 1px solid #0056b3;
        }
        .card-body {
            padding: 20px;
        }
        .btn-custom {
            background-color: #007bff;
            color: #fff;
            border-radius: 4px;
            border: none;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: #fff;
            cursor: pointer;
        }
    </style>
</head>
<body>



    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Seller Dashboard</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                <%
				    Integer sellerIdFromSession = (session != null) ? (Integer) session.getAttribute("seller_id") : null;
				%>
                    <a class="nav-link home" href="SellerHome.jsp?sellerId=<%= sellerIdFromSession != null ? sellerIdFromSession : "" %>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link manage-products" href="ManageProducts.jsp?sellerId=<%= sellerIdFromSession != null ? sellerIdFromSession : "" %>">Manage Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link orders" href="sellerOrders?SellerID=${sessionScope.user_id}">Orders</a>
                </li>
                <li class="nav-item">
					<% 
					    // Retrieve the sellerId from the session
					    Integer sellerId = (Integer) session.getAttribute("seller_id");
					    // Check if sellerId is not null, otherwise, you might want to handle the case where it is null
					%>                    <a class="nav-link profile" href="profile?user_id=${sessionScope.user_id}&sellerId=${sellerId}">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link logout" href="login.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    
    <div class="dashboard">
        <h2>Welcome to the Seller Dashboard</h2>
        <p>Here you can manage your products, view orders, and handle your account settings.</p>
        <!-- Example card component for seller-specific content -->
        <div class="card">
            <div class="card-header">
                Recent Activity
            </div>
            <div class="card-body">
                <p>No recent activity.</p>
            </div>
        </div>
        <!-- Add more seller-specific content here -->
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
