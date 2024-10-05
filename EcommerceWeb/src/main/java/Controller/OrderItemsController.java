package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dto.OrderItemResponse;
import com.service.OrderItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderItems")
public class OrderItemsController extends HttpServlet{
	
	public OrderItemService orderItemsService;
	
	public void init() {
		System.out.println("Get method invoked");
		this.orderItemsService=new OrderItemService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		HttpSession session = req.getSession();

	    // Retrieve orderId from request parameter or session attribute
	    String orderIdParam = req.getParameter("orderId");
	    if (orderIdParam == null) {
	        orderIdParam = (String) session.getAttribute("orderId");
	    }
        System.out.println("Session ID: " + session.getId());
        System.out.println("Session orderId attribute: " + session.getAttribute("orderId"));
        System.out.println("Retrieved orderIdParam: " + orderIdParam);


        if (orderIdParam == null || orderIdParam.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order ID is required.");
            return;
        }

        Integer orderId;
        try {
            orderId = Integer.parseInt(orderIdParam);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Order ID format.");
            return;
        }

        System.out.println("Parsed orderId: " + orderId);

        List<OrderItemResponse> orderItems;
        try {
            orderItems = orderItemsService.getOrderItemsByOrderId(orderId);
            req.setAttribute("orderItems", orderItems);
            req.getRequestDispatcher("/orderItems.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving order items.");
        }
    }

//    @Override
//    public void destroy() {
//        super.destroy();
//        System.out.println("OrderItemsController destroyed.");
//    }
}
