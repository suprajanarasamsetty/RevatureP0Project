<%@ page import="java.util.List" %>
<%@ page import="com.dto.OrderItemResponse" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        /* Container for the content */
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }

        /* Header Styling */
        h1 {
            color: #0056b3;
            border-bottom: 2px solid #0056b3;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        thead {
            background-color: #0056b3;
            color: #fff;
        }

        th, td {
            padding: 10px;
            text-align: right;
        }

        th {
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #dcdcdc;
        }

        tfoot td {
            font-weight: bold;
            text-align: right;
            background-color: #e0e0e0;
        }

        /* Link Styling */
        a {
            color: #0056b3;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            table, th, td {
                display: block;
                width: 100%;
            }
            
            th, td {
                box-sizing: border-box;
                text-align: left;
            }

            td {
                padding: 10px;
                position: relative;
                border: 1px solid #ddd;
            }

            td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 10px;
                font-weight: bold;
                white-space: nowrap;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order Items</h1>

        <%-- Retrieve the list of order items from the request scope --%>
        <%
            List<OrderItemResponse> orderItems = (List<OrderItemResponse>) request.getAttribute("orderItems");
            if (orderItems == null || orderItems.isEmpty()) {
        %>
            <p>No items found for this order.</p>
        <%
            } else {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%-- Iterate over the list of order items and display each --%>
                    <%
                        double grandTotal = 0.0;
                        for (OrderItemResponse item : orderItems) {
                            double itemTotal = item.getQuantity() * item.getPrice();
                            grandTotal += itemTotal;
                    %>
                        <tr>
                            <td><%= item.getQuantity() %></td>
                            <td><%= String.format("%.2f", item.getPrice()) %></td>
                            <td><%= String.format("%.2f", itemTotal) %></td>
                        </tr>
                    <%
                        }
                    %>
                    <tfoot>
                        <tr>
                            <td colspan="2"><strong>Grand Total</strong></td>
                            <td><%= String.format("%.2f", grandTotal) %></td>
                        </tr>
                    </tfoot>
                </tbody>
            </table>
        <%
            }
        %>

        <a href="BuyerDashboard.jsp">Back to Home</a>
    </div>
</body>
</html>
