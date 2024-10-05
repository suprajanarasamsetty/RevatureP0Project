<%@ page import="java.util.List" %>
<%@ page import="com.dto.ProductResponse" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <style>
        /* Inline CSS for the search results page */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        .product-table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            font-size: 16px;
            text-align: left;
        }

        .product-table th, .product-table td {
            border: 1px solid #ddd;
            padding: 12px;
        }

        .product-table th {
            background-color: #343a40;
            color: #ffffff;
        }

        .product-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .product-table tr:hover {
            background-color: #ddd;
        }

        .product-image {
            width: 100px; /* Adjust as needed */
            height: auto;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .no-results {
            text-align: center;
            font-size: 18px;
            color: #333;
        }

        .container {
            width: 90%;
            margin: 0 auto;
        }

        .nav-link {
            text-decoration: none;
            color: #007bff;
            padding: 10px;
            display: block;
        }

        .nav-link:hover {
            background-color: #e9ecef;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Results</h1>

        <%-- Retrieve the list of products from the request scope --%>
        <%
            List<ProductResponse> products = (List<ProductResponse>) request.getAttribute("products");
            if (products == null || products.isEmpty()) {
        %>
            <p class="no-results">No products found for your search query.</p>
        <%
            } else {
        %>
            <table class="product-table">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Seller ID</th>
                        <th>Category ID</th>
                        <th>Image</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Iterate over the list of products and display each --%>
                    <%
                        for (ProductResponse product : products) {
                    %>
                        <tr>
                            <td><%= product.getProductID() %></td>
                            <td><%= product.getProductName() %></td>
                            <td><%= String.format("%.2f", product.getProductPrice()) %></td>
                            <td><%= product.getSellerID() %></td>
                            <td><%= product.getCategoryID() %></td>
                            <td><img src="<%= product.getProductImage() %>" alt="Product Image" class="product-image"></td>
                            <td><%= product.getProductDescription() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            }
        %>

        <a href="index.jsp" class="nav-link">Back to Home</a> <!-- Link to navigate back to home or another page -->
    </div>
</body>
</html>
