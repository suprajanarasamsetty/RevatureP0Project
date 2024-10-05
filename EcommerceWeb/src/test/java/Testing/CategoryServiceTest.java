package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Exception.CategoryCreateException;
import com.Exception.CategoryDeleteException;
import com.Exception.CategoryNotFoundException;
import com.Exception.CategoryUpdateException;
import com.dao.CategoryDAOClass;
import com.dto.CategoryRequest;
import com.dto.CategoryResponse;
import com.service.CategoryService;

public class CategoryServiceTest {
	
	@Mock
    private CategoryDAOClass categoryDao;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategoryById() throws SQLException, CategoryNotFoundException {
        // Arrange
        CategoryResponse mockCategory = new CategoryResponse(1, "Fashion", null);
        when(categoryDao.getCategoryById(1)).thenReturn(mockCategory);

        // Act
        CategoryResponse category = categoryService.getCategoryById(1);

        // Assert
        assertEquals(mockCategory, category);
        verify(categoryDao, times(1)).getCategoryById(1);
    }

    @Test
    void testCreateCategory() throws SQLException, CategoryCreateException {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest(1, "Electronics", null);
        when(categoryDao.CreateCategory(categoryRequest)).thenReturn(true);

        // Act
        boolean result = categoryService.CreateCategory(categoryRequest);

        // Assert
        assertEquals(true, result);
        verify(categoryDao, times(1)).CreateCategory(categoryRequest);
    }

    @Test
    void testUpdateCategory() throws SQLException, CategoryUpdateException {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest(1, "Updated Category", null);
        when(categoryDao.UpdateCategory(categoryRequest)).thenReturn(true);

        // Act
        boolean result = categoryService.UpdateCategory(categoryRequest);

        // Assert
        assertEquals(true, result);
        verify(categoryDao, times(1)).UpdateCategory(categoryRequest);
    }

    @Test
    void testDeleteCategoryById() throws SQLException, CategoryDeleteException {
        // Arrange
        when(categoryDao.DeleteCategoryById(1)).thenReturn(true);

        // Act
        boolean result = categoryService.DeleteCategoryById(1);

        // Assert
        assertEquals(true, result);
        verify(categoryDao, times(1)).DeleteCategoryById(1);
    }

    @Test
    void testGetAllCategories() throws SQLException, CategoryNotFoundException {
        // Arrange
        CategoryResponse category1 = new CategoryResponse(1, "Fashion", null);
        CategoryResponse category2 = new CategoryResponse(2, "Electronics", null);
        List<CategoryResponse> mockCategoryList = Arrays.asList(category1, category2);
        when(categoryDao.getAllCategories()).thenReturn(mockCategoryList);

        // Act
        List<CategoryResponse> categories = categoryService.getAllCategories();

        // Assert
        assertEquals(mockCategoryList, categories);
        verify(categoryDao, times(1)).getAllCategories();
    }

    @Test
    void testGetCategoryByIdThrowsException() throws SQLException, CategoryNotFoundException {
        // Arrange
        when(categoryDao.getCategoryById(1)).thenThrow(new CategoryNotFoundException("Category not found"));

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(1));
        verify(categoryDao, times(1)).getCategoryById(1);
    }

    @Test
    void testCreateCategoryThrowsException() throws SQLException, CategoryCreateException {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest(1, "Electronics", null);
        when(categoryDao.CreateCategory(categoryRequest)).thenThrow(new CategoryCreateException("Category creation failed"));

        // Act & Assert
        assertThrows(CategoryCreateException.class, () -> categoryService.CreateCategory(categoryRequest));
        verify(categoryDao, times(1)).CreateCategory(categoryRequest);
    }

    @Test
    void testUpdateCategoryThrowsException() throws SQLException, CategoryUpdateException {
        // Arrange
        CategoryRequest categoryRequest = new CategoryRequest(1, "Updated Category", null);
        when(categoryDao.UpdateCategory(categoryRequest)).thenThrow(new CategoryUpdateException("Category update failed"));

        // Act & Assert
        assertThrows(CategoryUpdateException.class, () -> categoryService.UpdateCategory(categoryRequest));
        verify(categoryDao, times(1)).UpdateCategory(categoryRequest);
    }

    @Test
    void testDeleteCategoryByIdThrowsException() throws SQLException, CategoryDeleteException {
        // Arrange
        when(categoryDao.DeleteCategoryById(1)).thenThrow(new CategoryDeleteException("Category deletion failed"));

        // Act & Assert
        assertThrows(CategoryDeleteException.class, () -> categoryService.DeleteCategoryById(1));
        verify(categoryDao, times(1)).DeleteCategoryById(1);
    }

}
