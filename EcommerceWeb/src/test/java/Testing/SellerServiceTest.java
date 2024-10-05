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

import com.Exception.SellerCreateException;
import com.Exception.SellerDeleteException;
import com.Exception.SellerNotFoundException;
import com.Exception.SellerUpdateException;
import com.dao.SellerDAOClass;
import com.dto.SellerRequest;
import com.dto.SellerResponse;
import com.service.SellerService;


public class SellerServiceTest {
	
	@Mock
    private SellerDAOClass sellerDao;

    @InjectMocks
    private SellerService sellerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSellerById() throws SQLException, SellerNotFoundException {
        // Arrange
        SellerResponse mockSeller = new SellerResponse(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        when(sellerDao.getSellerById(1)).thenReturn(mockSeller);

        // Act
        SellerResponse seller = sellerService.getSellerById(1);

        // Assert
        assertEquals(mockSeller, seller);
        verify(sellerDao, times(1)).getSellerById(1);
    }

    @Test
    void testCreateSeller() throws SQLException, SellerCreateException {
        // Arrange
        SellerRequest sellerRequest = new SellerRequest(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        when(sellerDao.CreateSeller(sellerRequest)).thenReturn(true);

        // Act
        boolean result = sellerService.CreateSeller(sellerRequest);

        // Assert
        assertEquals(true, result);
        verify(sellerDao, times(1)).CreateSeller(sellerRequest);
    }

    @Test
    void testUpdateSeller() throws SQLException, SellerUpdateException {
        // Arrange
        SellerResponse sellerResponse = new SellerResponse(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        when(sellerDao.UpdateSeller(sellerResponse)).thenReturn(true);

        // Act
        boolean result = sellerService.UpdateSeller(sellerResponse);

        // Assert
        assertEquals(true, result);
        verify(sellerDao, times(1)).UpdateSeller(sellerResponse);
    }

    @Test
    void testDeleteSeller() throws SQLException, SellerDeleteException {
        // Arrange
        when(sellerDao.DeleteSeller(1)).thenReturn(true);

        // Act
        boolean result = sellerService.DeleteSeller(1);

        // Assert
        assertEquals(true, result);
        verify(sellerDao, times(1)).DeleteSeller(1);
    }

    @Test
    void testGetAllSellers() throws SQLException, SellerNotFoundException {
        // Arrange
        SellerResponse seller1 = new SellerResponse(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        SellerResponse seller2 = new SellerResponse(2, "Surya", "surya@gmail.com", "surya@123", "LuxeMart", "Suggests a premium shopping experience", 9);
        List<SellerResponse> mockSellerList = Arrays.asList(seller1, seller2);
        when(sellerDao.getAllSellers()).thenReturn(mockSellerList);

        // Act
        List<SellerResponse> sellers = sellerService.getAllSellers();

        // Assert
        assertEquals(mockSellerList, sellers);
        verify(sellerDao, times(1)).getAllSellers();
    }

    @Test
    void testGetSellerByIdThrowsException() throws SQLException, SellerNotFoundException {
        // Arrange
        when(sellerDao.getSellerById(1)).thenThrow(new SellerNotFoundException("Seller not found"));

        // Act & Assert
        assertThrows(SellerNotFoundException.class, () -> sellerService.getSellerById(1));
        verify(sellerDao, times(1)).getSellerById(1);
    }

    @Test
    void testCreateSellerThrowsException() throws SellerCreateException, SQLException {
        // Arrange
        SellerRequest sellerRequest = new SellerRequest(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        when(sellerDao.CreateSeller(sellerRequest)).thenThrow(new SellerCreateException("Seller creation failed"));

        // Act & Assert
        assertThrows(SellerCreateException.class, () -> sellerService.CreateSeller(sellerRequest));
        verify(sellerDao, times(1)).CreateSeller(sellerRequest);
    }

    @Test
    void testUpdateSellerThrowsException() throws SellerUpdateException, SQLException {
        // Arrange
        SellerResponse sellerResponse = new SellerResponse(1, "Kiran", "kiran@gmail.com", "Kiran@457", "TrendHive", "hub of the latest products and trends", 8);
        when(sellerDao.UpdateSeller(sellerResponse)).thenThrow(new SellerUpdateException("Seller update failed"));

        // Act & Assert
        assertThrows(SellerUpdateException.class, () -> sellerService.UpdateSeller(sellerResponse));
        verify(sellerDao, times(1)).UpdateSeller(sellerResponse);
    }

    @Test
    void testDeleteSellerThrowsException() throws SellerDeleteException, SQLException {
        // Arrange
        when(sellerDao.DeleteSeller(1)).thenThrow(new SellerDeleteException("Seller deletion failed"));

        // Act & Assert
        assertThrows(SellerDeleteException.class, () -> sellerService.DeleteSeller(1));
        verify(sellerDao, times(1)).DeleteSeller(1);
    }

}
