package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.dto.OrdersResponse;
import com.entity.Role;
import com.service.OrdersService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BuyerOrders")
public class BuyerOrders extends HttpServlet{
	
	private OrdersService orderService;

    @Override
    public void init() {
        // Initialize OrdersService
        this.orderService = new OrdersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
	    PrintWriter out = resp.getWriter(); // For debugging output
        
        HttpSession session = req.getSession(false);
        if (session == null) {
            out.println("No session found. Redirecting to login.");
            resp.sendRedirect("login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("user_id");
        Role userRole = (Role) session.getAttribute("user_role");

        out.println("Session found. Debugging session attributes:");
        out.println("userId: " + userId);
        out.println("userRole: " + userRole);

        if (userId == null) {
            out.println("user_id is null.");
        }

        if (userRole == null) {
            out.println("User role is null.");
            resp.sendRedirect("login.jsp");
            return;
        }

        if (userRole.equals(Role.BUYER)) {
            try {
                List<OrdersResponse> orders = orderService.getOrdersByUserId(userId);
                req.setAttribute("orders", orders);
                req.setAttribute("user_id", userId);
                req.getRequestDispatcher("/buyerOrders.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error");
            }
            
        } else if (userRole.equals(Role.SELLER)) {
            // Debugging output
            out.println("User is a SELLER. Redirecting to SellerOrders.");

            Integer sellerId = (Integer) session.getAttribute("Seller_ID");
            if (sellerId != null) {
                // Debugging output
                out.println("Seller ID found: " + sellerId);

                // Redirect to SellerOrdersServlet with SellerID parameter
                resp.sendRedirect("SellerOrders.jsp");
            } else {
                // Debugging output
                out.println("Seller ID is null. Redirecting to login.");
                resp.sendRedirect("login.jsp");
            }
        } else {
            // Debugging output
            out.println("User role is not recognized. Redirecting to login.");
            resp.sendRedirect("login.jsp");
        }
    }

}
