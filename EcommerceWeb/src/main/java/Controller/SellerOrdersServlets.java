package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dto.OrdersResponse;
import com.service.OrdersService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sellerOrders")
public class SellerOrdersServlets extends HttpServlet{
	
	private OrdersService orderService;

    @Override
    public void init() {
        this.orderService = new OrdersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String sellerIdParam = req.getParameter("SellerID");
        if (sellerIdParam == null) {
            resp.sendRedirect("login.jsp?error=Missing SellerID");
            return;
        }

        Integer sellerId;
        try {
            sellerId = Integer.parseInt(sellerIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect("login.jsp?error=Invalid SellerID");
            return;
        }

        try {
            List<OrdersResponse> orders = orderService.getOrdersBySellerId(sellerId);
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("SellerOrders.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

}
