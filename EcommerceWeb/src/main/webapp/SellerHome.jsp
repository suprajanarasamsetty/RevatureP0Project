<%@ page import="java.util.List" %>
<%@ page import="com.dto.ProductResponse" %>
<%@ page import="com.service.ProductService" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 30px;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .header h1 {
            color: #007bff;
            font-size: 2.5rem;
            margin-bottom: 10px;
        }
        .header p {
            font-size: 1.2rem;
            color: #666;
        }
        .btn {
            margin: 5px;
        }
        .product-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }
        .product-card {
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .product-card:hover {
            transform: scale(1.02);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
        .product-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-bottom: 1px solid #ddd;
        }
        .product-info {
            padding: 15px;
        }
        .product-name {
            font-size: 1.5rem;
            color: #007bff;
            margin: 10px 0;
        }
        .product-description, .product-price {
            font-size: 1rem;
            color: #555;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
    </style>
</head>
<body>

<%
    Integer sellerId = (Integer) session.getAttribute("seller_id");

    if (sellerId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    ProductService productService = new ProductService();
    List<ProductResponse> products = null;

    try {
        products = productService.getProductsBySellerId(sellerId);
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<p>Error retrieving products: " + e.getMessage() + "</p>");
    }
%>

<div class="container">
    <div class="header">
        <h1>Welcome to Your Seller Dashboard</h1>
        <p>Manage your products and update your account information.</p>
        <a href="AddProduct.jsp?sellerId=<%= sellerId %>" class="btn btn-primary">Add New Product</a>
        <a href="UpdateAccount.jsp" class="btn btn-secondary">Update Account Information</a>
    </div>

    <h2 class="text-center">Your Products</h2>

    <% if (products != null && !products.isEmpty()) { %>
        <div class="product-grid">
            <% for (ProductResponse product : products) { %>
                <div class="product-card">
                    <img src="<%= product.getProductImage() %>" alt="<%= product.getProductName() %>" class="product-image">
                    <div class="product-info">
                        <h3 class="product-name"><%= product.getProductName() %></h3>
                        <p class="product-description"><strong>Description:</strong> <%= product.getProductDescription() %></p>
                        <p class="product-price"><strong>Price:</strong> &#8377;<%= product.getProductPrice() %></p>
                        <a href="<%= request.getContextPath() %>/UpdateProduct.jsp?productId=<%= product.getProductID() %>" class="btn btn-success">Edit</a>
                        <button type="button" class="btn btn-danger" onclick="deleteProduct(<%= product.getProductID() %>)">Delete</button>
                    </div>
                </div>
            <% } %>
        </div>
    <% } else { %>
        <p class="text-center">No products found.</p>
    <% } %>
</div>

<script>
function deleteProduct(productId) {
    if (confirm('Are you sure you want to delete this product?')) {
        fetch('<%= request.getContextPath() %>/Product', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'ProductID': productId
            })
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => Promise.reject(text));
            }
            return response.text();
        })
        .then(result => {
            alert(result);
            window.location.reload(); // Reload the page to reflect changes
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to delete product: ' + error);
        });
    }
}
</script>

</body>
</html>
