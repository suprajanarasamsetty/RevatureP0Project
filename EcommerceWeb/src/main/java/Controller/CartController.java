package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dto.CartRequest;
import com.dto.CartResponse;
import com.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Cart")
public class CartController extends HttpServlet{
	private CartService cartService;

    @Override
    public void init() {
        cartService = new CartService(); // Initialize CartService
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer userId = (Integer) session.getAttribute("user_id");

        List<CartResponse> cartItems;
        try {
            cartItems = cartService.getCartItemsByUserId(userId);
            req.setAttribute("cartItems", cartItems);
            req.getRequestDispatcher("/Cart.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving cart items.");
        }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	HttpSession session=req.getSession();
    	
    	int productId = Integer.parseInt(req.getParameter("ProductId"));
        System.out.println("ProductId received: " + productId);

        // Retrieve cart from session, or create a new cart if it doesn't exist
        List<CartRequest> cart = (List<CartRequest>) session.getAttribute("cartItems");
        if (cart == null) {
            cart = new ArrayList<>();
            System.out.println("Created new cart as it did not exist.");

        }

        // Check if the product is already in the cart
        boolean productExists=false;
        for (CartRequest item : cart) {
            if (item.getProductID()==productId) {
                // Update quantity if the product is already in the cart
                item.setQuantity(item.getQuantity() + 1);
                productExists = true;
                System.out.println("Updated quantity for productId: " + productId + " to " + item.getQuantity());
                break;
            }
        }

        if (!productExists) {
        	int CartID =Integer.parseInt(req.getParameter("CartID"));	
    		int user_id=Integer.parseInt(req.getParameter("user_id"));
    		int ProductID=Integer.parseInt("ProductID");
    		double TotalPrice=Integer.parseInt(req.getParameter("TotalPrice"));
    		int Quantity=Integer.parseInt(req.getParameter("Quantity"));
    		String DiscountCoupon=req.getParameter("DiscountCoupon");
    		
    		CartRequest cartRequest = new CartRequest(CartID, user_id, ProductID, TotalPrice, Quantity, DiscountCoupon);
   		
    		try {
    			boolean response=cartService.CreateCart(cartRequest);
    			resp.getWriter().println("Cart "+cartRequest.getCartID()+" Created Successfully");
        	
            // Create a new cart item (assuming default quantity of 1)
    			cartRequest.setProductID(productId);
    			cartRequest.setQuantity(1);
            // Retrieve product details and set total price (pseudo-code)
            // newItem.setTotalPrice(getProductPrice(productId));
            cart.add(cartRequest);
            System.out.println("Added new CartRequest to cart: " + cartRequest);

        }catch (Exception e) {
            resp.getWriter().println("Failed to create cart: " + e.getMessage());
        }

        // Save the updated cart back to the session
        session.setAttribute("cartItems", cart);
        System.out.println("Cart updated in session.");

        
        resp.setContentType("text/plain");
        
        resp.getWriter().write("Success");
        // Forward to cart.jsp to reflect the changes
        req.getRequestDispatcher("Cart.jsp").forward(req, resp);
    }
    }
}
