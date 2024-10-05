package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.ProductResponse;
import com.utils.ConnectionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchProducts extends HttpServlet{
	
	@WebServlet("/search")
	public class SearchServlet extends HttpServlet {

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String query = request.getParameter("query");
	        List<ProductResponse> products = new ArrayList<>();

	        String sql = "SELECT * FROM Product WHERE ProductName LIKE ?";
	        
	        if (query == null || query.trim().isEmpty()) {
	            request.setAttribute("products", products);
	            request.getRequestDispatcher("searchResults.jsp").forward(request, response);
	            return;
	        }

	        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
	        	
	        	PreparedStatement statement = connection.prepareStatement(sql);

		            statement.setString(1, "%" + query + "%");

		            try (ResultSet resultSet = statement.executeQuery()) {
		                while (resultSet.next()) {
		                    int ProductID = resultSet.getInt("ProductID");
		                    String ProductName = resultSet.getString("ProductName");
		                    double ProductPrice = resultSet.getDouble("ProductPrice");
		                    int SellerID=resultSet.getInt("SellerID");
		                    int CategoryId=resultSet.getInt("CategoryId");
		                    String ProductImage = resultSet.getString("ProductImage");
		                    String ProductDescription=resultSet.getString("ProductDescription");
		                    int user_id=resultSet.getInt("user_id");

		                    ProductResponse product = new ProductResponse(ProductID, ProductName, ProductPrice, SellerID, CategoryId, ProductImage, ProductDescription, user_id);
		                    products.add(product);
		                }
	        	
	        }
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        request.setAttribute("products", products);
	        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
	    }
	}

}
