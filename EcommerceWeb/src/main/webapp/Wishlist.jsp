<%@ page import="java.util.List" %>
<%@ page import="com.dto.ProductResponse" %>
<%@ page import="com.service.WishlistService" %>

<%
Integer userId = (Integer) session.getAttribute("user_id");

if (userId == null) {
    // Handle the case where user is not logged in
    response.sendRedirect("login.jsp");
    return;
}

List<ProductResponse> wishlist = null;
try {
    WishlistService wishlistService = new WishlistService();
} catch (Exception e) {
    e.printStackTrace();
    out.println("<p>Error retrieving wishlist: " + e.getMessage() + "</p>");
    return; // Prevent further processing in case of an error
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Wishlist</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Your Wishlist</h1>
        <% 
            if (wishlist != null && !wishlist.isEmpty()) {
                for (ProductResponse product : wishlist) { 
        %>
            <div class="card mb-3">
                <img src="<%= product.getProductImage() %>" class="card-img-top" alt="<%= product.getProductName() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getProductName() %></h5>
                    <p class="card-text"><%= product.getProductDescription() %></p>
                    <p class="card-text"><strong>Price:</strong> &#8377;<%= product.getProductPrice() %></p>
                    <!-- Actions -->
                    <a href="ProductDetails.jsp?productId=<%= product.getProductID() %>" class="btn btn-primary">View</a>
                    <a href="UpdateProduct.jsp?productId=<%= product.getProductID() %>" class="btn btn-secondary">Edit</a>
                    <form method="post" action="Wishlist">
                        <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                        <input type="hidden" name="action" value="remove">
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </div>
            </div>
        <% 
                }
            } else {
        %>
            <p>Your wishlist is empty.</p>
        <% 
            }
        %>
    </div>
</body>
</html>