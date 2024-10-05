<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f5f7;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #343a40;
            text-align: center;
            margin: 40px 0;
            font-size: 2.5em;
            font-weight: 600;
        }

        .container {
            max-width: 700px;
            margin: 0 auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            font-size: 1.1em;
            color: #495057;
        }

        .form-group input, .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ced4da;
            border-radius: 6px;
            font-size: 1em;
            color: #495057;
            box-sizing: border-box;
        }

        .form-group input[type="file"] {
            padding: 0;
        }

        .btn {
            display: inline-block;
            padding: 12px 24px;
            font-size: 1.1em;
            text-align: center;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
            font-weight: 600;
        }

        .btn-primary {
            background-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }

        .btn-secondary {
            background-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
        }
    </style>
</head>
<body>
    <h1>Add Product</h1>
    <div class="container">
        <form action="Product" method="post" enctype="multipart/form-data">
            <input type="hidden" name="ProductID" value="<%= request.getParameter("ProductID") %>">    

            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>
            </div>
            
            <div class="form-group">
                <label for="productPrice">Product Price (INR):</label>
                <input type="text" id="productPrice" name="productPrice" step="0.01" required>
            </div>

            <div class="form-group">
                <label for="productDescription">Product Description:</label>
                <textarea id="productDescription" name="productDescription" rows="4" required></textarea>
            </div>           

            <div class="form-group">
                <label for="productImages">Upload Product Images:</label>
                <input type="file" id="productImages" name="productImages" multiple required>
            </div>

            <button type="submit" class="btn btn-primary">Add Product</button>
            <a href="SellerDashboard.jsp" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
