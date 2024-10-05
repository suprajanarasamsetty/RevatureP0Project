<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Seller Registration Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 600px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-control {
            border-radius: 4px;
            box-shadow: none;
        }
        .btn-primary {
            border-radius: 4px;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .btn-container {
            margin-top: 20px;
            text-align: center;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Seller Registration</h2>

        <!-- Display success or error messages -->
        <%
            String successMessage = request.getParameter("success");
            String errorMessage = request.getParameter("error");

            if (successMessage != null) {
                out.println("<div class='alert alert-success'>" + successMessage + "</div>");
            }
            if (errorMessage != null) {
                out.println("<div class='alert alert-danger'>" + errorMessage + "</div>");
            }
        %>

        <form action="Seller" method="post">
            <div class="form-group">
                <label for="SellerName">Name</label>
                <input type="text" id="SellerName" name="SellerName" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="SellerEmail">Email</label>
                <input type="email" id="SellerEmail" name="SellerEmail" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="SellerPassword">Password</label>
                <input type="password" id="SellerPassword" name="SellerPassword" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="BusinessName">Business Name</label>
                <input type="text" id="BusinessName" name="BusinessName" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="BusinessDetails">Business Details</label>
                <textarea id="BusinessDetails" name="BusinessDetails" class="form-control" rows="4" required></textarea>
            </div>
            <div class="btn-container">
                <input type="submit" class="btn btn-primary" value="Register Seller" />
            </div>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
