<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dto.CategoryResponse" %>
<!DOCTYPE html>
<html>
<head>
    <title>Categories</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .categories {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        .category {
            display: flex;
            align-items: center;
            padding: 20px;
            background: #e9e9e9;
            border-radius: 10px;
            text-align: center;
            transition: transform 0.3s ease, background 0.3s ease;
        }
        .category:hover {
            transform: translateY(-5px);
            background: #d9d9d9;
        }
        .category img {
            width: 60px; /* Adjust size as needed */
            height: 60px; /* Adjust size as needed */
            border-radius: 50%;
            margin-right: 20px;
        }
        .category h2 {
            margin: 0;
            font-size: 1.5em;
            color: #333;
        }
        .category p {
            color: #666;
        }
    </style>
</head>
<body>     
    <div class="categories">
        <%
            List<CategoryResponse> categories = (List<CategoryResponse>) request.getAttribute("categories");
            
            if (categories != null && !categories.isEmpty()) {
                for (CategoryResponse category : categories) {
        %>
            <div class="category">
                <img src="<%= category.getImageUrl() %>" alt="<%= category.getCategoryName() %> Logo">
                <h2><%= category.getCategoryName() %></h2>
            </div>
        <%
                }
            } else {
        %>
            <p>No categories found.</p>
        <%
            }
        %>
    </div>
</body>
</html>
