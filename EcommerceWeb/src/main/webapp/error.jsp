<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 50px;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            padding: 15px;
            display: inline-block;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-message">
            <h2>Error</h2>
            <p>
                <% 
                    String errorMessage = (String) session.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        out.println(errorMessage);
                        session.removeAttribute("errorMessage"); // Remove the message after displaying it
                    } else {
                        out.println("An unknown error occurred. Please try again later.");
                    }
                %>
            </p>
            <p><a href="login.jsp">Go back to Login</a></p>
        </div>
    </div>
</body>
</html>
