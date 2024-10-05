<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buyer Dashboard Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 20px;
        }
        .welcome-header {
            margin-bottom: 30px;
        }
        .card {
            margin-bottom: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-body {
            padding: 20px;
        }
        .card-title {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: white;
            cursor: pointer;
        }
        .section-title {
            margin-top: 20px;
            margin-bottom: 10px;
            font-size: 1.25rem;
            font-weight: bold;
        }
        /* Color Customizations */
        .featured-products-title {
            color: #28a745; /* Green */
        }
        .recent-orders-title {
            color: #ffc107; /* Yellow */
        }
        .special-offers-title {
            color: #dc3545; /* Red */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="welcome-header">
            <h1 class="text-center">Welcome to Your Dashboard, [Buyer Name]</h1>
            <p class="text-center">Here you can explore products, manage your orders, and update your profile.</p>
        </div>
        
        <div class="row">
            <!-- Featured Products Card -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title featured-products-title">Featured Products</h5>
                        <p class="card-text">Discover the latest products we've added just for you!</p>
                        <a href="Product/all" class="btn btn-custom">View Products</a>
                    </div>
                </div>
            </div>
            
            <!-- Recent Orders Card -->
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title recent-orders-title">Recent Orders</h5>
                        <p class="card-text">Check the status of your recent orders and manage them.</p>
                        <a href="BuyerOrders" class="btn btn-custom">View Orders</a>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Additional Information or Offers Section -->
        <div class="section-title special-offers-title">Special Offers & Updates</div>
        <div class="card">
            <div class="card-body">
                <p class="card-text">Stay tuned for our special offers and updates. We have exciting deals and new features coming your way!</p>
                <a href="SpecialOffers.jsp" class="btn btn-custom">Explore Offers</a>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
