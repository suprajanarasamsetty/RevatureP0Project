<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dto.ProductResponse" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            overflow: hidden;
        }
        header {
            background: #333;
            color: #fff;
            padding-top: 30px;
            min-height: 70px;
            border-bottom: #ddd 3px solid;
            text-align: center;
        }
        header h1 {
            margin: 0;
        }
        .product-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-top: 20px;
        }
        .product-item {
            background: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 23%;
            box-sizing: border-box;
        }
        .product-item img {
            max-width: 100%;
            height: auto;
        }
        .product-item h2 {
            font-size: 1.5em;
            margin: 0;
        }
        .product-item p {
            margin: 10px 0;
        }
        .product-item a {
            display: inline-block;
            padding: 10px 20px;
            color: #fff;
            background: #333;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .product-item a:hover {
            background: #555;
        }
    </style>
</head>
<body>
    <header>
        <div class="container">
            <h1>Product List</h1>
        </div>
    </header>

    <div class="container">
        <div class="product-list">
            <% 
                List<ProductResponse> products = (List<ProductResponse>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (ProductResponse product : products) {
            %>
                <div class="product-item">
                    <img src="<%= product.getProductImage() %>" alt="<%= product.getProductName() %>">
                    <h2><%= product.getProductName() %></h2>
                    <p>Price: $<%= product.getProductPrice() %></p>
                    <p><%= product.getProductDescription() %></p>
                    <a href="Product/<%= product.getProductID() %>">View Details</a>
                </div>
            <% 
                    }
                } else {
            %>
                <p>No products found.</p>
            <% 
                }
            %>
        </div>
    </div>
</body>
</html>
