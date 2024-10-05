package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dao.SellerDAOClass;
import com.dao.UserRegistrationDAOClass;
import com.dto.SellerResponse;
import com.dto.UserRegistrationResponse;
import com.entity.Role;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	
    private SellerDAOClass sellerDAO;
    private UserRegistrationDAOClass userDAO;
    @Override
    public void init() {
    	 sellerDAO = new SellerDAOClass(); // Initialize the DAO
         userDAO = new UserRegistrationDAOClass(); // Initialize the DAO
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	HttpSession session = req.getSession(false); // Use false to avoid creating a new session if one does not exist
        if (session == null) {
            resp.sendRedirect("login.jsp?error=Session not found. Please log in again.");
            return;
        }

        // Retrieve user email from session
        String userEmail = (String) session.getAttribute("user_email");
        if (userEmail == null || userEmail.isEmpty()) {
            resp.sendRedirect("login.jsp?error=User email is missing. Please log in.");
            return;
        }
        // Retrieve user_id from the request parameter
        String userIdParam = req.getParameter("user_id");
        if (userIdParam == null || userIdParam.isEmpty()) {
            resp.sendRedirect("login.jsp?error=User ID is missing");
            return;
        }

        Integer userId;
        try {
            userId = Integer.parseInt(userIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect("login.jsp?error=Invalid User ID");
            return;
        }

        try {
            UserRegistrationResponse user = userDAO.getUserByEmail(userEmail);
            
            // Check if the user ID matches the one retrieved
            if (user.getUser_id() != userId) {
                resp.sendRedirect("login.jsp?error=User ID mismatch");
                return;
            }

            Role role = user.getRole(); // Assuming there's a getRole method
            
            // Get seller details if the user is a seller
            if (role.equals(Role.SELLER)) {
                String sellerIdParam = req.getParameter("sellerId");
                
                // Debugging output for sellerIdParam
                System.out.println("Received sellerIdParam: " + sellerIdParam);
                
                if (sellerIdParam == null || sellerIdParam.isEmpty()) {
                    System.out.println("Error: Seller ID is missing.");
                    resp.sendRedirect("error.jsp?message=Missing seller ID");
                    return;
                }

                Integer sellerId;
                try {
                    sellerId = Integer.parseInt(sellerIdParam);
                    // Debugging output for sellerId
                    System.out.println("Parsed sellerId: " + sellerId);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid seller ID format.");
                    resp.sendRedirect("error.jsp?message=Invalid seller ID");
                    return;
                }

                SellerResponse seller = sellerDAO.getSellerById(sellerId);
                if (seller != null) {
                    req.setAttribute("seller", seller);
                    req.getRequestDispatcher("SellerProfile.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("error.jsp?message=Seller not found");
                }
            } else if (role.equals(Role.BUYER)) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("BuyerProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("login.jsp?error=Unknown role");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
