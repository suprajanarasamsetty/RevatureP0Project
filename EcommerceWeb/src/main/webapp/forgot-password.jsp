<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f06, #a2c2e1);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            max-width: 500px;
            width: 100%;
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
            text-align: center;
        }
        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: 600;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 500;
        }
        .form-group input {
            width: calc(100% - 20px);
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 16px;
            color: #333;
        }
        .form-group input:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0,123,255,0.5);
        }
        .form-group button {
            width: 100%;
            padding: 15px;
            background: #007bff;
            border: none;
            border-radius: 8px;
            color: #fff;
            font-size: 18px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .form-group button:hover {
            background: #0056b3;
        }
        .message {
            margin-top: 20px;
            font-size: 16px;
            color: #e74c3c;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Forgot Password</h1>
    <form action="reset-password" method="post">
        <div class="form-group">
            <label for="email">Enter your email address:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>

    <% 
        // Display message if any
        String message = (String) request.getAttribute("message");
        if (message != null && !message.isEmpty()) {
    %>
        <div class="message"><%= message %></div>
    <% 
        }
    %>
</div>

</body>
</html>
