<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration Page</title>
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
            max-width: 500px;
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
        .optional {
            display: none;
        }
    </style>
    <script>
        function toggleFields() {
            var role = document.getElementById('role').value;
            var optionalFields = document.querySelectorAll('.optional');
            
            if (role === 'SELLER') {
                optionalFields.forEach(function(field) {
                    field.style.display = 'block';
                });
            } else {
                optionalFields.forEach(function(field) {
                    field.style.display = 'none';
                });
            }
        }

        function handleSubmit(event) {
            var role = document.getElementById('role').value;
            if (role === 'SELLER') {
                event.preventDefault(); // Prevent the default form submission
                document.getElementById('registrationForm').action = 'SellerRegistration.jsp'; // Redirect to seller registration page
                document.getElementById('registrationForm').submit(); // Submit the form
            }
        }

        function handleRoleChange(event) {
            var role = event.target.value;
            if (role === 'SELLER') {
                handleSubmit(event);
            } else {
                toggleFields();
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 class="text-center">User Registration</h2>
        <form id="registrationForm" action="Users" method="post">
            <div class="form-group">
                <label for="user_first_name">First Name</label>
                <input type="text" id="user_first_name" name="user_first_name" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="user_last_name">Last Name</label>
                <input type="text" id="user_last_name" name="user_last_name" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="user_email">Email</label>
                <input type="email" id="user_email" name="user_email" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="user_password">Password</label>
                <input type="password" id="user_password" name="user_password" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="user_phonenumber">Phone Number</label>
                <input type="text" id="user_phonenumber" name="user_phonenumber" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="user_address">Address</label>
                <input type="text" id="user_address" name="user_address" class="form-control" required />
            </div>
            <div class="form-group">
                <label for="role">Role</label>
                <select id="role" name="role" class="form-control" onchange="handleRoleChange(event)" required>
                    <option value="" disabled selected>Select your role</option>
                    <option value="BUYER">BUYER</option>
                    <option value="SELLER">SELLER</option>
                </select>
            </div>
            <div class="btn-container">
                <input type="submit" class="btn btn-primary" value="Register" />
            </div>
        </form>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
