<%@ page import="com.dto.ProductResponse" %>
<%@ page import="com.service.ProductService" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>

<%
    String productIdParam = request.getParameter("productId");
	System.out.println("Received productIdParam: " + productIdParam); // Debugging statement

    if (productIdParam == null || productIdParam.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required.");
        return;
    }

    int productId = Integer.parseInt(productIdParam);
    System.out.println("Parsed productId: " + productId); // Debugging statement

    ProductService productService = new ProductService();
    ProductResponse product = productService.getProductById(productId);

    if (product == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
        return;
    }
    System.out.println("Product details: " + product.getProductName() + ", " + product.getProductDescription() + ", " + product.getProductPrice()); // Debugging statement

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
    function confirmDelete() {
        console.log('Product ID:', productId); // Debugging statement
        const productId = document.getElementById('productId').value;

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
                console.log('Response status:', response.status); // Debugging statement
                if (!response.ok) {
                    return response.text().then(text => Promise.reject(text));
                }
                return response.text();
            })
            .then(result => {
                console.log('Response text:', result); // Debugging statement
                alert(result);
                window.location.href = '<%= request.getContextPath() %>/ManageProducts.jsp';
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Failed to delete product: ' + error);
            });
        }
    }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Delete Product</h2>
    <p>Are you sure you want to delete the following product?</p>
    <div class="product-info">
        <h4><%= product.getProductName() %></h4>
        <p><strong>Description:</strong> <%= product.getProductDescription() %></p>
        <p><strong>Price:</strong> &#8377;<%= product.getProductPrice() %></p>
    </div>
    <input type="hidden" id="productId" value="<%= productId %>">
    <button class="btn btn-danger" onclick="confirmDelete()">Delete</button>
    <a href="<%= request.getContextPath() %>/ManageProducts.jsp" class="btn btn-secondary">Cancel</a>
</div>
</body>
</html>