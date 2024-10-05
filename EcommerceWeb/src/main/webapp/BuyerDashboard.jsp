<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        /* Additional custom styling */
        body {
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }
        .navbar-nav .nav-link {
            color: #fff;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .search-bar {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
        }
        .sidebar {
            padding: 15px;
            background-color: #f8f9fa;
            height: 100%;
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            width: 250px;
            border-right: 1px solid #dee2e6;
        }
        .content {
            margin-left: 270px;
            padding: 20px;
        }
        .featured-products, .categories {
            margin-bottom: 20px;
        }
        .featured-products img, .categories img {
            max-width: 100%;
        }
        .btn-custom {
            background-color: #007bff;
            color: #fff;
            border-radius: 5px;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: #fff;
            transform: translateY(-2px);
        }
        .btn-secondary-custom {
            background-color: #6c757d;
            color: #fff;
            border-radius: 5px;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }
        .btn-secondary-custom:hover {
            background-color: #5a6268;
            color: #fff;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Ecommerce Web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="BuyerHome.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Product/all">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Category/all">Categories</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="BuyerOrders">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Cart">Cart</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Wishlist">Wishlist</a>
            </li>
            <li class="nav-item">             
             <a class="nav-link" href="orderItems?orderId=${sessionScope.orderId}">Order Items</a>                  
           </li>
        </ul>
    </div>
</nav>

<!-- Sidebar -->
<div class="sidebar">
    <h4>Dashboard</h4>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="BuyerHome.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Product/">Products</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Category/all">Categories</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="BuyerOrders">Orders</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="profile?user_id=${sessionScope.user_id}">Profile</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Cart">Cart</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Wishlist.jsp">Wishlist</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="orderItems?orderId=${sessionScope.orderId}">Order Items</a>
        </li>
    </ul>
</div>


<!-- Main Content -->
<div class="content">
    <!-- Search Bar -->
    <div class="container">
        <form action="search" method="get" class="search-bar">
            <div class="input-group">
                <input type="text" class="form-control" name="query" placeholder="Search for products..." aria-label="Search" required>
                <div class="input-group-append">
                    <button class="btn btn-secondary-custom" type="submit">Search</button>
                </div>
            </div>
        </form>
    </div>

    <!-- Featured Products -->
    <div class="container featured-products">
        <h2>Featured Products</h2>
        <div class="row">
            <!-- Example Product Card -->
            <div class="col-md-4">
                <div class="card">
                    <img src="" class="card-img-top" alt="Product 1">
                    <div class="card-body">
                        <h5 class="card-title">Product 1</h5>
                        <p class="card-text">7000.00</p>
                        <a href="ProductDetails.jsp?id=1" class="btn btn-custom">View Details</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="https://i.imghippo.com/files/HB1Nw1725095815.jpg" alt="" class="card-img-top" alt="Product 2">
                    <div class="card-body">
                        <h5 class="card-title">Product 2</h5>
                        <p class="card-text">2499.00</p>
                        <a href="product.jsp?id=2" class="btn btn-custom">View Details</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="images/product3.jpg" class="card-img-top" alt="Product 3">
                    <div class="card-body">
                        <h5 class="card-title">Product 3</h5>
                        <p class="card-text">$30.00</p>
                        <a href="product.jsp?id=3" class="btn btn-custom">View Details</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Categories -->
    <div class="container categories">
        <h2>Categories</h2>
        <div class="row">
            <!-- Example Category Card -->
            <div class="col-md-4">
                <div class="card">
                    <img src="images/category1.jpg" class="card-img-top" alt="Category 1">
                    <div class="card-body">
                        <h5 class="card-title">Category 1</h5>
                        <a href="category.jsp?id=1" class="btn btn-custom">Explore</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="images/category2.jpg" class="card-img-top" alt="Category 2">
                    <div class="card-body">
                        <h5 class="card-title">Category 2</h5>
                        <a href="category.jsp?id=2" class="btn btn-custom">Explore</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <img src="images/category3.jpg" class="card-img-top" alt="Category 3">
                    <div class="card-body">
                        <h5 class="card-title">Category 3</h5>
                        <a href="category.jsp?id=3" class="btn btn-custom">Explore</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
