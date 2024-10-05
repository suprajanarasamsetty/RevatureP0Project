<%@ page import="java.util.List" %>
<%@ page import="com.dto.ProductResponse" %>
<%@ page import="com.service.ProductService" %>
<%@ page import="com.entity.Role" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Manage Products</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-top: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            margin: 5px;
        }

        .btn-primary {
            background-color: #007bff;
        }

        .btn-edit {
            background-color: #28a745;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        /* Product Grid Styles */
        .product-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            padding: 20px;
        }

        .product-card {
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            transition: transform 0.2s;
        }

        .product-card:hover {
            transform: scale(1.02);
        }

        .product-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .product-info {
            padding: 15px;
        }

        .product-name {
            font-size: 18px;
            color: #007bff;
            margin: 10px 0;
        }

        .product-description {
            font-size: 14px;
            color: #555;
        }

        .product-price {
            font-size: 16px;
            font-weight: bold;
            color: #333;
            margin: 10px 0;
        }

        .product-actions {
            margin-top: 10px;
        }

        .product-actions .btn {
            margin: 5px;
        }
    </style>
</head>
<body>
<script>
function sendDeleteRequest(productId) {
    console.log('Attempting to delete product with ID:', productId); // Debugging statement
    if (confirm("Are you sure you want to delete this product?")) {
        console.log('User confirmed deletion. Sending DELETE request...'); // Debugging statement
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
            console.log('Received response status:', response.status); // Debugging statement
            if (!response.ok) {
                console.error('Response was not OK. Status:', response.status); // Debugging statement
                return response.text().then(text => Promise.reject(text));
            }
            return response.text();
        })
        .then(result => {
            console.log('Server response text:', result); // Debugging statement
            alert(result);
            window.location.reload(); // Reload the page to reflect changes
        })
        .catch(error => {
            console.error('Error occurred during fetch operation:', error); // Debugging statement
            console.error('Error:', error);
            alert('Failed to delete product: ' + error);
        });
    }else {
        console.log('User canceled the deletion. No action taken.'); // Debugging statement
    }
}
 </script>

<%
    Integer sellerId = (Integer) session.getAttribute("seller_id");

    if (sellerId == null) {
        // Redirect to login page or show error message
        response.setContentType("text/html");
        out.println("<div class='error-message'><h2>Session Timeout</h2>");
        out.println("<p>Your session has expired. Please <a href='login.jsp'>login again</a>.</p></div>");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Optional, for status code
        return;
    }

    // Fetch products for the seller
    ProductService productService = new ProductService();
    List<ProductResponse> products = null;
    try {
        products = productService.getProductsBySellerId(sellerId);
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<p>Error retrieving products: " + e.getMessage() + "</p>");
    }
%>

</head>
<body>

<h1>Manage Products</h1>

<p>
    <a href="AddProduct.jsp?sellerId=<%= sellerId %>" class="btn btn-primary">Add Product</a>
</p>

<% if (products != null && !products.isEmpty()) { %>
    <div class="product-grid">
        <% for (ProductResponse product : products) { %>
            <div class="product-card">
                <img src="<%= product.getProductImage() %>" alt="<%= product.getProductName() %>" class="product-image">
                <div class="product-info">
                    <h2 class="product-name"><%= product.getProductName() %></h2>
                    <p class="product-price">&#8377;<%= product.getProductPrice() %></p> 
                    <p class="product-id"><strong>Product ID:</strong> <%= product.getProductID() %></p>
                    
                    <div class="product-actions">
                    <a href="<%= request.getContextPath() %>/Product/<%=product.getProductID() %>" class="btn btn-primary">View</a>
        </div>
    </div>
</div>
        <% } %>
    </div>
<% } else { %>
    <p>No products found for this seller.</p>
<% } %>

</body>
</html>
