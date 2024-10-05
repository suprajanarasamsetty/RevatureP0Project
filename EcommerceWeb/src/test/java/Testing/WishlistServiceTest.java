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

import com.Exception.WishlistCreateException;
import com.Exception.WishlistDeleteException;
import com.Exception.WishlistNotFoundException;
import com.Exception.WishlistUpdateException;
import com.dao.WishlistDAOClass;
import com.dto.WishlistRequest;
import com.dto.WishlistResponse;
import com.service.WishlistService;

public class WishlistServiceTest {
	
	 @Mock
	    private WishlistDAOClass wishlistDao;

	    @InjectMocks
	    private WishlistService wishlistService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetWishlistById() throws SQLException, WishlistNotFoundException {
	        // Arrange
	        WishlistResponse mockWishlist = new WishlistResponse(1, 1, 4);
	        when(wishlistDao.getWishlistById(1)).thenReturn(mockWishlist);

	        // Act
	        WishlistResponse wishlist = wishlistService.getWishlistById(1);

	        // Assert
	        assertEquals(mockWishlist, wishlist);
	        verify(wishlistDao, times(1)).getWishlistById(1);
	    }

	    @Test
	    void testCreateWishlist() throws WishlistCreateException {
	        // Arrange
	        WishlistRequest wishlistRequest = new WishlistRequest(1, 1, 4);
	        when(wishlistDao.CreateWishlist(wishlistRequest)).thenReturn(true);

	        // Act
	        boolean result = wishlistService.CreateWishlist(wishlistRequest);

	        // Assert
	        assertEquals(true, result);
	        verify(wishlistDao, times(1)).CreateWishlist(wishlistRequest);
	    }

	    @Test
	    void testUpdateWishlist() throws WishlistUpdateException {
	        // Arrange
	        WishlistRequest wishlistRequest = new WishlistRequest(1, 1, 4);
	        when(wishlistDao.UpdateWishlist(wishlistRequest)).thenReturn(true);

	        // Act
	        boolean result = wishlistService.UpdateWishlist(wishlistRequest);

	        // Assert
	        assertEquals(true, result);
	        verify(wishlistDao, times(1)).UpdateWishlist(wishlistRequest);
	    }

	    @Test
	    void testDeleteWishlistById() throws WishlistDeleteException {
	        // Arrange
	        when(wishlistDao.DeleteWishlistById(1)).thenReturn(true);

	        // Act
	        boolean result = wishlistService.DeleteWishlistById(1);

	        // Assert
	        assertEquals(true, result);
	        verify(wishlistDao, times(1)).DeleteWishlistById(1);
	    }

	    @Test
	    void testGetAllWishlistById() {
	        // Arrange
	        WishlistResponse wishlist1 = new WishlistResponse(1, 1, 4);
	        WishlistResponse wishlist2 = new WishlistResponse(2, 2, 9);
	        List<WishlistResponse> mockWishlistList = Arrays.asList(wishlist1, wishlist2);
	        when(wishlistDao.getAllWishlistById()).thenReturn(mockWishlistList);

	        // Act
	        List<WishlistResponse> wishlists = wishlistService.getAllWishlistById();

	        // Assert
	        assertEquals(mockWishlistList, wishlists);
	        verify(wishlistDao, times(1)).getAllWishlistById();
	    }

	    @Test
	    void testGetWishlistByIdThrowsException() throws SQLException, WishlistNotFoundException {
	        // Arrange
	        when(wishlistDao.getWishlistById(1)).thenThrow(new WishlistNotFoundException("Wishlist not found"));

	        // Act & Assert
	        assertThrows(WishlistNotFoundException.class, () -> wishlistService.getWishlistById(1));
	        verify(wishlistDao, times(1)).getWishlistById(1);
	    }

	    @Test
	    void testCreateWishlistThrowsException() throws WishlistCreateException {
	        // Arrange
	        WishlistRequest wishlistRequest = new WishlistRequest(1, 1, 4);
	        when(wishlistDao.CreateWishlist(wishlistRequest)).thenThrow(new WishlistCreateException("Wishlist creation failed"));

	        // Act & Assert
	        assertThrows(WishlistCreateException.class, () -> wishlistService.CreateWishlist(wishlistRequest));
	        verify(wishlistDao, times(1)).CreateWishlist(wishlistRequest);
	    }

	    @Test
	    void testUpdateWishlistThrowsException() throws WishlistUpdateException {
	        // Arrange
	        WishlistRequest wishlistRequest = new WishlistRequest(1, 1, 4);
	        when(wishlistDao.UpdateWishlist(wishlistRequest)).thenThrow(new WishlistUpdateException("Wishlist update failed"));

	        // Act & Assert
	        assertThrows(WishlistUpdateException.class, () -> wishlistService.UpdateWishlist(wishlistRequest));
	        verify(wishlistDao, times(1)).UpdateWishlist(wishlistRequest);
	    }

	    @Test
	    void testDeleteWishlistByIdThrowsException() throws WishlistDeleteException {
	        // Arrange
	        when(wishlistDao.DeleteWishlistById(1)).thenThrow(new WishlistDeleteException("Wishlist deletion failed"));

	        // Act & Assert
	        assertThrows(WishlistDeleteException.class, () -> wishlistService.DeleteWishlistById(1));
	        verify(wishlistDao, times(1)).DeleteWishlistById(1);
	    }

}
