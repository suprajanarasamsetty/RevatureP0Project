<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Special Offers & Updates</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Roboto', sans-serif;
        }
        .container {
            margin-top: 30px;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }
        .header h1 {
            font-size: 2rem;
        }
        .header p {
            font-size: 1.2rem;
        }
        .content {
            margin-top: 20px;
        }
        .content h2 {
            color: #007bff;
            margin-bottom: 15px;
        }
        .card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card-header {
            padding: 15px;
            border-radius: 8px 8px 0 0;
            font-size: 1.25rem;
            font-weight: bold;
            color: #fff;
        }
        .card-body {
            padding: 20px;
            background-color: #fff;
        }
        .card-body p {
            margin-bottom: 1rem;
        }
        .latest-deals {
            background-color: #28a745; /* Green */
        }
        .new-features {
            background-color: #ffc107; /* Yellow */
        }
        .newsletter-signup {
            background-color: #dc3545; /* Red */
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 4px;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        @media (max-width: 576px) {
            .header {
                padding: 15px;
            }
            .card-body {
                padding: 15px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Special Offers & Updates</h1>
            <p>Stay tuned for our special offers and updates. We have exciting deals and new features coming your way!</p>
        </div>

        <div class="content">
            <div class="card latest-deals">
                <div class="card-header">
                    Latest Deals
                </div>
                <div class="card-body">
                    <p>Check out our latest deals on a wide range of products! Save big on electronics, fashion, home goods, and more. Don't miss out!</p>
                    <p><a href="#" class="btn btn-primary">View Deals</a></p>
                </div>
            </div>

            <div class="card new-features">
                <div class="card-header">
                    New Features
                </div>
                <div class="card-body">
                    <p>We've introduced new features to enhance your shopping experience. Explore our updated interface, improved search functionality, and personalized recommendations.</p>
                    <p><a href="#" class="btn btn-primary">Explore Features</a></p>
                </div>
            </div>

            <div class="card newsletter-signup">
                <div class="card-header">
                    Newsletter Signup
                </div>
                <div class="card-body">
                    <p>Sign up for our newsletter to get the latest updates, special offers, and exclusive discounts delivered right to your inbox.</p>
                    <form action="#" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Subscribe</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
