<%@ page import="java.util.List" %>
<%@ page import="com.dto.CartResponse" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
        /* General body styling */
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Header styling */
        h1 {
            text-align: center;
            color: #333;
            padding: 20px;
            background-color: #4CAF50;
            color: white;
            margin: 0;
        }

        /* Table styling */
        .cart-table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        .cart-table th, .cart-table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        .cart-table th {
            background-color: #4CAF50;
            color: white;
        }

        .cart-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .cart-table tr:hover {
            background-color: #ddd;
        }

        /* Link styling */
        a {
            display: block;
            text-align: center;
            margin: 20px auto;
            padding: 10px;
            width: 150px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Your Shopping Cart</h1>

    <%-- Retrieve the list of cart items from the request scope --%>
    <%
        List<CartResponse> cartItems = (List<CartResponse>) request.getAttribute("cartItems");
        if (cartItems == null || cartItems.isEmpty()) {
    %>
        <p>Your cart is empty.</p>
    <%
        } else {
    %>
        <table class="cart-table">
            <thead>
                <tr>
                    <th>Cart ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Discount Coupon</th>
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over the list of cart items and display each --%>
                <%
                    for (CartResponse item : cartItems) {
                %>
                    <tr>
                        <td><%= item.getCartID() %></td>
                        <td><%= item.getUser_id() %></td>
                        <td><%= item.getProductID() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td><%= String.format("%.2f", item.getTotalPrice()) %></td>
                        <td><%= item.getDiscountCoupon() %></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>

    <a href="BuyerDashboard.jsp">Back to Home</a> <!-- Link to navigate back to home or another page -->
</body>
</html>
