package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.dto.CategoryResponse;
import com.service.CategoryService;

@WebServlet("/Category/*")
public class CategoriesController extends HttpServlet {
	
	private CategoryService categoryService;

	public void init() {
		System.out.println("init");
		this.categoryService=new CategoryService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		System.out.println("get method invoked");
		
		System.out.println(req.getRequestURI().substring("EcommerceWeb/Category".length()+1));
		
		String path = req.getRequestURI();
	    String action = path.substring(path.lastIndexOf('/') + 1);
	    if (action.equals("all")) {

	    try {
			
				List<CategoryResponse> categories=categoryService.getAllCategories();
				
				for(CategoryResponse c : categories) {
					out.printf("<p>Category Name: %s</p>", c.getCategoryName());
				
                req.setAttribute("categories", categories);

				

		                // Forward request to JSP
		                RequestDispatcher dispatcher = req.getRequestDispatcher("/Categories.jsp");
		                dispatcher.forward(req, resp);	
				}
	    }
			catch(Exception e) {
				e.printStackTrace();
			}
	    }
			
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
