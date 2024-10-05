package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dao.UserRegistrationDAOClass;
import com.dto.CartResponse;
import com.entity.Role;
import com.service.CartService;
import com.service.OrdersService;
import com.service.SellerService;

	@WebServlet("/login")
	public class LoginServlet extends HttpServlet {	 
		private OrdersService orderService;
		private CartService cartService;
		private SellerService sellerService;
		
		public void init() {
			System.out.println("init");
			this.orderService=new OrdersService();
			this.cartService=new CartService();
			this.sellerService=new SellerService();
		}
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Retrieve form parameters
	        String user_email = req.getParameter("user_email");
	        String user_password = req.getParameter("user_password");
	        String orderIdParam = req.getParameter("orderId");


	        // Simple validation
	        if (user_email == null || user_password == null || user_email.isEmpty() || user_password.isEmpty()) {
	            resp.sendRedirect("login.jsp?error=Both fields are required");
	            return;
	        }

	        // Create UserDAO instance and authenticate user
	        UserRegistrationDAOClass userDAO = new UserRegistrationDAOClass();
	        boolean isValidUser=userDAO.authenticateUser(user_email, user_password);
	        Role userRole = userDAO.authenticateRole(user_email, user_password);
	        Integer userId=userDAO.getUserId(user_email, user_password);
	        
	        if (isValidUser && userRole != null && userId != null) {
	        	
	        	Integer sellerId = null;
	            if (userRole == Role.SELLER) {
	                try {
	                    // Query the seller ID for the logged-in user
	                    sellerId = sellerService.getSellerIdByUserId(userId); // Add this method in your DAO
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                    resp.sendRedirect("login.jsp?error=Unable to fetch seller ID");
	                    return;
	                }
	            }
	            // User authenticated successfully
	            HttpSession session = req.getSession();
	            session.setAttribute("user_email", user_email); // Store email in the session
	            session.setAttribute("user_role", userRole);  // Store user role in the session
	            session.setAttribute("user_id", userId);
	            session.setAttribute("sellerId", sellerId);
	            session.setAttribute("seller_id", sellerId);
	            
	            
	            Integer orderId = fetchOrderIdForUser(userId);
	            session.setAttribute("orderId", orderId);
	            
	            try {
	                List<CartResponse> cartItems = cartService.getCartItemsByUserId(userId);
	                session.setAttribute("cartItems", cartItems);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                // Handle exception, maybe set an error attribute or log it
	            }
	            
	            	                     
	            // Redirect based on user role
	            if (userRole == Role.BUYER) {
	                resp.sendRedirect("BuyerDashboard.jsp?success=Successfully Logged in");
	            } else if (userRole == Role.SELLER) {
	                resp.sendRedirect("SellerDashboard.jsp?success=Successfully Logged in");
	            } else {
	                resp.sendRedirect("login.jsp?error=Unknown role");
	            }
	        } else {
	            // Authentication failed
	            resp.sendRedirect("login.jsp?error=Invalid email or password");
	        }
	    }
	    private Integer fetchOrderIdForUser(Integer userId) {
	        try {
	            return orderService.getOrderIdForUser(userId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null; // Handle exception and return null if there is an issue
	        }
	    }
}
