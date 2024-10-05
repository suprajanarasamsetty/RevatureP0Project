package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.Exception.UserCreationException;
import com.dto.SellerResponse;
import com.dto.UserRegistrationRequest;
import com.dto.UserRegistrationResponse;
import com.entity.Role;
import com.service.SellerService;
import com.service.UserRegistrationService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Users/*")
public class BuyerRegistrationController extends HttpServlet{
	private UserRegistrationService userService;
	private SellerService sellerService;
	
	public void init() {
		System.out.println("init");
		this.userService=new UserRegistrationService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		System.out.println("Get method invoked");
		
		String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.equals("/Users/all")) {
            try {
            	List<SellerResponse> sellers=sellerService.getAllSellers();
            	
                List<UserRegistrationResponse> users = userService.getAllUsers();
                
                req.setAttribute("users", users);
                
                req.setAttribute("sellers", sellers);
                
                RequestDispatcher Sellerdispatcher = req.getRequestDispatcher("/SellersList.jsp");
                
                RequestDispatcher dispatcher = req.getRequestDispatcher("/UsersList.jsp");
                
                dispatcher.forward(req, resp);
                
                Sellerdispatcher.forward(req, resp);
                
            } catch (Exception e) {
                log("Error retrieving users", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving users.");
            }
        } else if (path.startsWith("/Users/")) {
            try {
                Long id = Long.parseLong(path.substring("/Users/".length()));
                UserRegistrationResponse user = userService.getUserById(id);
                SellerResponse seller=sellerService.getSellerById(id);
                
                if (user != null) {
                	
                	req.setAttribute("seller", seller);
                    req.setAttribute("user", user);

                    // Redirect to the appropriate profile page based on role
                    if (user.getRole() == Role.SELLER) {
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/SellerProfile.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/BuyerProfile.jsp");
                        dispatcher.forward(req, resp);
                    }
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
            } catch (Exception e) {
                log("Error retrieving user information", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving user information.");
            }
        }
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String user_first_name=req.getParameter("user_first_name");
		String user_last_name=req.getParameter("user_last_name");
		String user_email=req.getParameter("user_email");
		String user_password=req.getParameter("user_password");
        String user_phonenumberStr = req.getParameter("user_phonenumber");
		String user_address=req.getParameter("user_address");
		String roleStr=req.getParameter("role");
			
		if (user_first_name == null || user_last_name == null || user_email == null ||
				user_password == null || user_phonenumberStr == null || user_address == null ||  roleStr==null ||
						user_first_name.isEmpty() || user_last_name.isEmpty() || user_email.isEmpty() ||
						user_password.isEmpty()|| user_phonenumberStr.isEmpty() || user_address.isEmpty() || roleStr.isEmpty()) {
            req.setAttribute("error", "All fields are required");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
	        return;
	        }
		Long user_phonenumber;
        try {
            user_phonenumber = Long.parseLong(user_phonenumberStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid phone number format");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        Role role;
        try {
            role = Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("register.jsp?error=Invalid role selected");
            return;
        }
	        // Create user registration request object
	        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(user_first_name, user_last_name, user_email, user_password, user_phonenumber, user_address, role);
	        boolean isRegistered;
			try {
				isRegistered = userService.CreateUser(userRegistrationRequest);
				 if (isRegistered) {
			            resp.sendRedirect("login.jsp?success=Registration successful");
			        } else {
			        	req.setAttribute("error", "Registration failed");
		                req.getRequestDispatcher("register.jsp").forward(req, resp);			 
		            }
			} catch (SQLException | UserCreationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	       
	    }

}
