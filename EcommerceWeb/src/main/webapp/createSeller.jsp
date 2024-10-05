<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seller Create Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style>
  body {
    background-color: #f8f9fa;
  }
  .container {
    max-width: 800px;
    margin-top: 50px;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  }
  .form-label {
    font-weight: bold;
    color: #333;
  }
  .btn-primary {
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    padding: 10px 20px;
  }
  .btn-primary:hover {
    background-color: #0056b3;
    cursor: pointer;
  }
  .form-control {
    border-radius: 4px;
    border: 1px solid #ced4da;
    box-shadow: none;
  }
  .form-control:focus {
    border-color: #007bff;
    box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
  }
  .btn {
    font-size: 16px;
  }
</style>
</head>
<body>
<div class="container">
  <form class="row g-3" action="Seller/" method="POST">
    <div class="col-md-6">
      <label for="SellerName" class="form-label">Name</label>
      <input type="text" class="form-control" id="SellerName" name="SellerName" required>
    </div>
    <div class="col-md-6">
      <label for="SellerEmail" class="form-label">Email</label>
      <input type="email" class="form-control" id="SellerEmail" name="SellerEmail" required>
    </div>
    <div class="col-12">
      <label for="SellerPassword" class="form-label">Password</label>
      <input type="password" class="form-control" id="SellerPassword" name="SellerPassword" required>
    </div>
    <div class="col-12">
      <label for="BusinessName" class="form-label">Business Name</label>
      <input type="text" class="form-control" id="BusinessName" name="BusinessName" required>
    </div>
    <div class="col-md-6">
      <label for="BusinessDetails" class="form-label">Business Details</label>
      <input type="text" class="form-control" id="BusinessDetails" name="BusinessDetails">
    </div>
    <div class="col-12">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
