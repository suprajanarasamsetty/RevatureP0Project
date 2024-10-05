<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.ProductResponse" %>
<%@ page import="com.service.ProductService" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .container {
            margin-top: 30px;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>Update Product Details</h2>
        
        <!-- Display error or success messages -->
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                <strong>Error:</strong> ${param.error}
            </div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="alert alert-success">
                <strong>Success:</strong> ${param.message}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/Product" method="post">
            <input type="hidden" name="ProductID" value="${param.ProductID}">

            <!-- Product Name -->
            <div class="form-group">
                <label for="ProductName">Product Name:</label>
                <input type="text" id="ProductName" name="ProductName" class="form-control" value="${product.ProductName}" required>
            </div>

            <!-- Product Price -->
            <div class="form-group">
                <label for="ProductPrice">Product Price:</label>
                <input type="number" id="ProductPrice" name="ProductPrice" class="form-control" step="0.01" value="${product.ProductPrice}" required>
            </div>

            <!-- Product Image URL -->
            <div class="form-group">
                <label for="ProductImage">Product Image URL:</label>
                <input type="text" id="ProductImage" name="ProductImage" class="form-control" value="${product.ProductImage}" required>
            </div>

            <!-- Product Description -->
            <div class="form-group">
                <label for="ProductDescription">Product Description:</label>
                <textarea id="ProductDescription" name="ProductDescription" class="form-control" rows="4" required>${product.ProductDescription}</textarea>
            </div>

            <!-- Category ID -->
            <div class="form-group">
                <label for="CategoryID">Category ID:</label>
                <input type="number" id="CategoryID" name="CategoryID" class="form-control" value="${product.CategoryID}" required>
            </div>

            <!-- Seller ID -->
            <div class="form-group">
                <label for="SellerID">Seller ID:</label>
                <input type="number" id="SellerID" name="SellerID" class="form-control" value="${product.SellerID}" required>
            </div>

            <!-- User ID -->
            <div class="form-group">
                <label for="user_id">User ID:</label>
                <input type="number" id="user_id" name="user_id" class="form-control" value="${product.user_id}" required>
            </div>

            <!-- Submit and Cancel Buttons -->
            <button type="submit" class="btn btn-primary">Update Product</button>
            <a href="${pageContext.request.contextPath}/Product/${param.ProductId}" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
