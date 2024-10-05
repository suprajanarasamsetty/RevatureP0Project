<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.SellerResponse" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Seller Profile</h1>
        <table class="table table-striped">
            <tbody>
                <% 
                    SellerResponse seller = (SellerResponse) request.getAttribute("seller");
                    if (seller != null) {
                %>
                <tr>
                    <th>Seller ID</th>
                    <td><%= seller.getSellerID() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= seller.getSellerEmail() %></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><%= seller.getSellerName() %></td>
                </tr>
                <tr>
                    <th>Business Name</th>
                    <td><%= seller.getBusinessName() %></td>
                </tr>
                <tr>
                    <th>BusinessDetails</th>
                    <td><%= seller.getBusinessDetails() %></td>
                </tr>
                <% 
                    } else { 
                %>
                <tr>
                    <td colspan="2">Seller details not available.</td>
                </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
