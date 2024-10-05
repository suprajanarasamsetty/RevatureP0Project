<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
        body {
            background: linear-gradient(135deg, #6f42c1, #e83e8c);
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            font-family: 'Roboto', sans-serif;
        }
        .container {
            max-width: 400px;
            width: 100%;
            padding: 40px;
            background-color: #ffffff;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }
        h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 30px;
            font-weight: 600;
        }
        .alert {
            margin-bottom: 20px;
        }
        .btn-container {
            margin-top: 20px;
            text-align: center;
        }
        .btn-container a {
            margin: 0 10px;
        }
        .form-group label {
            font-weight: 500;
            color: #555;
        }
        .form-group input {
            border-radius: 8px;
            box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.2s ease-in-out;
        }
        .form-group input:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
            border-color: #007bff;
            outline: none;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            padding: 12px;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            border-radius: 8px;
            padding: 12px;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .btn-link {
            color: #007bff;
            font-size: 16px;
            text-decoration: none;
        }
        .btn-link:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
    <script>
        function showSuccessAlert() {
            const urlParams = new URLSearchParams(window.location.search);
            const successMessage = urlParams.get('success');
            if (successMessage) {
                alert(successMessage);
            }
        }

        // Run the alert function when the page loads
        window.onload = showSuccessAlert;
    </script>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Login</h1>
        
        <% 
            // Retrieve and display the error message from query parameters
            String errorMessage = request.getParameter("error");
            if (errorMessage != null && !errorMessage.isEmpty()) {
                out.println("<div class='alert alert-danger' role='alert'>" + errorMessage + "</div>");
            }
        %>

        <form action="login" method="post">
            <input type="hidden" name="login" value="login" />

            <div class="form-group">
                <label for="user_email">Email</label>
                <input type="email" id="user_email" name="user_email" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="user_password">Password</label>
                <input type="password" id="user_password" name="user_password" class="form-control" required />
            </div>

            <button type="submit" class="btn btn-primary btn-block">Login</button>

            <div class="btn-container">
                <a href="register.jsp" class="btn btn-secondary">Create Account</a>
                <a href="forgot-password.jsp" class="btn btn-link">Forgot Password?</a>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
