package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.Exception.SellerCreateException;
import com.dto.SellerRequest;
import com.dto.SellerResponse;
import com.service.SellerService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Seller/*")
public class SellerController extends HttpServlet{
	
	private SellerService sellerService;
	
	public void init() {
		System.out.println("init");
		this.sellerService=new SellerService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.equals("/Seller/all")) {
            try {
                List<SellerResponse> sellers = sellerService.getAllSellers();
                
                out.println("<h1>Seller Controller</h1>");
                for (SellerResponse seller : sellers) {
                    out.printf("<p>Seller ID: %d</p>", seller.getSellerID());
                    out.printf("<p>Seller Name: %s</p>", seller.getSellerName());
                    out.printf("<p>Seller Email: %s</p>", seller.getSellerEmail());
                    out.printf("<p>Seller Password: %s</p>", seller.getSellerPassword());
                    out.printf("<p>Business Name: %s</p>", seller.getBusinessName());
                    out.printf("<p>Business Details: %s</p>", seller.getBusinessDetails());
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Long id = Long.parseLong(path.substring("/Seller/".length()));
            
            try {
                SellerResponse seller = sellerService.getSellerById(id);
                
                out.println("<h1>Seller Controller</h1>");
                out.printf("<p>Seller ID: %d</p>", seller.getSellerID());
                out.printf("<p>Seller Name: %s</p>", seller.getSellerName());
                out.printf("<p>Seller Email: %s</p>", seller.getSellerEmail());
                out.printf("<p>Seller Password: %s</p>", seller.getSellerPassword());
                out.printf("<p>Business Name: %s</p>", seller.getBusinessName());
                out.printf("<p>Business Details: %s</p>", seller.getBusinessDetails());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String SellerName=req.getParameter("SellerName");
		String SellerEmail=req.getParameter("SellerEmail");
		String SellerPassword=req.getParameter("SellerPassword");
		String BusinessName=req.getParameter("BusinessName");
		String BusinessDetail=req.getParameter("BusinessDetails");
		
		System.out.println("Received parameters:");
	    System.out.println("SellerName: " + SellerName);
	    System.out.println("SellerEmail: " + SellerEmail);
	    System.out.println("SellerPassword: " + SellerPassword);
	    System.out.println("BusinessName: " + BusinessName);
	    System.out.println("BusinessDetails: " + BusinessDetail);

	    Integer userId;
	    try {
	        userId = Integer.parseInt(req.getParameter("user_id"));
	        System.out.println("Parsed user_id: " + userId);
	    } catch (NumberFormatException e) {
	        System.err.println("Invalid User ID format: " + e.getMessage());
	        req.setAttribute("error", "Invalid User ID format.");
	        RequestDispatcher dispatcher = req.getRequestDispatcher("SellerRegistration.jsp");
	        dispatcher.forward(req, resp);
	        return;
	    }

	    SellerRequest seller = new SellerRequest(0, SellerName, SellerEmail, SellerPassword, BusinessName, BusinessDetail, userId);
	    System.out.println("Created SellerRequest object: " + seller);

	    try {
	        boolean response = sellerService.CreateSeller(seller);
	        System.out.println("CreateSeller response: " + response);

	        if (response) {
	            System.out.println("Seller created successfully");
	            // Redirect with success message
	            resp.sendRedirect("SellerRegistration.jsp?success=Seller created successfully");
	        } else {
	            System.out.println("Seller creation failed");
	            // Redirect with failure message
	            resp.sendRedirect("SellerRegistration.jsp?error=Seller creation failed");
	        }
	    } catch (SQLException e) {
	        System.err.println("Database error occurred: " + e.getMessage());
	        e.printStackTrace();
	        // Redirect with error message
	        resp.sendRedirect("SellerRegistration.jsp?error=Database error occurred");
	    } catch (SellerCreateException e) {
	        System.err.println("Error creating seller: " + e.getMessage());
	        e.printStackTrace();
	        // Redirect with error message
	        resp.sendRedirect("SellerRegistration.jsp?error=Error creating seller");
	    }
	}
}
