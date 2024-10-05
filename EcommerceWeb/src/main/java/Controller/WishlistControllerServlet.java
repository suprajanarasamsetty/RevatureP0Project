package Controller;

import java.io.IOException;
import java.util.List;

import com.service.WishlistService;
import com.dto.ProductResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Wishlist")
public class WishlistControllerServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    private WishlistService wishlistService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.wishlistService = new WishlistService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
            List<ProductResponse> wishlist = (List<ProductResponse>) wishlistService.getWishlistById(userId);
            request.setAttribute("wishlist", wishlist);
            request.getRequestDispatcher("Wishlist.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving wishlist");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String productIdParam = request.getParameter("productId");

        if (action == null || productIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action or Product ID is missing");
            return;
        }

        int productId = Integer.parseInt(productIdParam);

        try {
            if ("add".equals(action)) {
                wishlistService.addProductToWishlist(userId, productId);
            } else if ("remove".equals(action)) {
                wishlistService.removeProductFromWishlist(userId, productId);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                return;
            }

            response.sendRedirect("wishlist");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }
}

