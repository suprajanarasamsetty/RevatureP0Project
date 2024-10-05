package Controller;

import java.io.IOException;
import java.util.List;

import com.Exception.ProductNotFoundException;
import com.dto.ProductResponse;
import com.service.ProductService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Product/all")
public class ProductsAllController extends HttpServlet{
	
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Assuming ProductService is a class that interacts with the database
	        ProductService productService = new ProductService();
	        List<ProductResponse> products;
			try {
				products = productService.getAllProducts();
				req.setAttribute("products", products);
		        RequestDispatcher dispatcher = req.getRequestDispatcher("viewProducts.jsp");
		        dispatcher.forward(req, resp);
			} catch (ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	    }

}
