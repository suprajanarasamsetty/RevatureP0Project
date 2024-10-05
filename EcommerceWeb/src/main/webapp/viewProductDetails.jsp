<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.ProductResponse" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .container {
            margin-top: 30px;
        }

        .product-image {
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            height: auto;
        }

        .btn-danger, .btn-primary {
            margin-top: 10px;
            border-radius: 5px;
        }

        .btn-edit {
            background-color: #007bff;
            color: #fff;
            border: none;
        }

        .btn-edit:hover {
            background-color: #0056b3;
        }

        .btn-delete {
            background-color: #dc3545;
            color: #fff;
            border: none;
        }

        .btn-delete:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>Product Details</h2>
        <div class="row">
            <% 
            ProductResponse product = (ProductResponse) request.getAttribute("product");
            if (product != null) {
            %>
            <div class="col-md-6">
                <img src="<%= product.getProductImage() %>" alt="<%= product.getProductName() %>" class="img-fluid product-image">
            </div>
            <div class="col-md-6">
                <h3><%= product.getProductName() %></h3>
                <p><strong>Price:</strong> ₹<%= String.format("%.2f", product.getProductPrice()) %></p>
                <p><strong>Description:</strong> <%= product.getProductDescription() %></p>
                <p><strong>Category ID:</strong> <%= product.getCategoryID() %></p>
                <p><strong>Product ID:</strong> <%= product.getProductID() %></p>
                
                <form action="<%= request.getContextPath() %>/Product" method="post" style="display: inline;">
                    <a href="<%= request.getContextPath() %>/Product/<%= product.getProductID() %>" class="btn btn-edit">Edit</a>
                    <input type="hidden" name="ProductID" value="<%= product.getProductID() %>">
                    <button type="button" class="btn btn-delete" onclick="sendDeleteRequest(<%= product.getProductID() %>)">Delete</button>
                </form>
                
                <script>
                function sendDeleteRequest(ProductID) {
                    console.log(`Attempting to delete product with ID: ${ProductID}`);

                    fetch('<%= request.getContextPath() %>/Product', {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: new URLSearchParams({
                            'ProductID': ProductID
                        })
                    })
                    .then(response => {
                        console.log('Response received:', response);
                        if (response.ok) {
                            console.log('Product deleted successfully. Redirecting...');
                            window.location.href = '<%= request.getContextPath() %>/Product/all'; // Redirect to the product list or appropriate page
                        } else {
                            console.error('Failed to delete the product. Response status:', response.status);
                            alert('Failed to delete the product.');
                        }
                    })
                    .catch(error => {
                        console.error('Error occurred while deleting the product:', error);
                        alert('Error occurred while deleting the product.');
                    });
                }
                </script>
            <% 
            } else {
            %>
            <div class="col-12">
                <p>Product not found.</p>
            </div>
            <% 
            }
            %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
