package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


import com.Exception.ProductCreationException;
import com.Exception.ProductNotFoundException;
import com.dao.SellerDAOClass;
import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Product/*")
public class ProductController extends HttpServlet{
	
	private ProductService productService;
	
	public void init() {
		System.out.println("init");
		this.productService=new ProductService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
	    System.out.println("get method invoked");
	
		resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();         
   
	    String action = "";
	    try {
	        String path = req.getRequestURI();
	        System.out.println("Request Path: " + path);

	        // Extract action from the path
	        String basePath = "/EcommerceWeb/Product"; // Adjust according to your context path
	        if (path.startsWith(basePath)) {
	            String subPath = path.substring(basePath.length());
	            action = subPath.isEmpty() ? "" : subPath.substring(1); // Remove leading '/'
	        }
	        
	        System.out.println("Extracted Action: " + action);
	    } catch (StringIndexOutOfBoundsException e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request format");
	        return;
	    }

	    // Handle actions based on extracted action
	    if ("all".equals(action)) {
	        try {
	            List<ProductResponse> products = productService.getAllProducts();
	            req.setAttribute("products", products);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/viewProducts.jsp");
	            dispatcher.forward(req, resp);
	            return;
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching products");
	        }
	    } else {
	        try {
	            if (action == null || action.isEmpty()) {
	                throw new NumberFormatException("Action is null or empty");
	            }

	            int ProductID = Integer.parseInt(action); // Convert action to product ID
	            System.out.println("Fetching product details for ID: " + action);
	            ProductResponse product = productService.getProductById(ProductID);
	            if (product == null) {
	                System.out.println("Product not found for ID: " + ProductID);
	                resp.sendRedirect("error.jsp?message=Product not found");
	                return;
	            }
	            
	            req.setAttribute("product", product);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/viewProductDetails.jsp");
	            dispatcher.forward(req, resp);
	            System.out.println("Product details forwarded to viewProductDetails.jsp");
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID format");
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching product details");
	        }
	    }
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
        String productIdStr = req.getParameter("ProductID");
		String productName = req.getParameter("ProductName");
	    String productPriceParam = req.getParameter("ProductPrice");
	    String productImage = req.getParameter("ProductImage");
	    String productDescription = req.getParameter("ProductDescription");
	    String categoryIdParam = req.getParameter("CategoryId");
	    String userId=req.getParameter("user_id");
	    
	    System.out.println("Received Parameters:");
	    System.out.println("ProductID: " + productIdStr);
	    System.out.println("ProductName: " + productName);
	    System.out.println("ProductPrice: " + productPriceParam);
	    System.out.println("ProductImage: " + productImage);
	    System.out.println("ProductDescription: " + productDescription);
	    System.out.println("CategoryId: " + categoryIdParam);
	    System.out.println("user_id: " + userId);

	    // Validate parameters
	    if (productIdStr==null|| productName == null || productName.isEmpty() ||
	        productPriceParam == null || productPriceParam.isEmpty() ||
	        productImage == null || productImage.isEmpty() ||
	        productDescription == null || productDescription.isEmpty()) {
	        resp.sendRedirect("AddProduct.jsp?error=All fields are required");
	        return;
	    }
	    
        int ProductID;
	    double productPrice;
	    int categoryId;
	    int user_id;

	    // Parse numeric values
	    try {
	    	ProductID=Integer.parseInt(productIdStr.trim());
	        productPrice = Double.parseDouble(productPriceParam);
	        categoryId = Integer.parseInt(categoryIdParam);
	        user_id=Integer.parseInt(userId);
	    } catch (NumberFormatException e) {
	        resp.sendRedirect("AddProduct.jsp?error=Invalid number format");
	        return;
	    }
	    
	    HttpSession session = req.getSession();
	    Integer sellerID = (Integer) session.getAttribute("sellerID"); // Ensure "sellerID" is the session attribute name

	    if (sellerID == null) {
	        resp.sendRedirect("AddProduct.jsp?error=No Seller ID found in session");
	        return;
	    }

	    // Check if SellerID exists
	    try {
	        SellerDAOClass sellerDAO = new SellerDAOClass();
	        boolean sellerExists = sellerDAO.doesSellerExist(sellerID);
	        if (!sellerExists) {
	            resp.sendRedirect("AddProduct.jsp?error=Invalid SellerID");
	            return;
	        }

	        // Create ProductRequest object
	        ProductRequest productRequest = new ProductRequest(productName, productPrice, sellerID, categoryId, productImage, productDescription, user_id);

	        // Process the request
	        boolean response = productService.createProduct(productRequest);

	        if (response) {
	            resp.sendRedirect("AddProduct.jsp?message=Product created successfully"); // Redirect to success page
	        } else {
	            resp.sendRedirect("AddProduct.jsp?error=Product creation failed"); // Redirect to error page
	        }
		} catch (SQLException | ProductCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
}
   
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		try {
	        // Parse parameters
	        int ProductID = Integer.parseInt(req.getParameter("ProductID"));
	        String ProductName = req.getParameter("ProductName");
	        double ProductPrice = Double.parseDouble(req.getParameter("ProductPrice"));
	        String ProductImage = req.getParameter("ProductImage");
	        String ProductDescription = req.getParameter("ProductDescription");
	        int CategoryID = Integer.parseInt(req.getParameter("CategoryID"));
	        int SellerID = Integer.parseInt(req.getParameter("SellerID"));
	        int userId = Integer.parseInt(req.getParameter("user_id"));

	        // Create ProductResponse object
	        ProductResponse productResponse = new ProductResponse(ProductID, ProductName, ProductPrice, SellerID, CategoryID, ProductImage, ProductDescription, userId);
	        
	        // Update product
	        boolean success = productService.UpdateProduct(productResponse);

	        // Redirect based on the result
	        if (success) {
	            resp.sendRedirect(req.getContextPath() + "/Product/" + ProductID); // Redirect to the updated product details page
	        } else {
	            resp.sendRedirect("UpdateProduct.jsp?productId=" + ProductID + "&error=Update failed");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        resp.sendRedirect("UpdateProduct.jsp?ProductID=" + req.getParameter("ProductID") + "&error=Invalid input");
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendRedirect("UpdateProduct.jsp?ProductID=" + req.getParameter("ProductID") + "&error=Error occurred");
	    }
	}
		
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
			
			try {
	            String productIdParam = req.getParameter("ProductID");
	            if (productIdParam == null || productIdParam.isEmpty()) {
	                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                resp.getWriter().write("Product ID is required.");
	                return;
	            }

	            Integer productId = Integer.parseInt(productIdParam);
	            System.out.println("parsed Product ID "+productId);
				
	            ProductService productService = new ProductService();
	            boolean success = productService.DeleteProductByID(productId);
	            
				if (success) {
	                resp.setStatus(HttpServletResponse.SC_OK);
	                resp.getWriter().write("Product deleted successfully.");
	            } else {
	                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                resp.getWriter().write("Failed to delete product.");
	            }
	        } catch (NumberFormatException e) {
	            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            resp.getWriter().write("Invalid Product ID format.");
	        } catch (Exception e) {
	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            resp.getWriter().write("An error occurred while deleting the product.");
	        }
		}
		
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
			super.destroy();
			
			System.out.println("destroyed");
		}

	
}
