package Testing;
	
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Exception.ProductCreationException;
import com.Exception.ProductDeletionException;
import com.Exception.ProductNotFoundException;
import com.Exception.ProductUpdateException;
import com.dao.ProductDAOClass;
import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.service.ProductService;

public class ProductServiceTest {
	    @Mock
	    private ProductDAOClass productDao;

	    @InjectMocks
	    private ProductService productService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetProductById() throws SQLException, ProductNotFoundException {
	        // Arrange
	        ProductResponse mockProduct = new ProductResponse(1, "Product1", 100.0, 10, 1, "image1.jpg", "Description of Product1");
	        when(productDao.getProductById(1)).thenReturn(mockProduct);

	        // Act
	        ProductResponse product = productService.getProductById(1);

	        // Assert
	        assertEquals(mockProduct, product);
	        verify(productDao, times(1)).getProductById(1);
	    }

	    @Test
	    void testDeleteProductByID() throws SQLException, ProductDeletionException {
	        // Arrange
	        when(productDao.DeleteProductById(1)).thenReturn(true);

	        // Act
	        boolean result = productService.DeleteProductByID(1);

	        // Assert
	        assertEquals(true, result);
	        verify(productDao, times(1)).DeleteProductById(1);
	    }

	    @Test
	    void testUpdateProduct() throws SQLException, ProductUpdateException {
	        // Arrange
	        ProductResponse productResponse = new ProductResponse(1, "Product1", 150.0, 10, 1, "image1.jpg", "Updated description");
	        when(productDao.UpdateProduct(productResponse)).thenReturn(true);

	        // Act
	        boolean result = productService.UpdateProduct(productResponse);

	        // Assert
	        assertEquals(true, result);
	        verify(productDao, times(1)).UpdateProduct(productResponse);
	    }

	    @Test
	    void testCreateProduct() throws SQLException, ProductCreationException {
	        // Arrange
	        ProductRequest productRequest = new ProductRequest(1, "Product1", 200.0, 10, 1, "image1.jpg", "Description of Product1");
	        when(productDao.CreateProduct(productRequest)).thenReturn(true);

	        // Act
	        boolean result = productService.createProduct(productRequest);

	        // Assert
	        assertEquals(true, result);
	        verify(productDao, times(1)).CreateProduct(productRequest);
	    }

	    @Test
	    void testGetAllProducts() throws SQLException, ProductNotFoundException {
	        // Arrange
	        ProductResponse product1 = new ProductResponse(1, "Product1", 100.0, 10, 1, "image1.jpg", "Description of Product1");
	        ProductResponse product2 = new ProductResponse(2, "Product2", 200.0, 20, 2, "image2.jpg", "Description of Product2");
	        List<ProductResponse> mockProductList = Arrays.asList(product1, product2);
	        when(productDao.getAllProducts()).thenReturn(mockProductList);

	        // Act
	        List<ProductResponse> products = productService.getAllProducts();

	        // Assert
	        assertEquals(mockProductList, products);
	        verify(productDao, times(1)).getAllProducts();
	    }

	    @Test
	    void testGetProductByIdThrowsException() throws SQLException, ProductNotFoundException {
	        // Arrange
	        when(productDao.getProductById(1)).thenThrow(new ProductNotFoundException("Product not found"));

	        // Act & Assert
	        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
	        verify(productDao, times(1)).getProductById(1);
	    }

	    @Test
	    void testCreateProductThrowsException() throws SQLException, ProductCreationException {
	        // Arrange
	        ProductRequest productRequest = new ProductRequest(1, "Product1", 200.0, 10, 1, "image1.jpg", "Description of Product1");
	        when(productDao.CreateProduct(productRequest)).thenThrow(new ProductCreationException("Product creation failed"));

	        // Act & Assert
	        assertThrows(ProductCreationException.class, () -> productService.createProduct(productRequest));
	        verify(productDao, times(1)).CreateProduct(productRequest);
	    }

}
