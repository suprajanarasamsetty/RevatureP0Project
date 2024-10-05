package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.Exception.OrderCreateException;
import com.dto.OrdersRequest;
import com.dto.OrdersResponse;
import com.service.OrdersService;
import com.utils.ConnectionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Orders/*")
public class OrdersController extends HttpServlet{
	
	private OrdersService orderService;
	
	public void init() {
		System.out.println("init");
		this.orderService=new OrdersService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		resp.setContentType("text/html");
		
		PrintWriter out=resp.getWriter();
		
		System.out.println("Get method invoked");
		
		System.out.println(req.getRequestURI().substring("EcommerceWeb/Orders".length()+1));
		
		if(req.getRequestURI().substring("/EcommerceWeb/Orders/".length()+1).equals("all")) {
			try {
				List<OrdersResponse> orders=orderService.getAllOrders();
				
				out.println("<H1> Orders Controller </H1>");
				
				for(OrdersResponse order:orders) {
					out.printf("<p>Order ID: %d</p>", order.getOrder_id());
                    out.printf("<p>User ID: %d</p>", order.getUser_id());
                    out.printf("<p>Shipping Address: %s</p>", order.getShippingAddress());
                    out.printf("<p>Billing Address: %s</p>", order.getBillingAddress());
                    out.printf("<p>Order Date: %s</p>", order.getOrder_Date());
                    out.printf("<p>Order Status: %s</p>", order.getOrder_Status());
                    out.printf("<p>ProductID: %d</p>", order.getProductID());

					
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Long id=Long.parseLong(req.getRequestURI().substring("/EcommerceWeb/Orders/".length()));
			
			try {
				
				OrdersResponse Order=orderService.getOrderById(id);
				
				out.println("<H1> Orders Controller<H1>");
				
					out.printf("<p>Order ID: %d</p>", Order.getOrder_id());
                    out.printf("<p>User ID: %d</p>", Order.getUser_id());
                    out.printf("<p>Shipping Address: %s</p>", Order.getShippingAddress());
                    out.printf("<p>Billing Address: %s</p>", Order.getBillingAddress());
                    out.printf("<p>Order Date: %s</p>", Order.getOrder_Date());
                    out.printf("<p>Order Status: %s</p>", Order.getOrder_Status());
                    out.printf("<p>ProductID: %d</p>", Order.getProductID());
					
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		Integer sellerID = null;
        String sellerIDParam = req.getParameter("SellerID");

        if (sellerIDParam != null && !sellerIDParam.trim().isEmpty()) {
            try {
                sellerID = Integer.parseInt(sellerIDParam);
            } catch (NumberFormatException e) {
                sellerID = null; // Handle invalid number format
            }
        }

        if (sellerID == null) {
            resp.sendRedirect("login.jsp"); // Redirect to login if sellerID is not valid
            return;
        }

        String queryOrders = "SELECT o.Order_id, o.ShippingAddress, o.BillingAddress, o.Order_Date, o.Order_Status "
                + "FROM Orders o "
                + "JOIN Product p ON o.ProductID = p.ProductID "
                + "WHERE p.SellerID = ?";
        
        String querySeller = "SELECT user_first_name, user_last_name "
                + "FROM user_registration "
                + "WHERE user_id = ?";

        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmtOrders = con.prepareStatement(queryOrders);
             PreparedStatement stmtSeller = con.prepareStatement(querySeller)) {

            // Set the sellerID for the orders query
            stmtOrders.setInt(1, sellerID);
            ResultSet rs = stmtOrders.executeQuery();
            
            List<OrdersResponse> orderList = new ArrayList<>();
            Timestamp timestamp = rs.getTimestamp("Order_Date");
			LocalDateTime orderDate = timestamp != null ? timestamp.toLocalDateTime() : null;
            while (rs.next()) {
                OrdersResponse order = new OrdersResponse();
                order.setOrder_id(rs.getInt("Order_id"));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setBillingAddress(rs.getString("BillingAddress"));
                order.setOrder_Date(orderDate);             
                
                order.setOrder_Status(rs.getString("Order_Status"));
                order.setProductID(rs.getInt("ProductID"));
                
                orderList.add(order);
            }
            req.setAttribute("orderDetails", orderList);

            // Set the sellerID for the seller query
            stmtSeller.setInt(1, sellerID);
            ResultSet rsSeller = stmtSeller.executeQuery();
            if (rsSeller.next()) {
                String sellerFirstName = rsSeller.getString("user_first_name");
                String sellerLastName = rsSeller.getString("user_last_name");
                req.setAttribute("sellerName", sellerFirstName + " " + sellerLastName);
            } else {
                req.setAttribute("sellerName", "Unknown Seller");
            }

            req.getRequestDispatcher("sellerOrders.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }


	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int Order_id =Integer.parseInt(req.getParameter("Order_id"));	
		int user_id=Integer.parseInt(req.getParameter("user_id"));
		String ShippingAddress=req.getParameter("ShippingAddress");
		String BillingAddress=req.getParameter("BillingAddress");
		int ProductID=Integer.parseInt("ProductID");
		
//		String orderDateStr = req.getParameter("Order_Date");
//        LocalDateTime orderDate = LocalDateTime.parse(orderDateStr);
         
		String Order_Status=req.getParameter("Order_Status");
		           
		OrdersRequest orderRequest=new OrdersRequest(0, user_id, ShippingAddress, BillingAddress, null, Order_Status, ProductID);
		
		try {
			boolean response=orderService.CreateOrder(orderRequest);
			resp.getWriter().println("Order "+orderRequest.getOrder_id()+" Created Successfully");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		} catch (OrderCreateException e) {
			e.printStackTrace();
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
	
	}

}
