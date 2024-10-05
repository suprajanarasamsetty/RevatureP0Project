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

import com.Exception.CartCreateException;
import com.Exception.CartDeleteException;
import com.Exception.CartNotFoundException;
import com.Exception.CartUpdateException;
import com.dao.CartDAOClass;
import com.dto.CartRequest;
import com.dto.CartResponse;
import com.service.CartService;

public class CartServiceTest {
	
	@Mock
    private CartDAOClass cartDao;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartById() throws SQLException, CartNotFoundException {
        // Arrange
        CartResponse mockCart = new CartResponse(1, 3, 10, 2875.00, 1, null);
        when(cartDao.getCartById(1)).thenReturn(mockCart);

        // Act
        CartResponse cart = cartService.getCartById(1);

        // Assert
        assertEquals(mockCart, cart);
        verify(cartDao, times(1)).getCartById(1);
    }

    @Test
    void testCreateCart() throws SQLException, CartCreateException {
        // Arrange
        CartRequest cartRequest = new CartRequest(1, 3, 10, 2875.00, 1, null);
        when(cartDao.CreateCart(cartRequest)).thenReturn(true);

        // Act
        boolean result = cartService.CreateCart(cartRequest);

        // Assert
        assertEquals(true, result);
        verify(cartDao, times(1)).CreateCart(cartRequest);
    }

    @Test
    void testUpdateCart() throws SQLException, CartUpdateException {
        // Arrange
        CartRequest cartRequest = new CartRequest(1, 3, 10, 2875.00, 1, null);
        when(cartDao.UpdateCart(cartRequest)).thenReturn(true);

        // Act
        boolean result = cartService.UpdateCart(cartRequest);

        // Assert
        assertEquals(true, result);
        verify(cartDao, times(1)).UpdateCart(cartRequest);
    }

    @Test
    void testDeleteCart() throws SQLException, CartDeleteException {
        // Arrange
        when(cartDao.DeleteCart(1)).thenReturn(true);

        // Act
        boolean result = cartService.DeleteCart(1);

        // Assert
        assertEquals(true, result);
        verify(cartDao, times(1)).DeleteCart(1);
    }

    @Test
    void testGetAllCart() throws SQLException {
        // Arrange
        CartResponse cart1 = new CartResponse(1, 3, 10, 2875.00, 1, null);
        CartResponse cart2 = new CartResponse(2, 4, 5, 1450.00, 2, null);
        List<CartResponse> mockCartList = Arrays.asList(cart1, cart2);
        when(cartDao.getAllCart()).thenReturn(mockCartList);

        // Act
        List<CartResponse> carts = cartService.getAllCart();

        // Assert
        assertEquals(mockCartList, carts);
        verify(cartDao, times(1)).getAllCart();
    }

    @Test
    void testGetCartByIdThrowsException() throws SQLException, CartNotFoundException {
        // Arrange
        when(cartDao.getCartById(1)).thenThrow(new CartNotFoundException("Cart not found"));

        // Act & Assert
        assertThrows(CartNotFoundException.class, () -> cartService.getCartById(1));
        verify(cartDao, times(1)).getCartById(1);
    }

    @Test
    void testCreateCartThrowsException() throws SQLException, CartCreateException {
        // Arrange
        CartRequest cartRequest = new CartRequest(1, 3, 10, 2875.00, 1, null);
        when(cartDao.CreateCart(cartRequest)).thenThrow(new CartCreateException("Cart creation failed"));

        // Act & Assert
        assertThrows(CartCreateException.class, () -> cartService.CreateCart(cartRequest));
        verify(cartDao, times(1)).CreateCart(cartRequest);
    }

    @Test
    void testUpdateCartThrowsException() throws SQLException, CartUpdateException {
        // Arrange
        CartRequest cartRequest = new CartRequest(1, 3, 10, 2875.00, 1, null);
        when(cartDao.UpdateCart(cartRequest)).thenThrow(new CartUpdateException("Cart update failed"));

        // Act & Assert
        assertThrows(CartUpdateException.class, () -> cartService.UpdateCart(cartRequest));
        verify(cartDao, times(1)).UpdateCart(cartRequest);
    }

    @Test
    void testDeleteCartThrowsException() throws SQLException, CartDeleteException {
        // Arrange
        when(cartDao.DeleteCart(1)).thenThrow(new CartDeleteException("Cart deletion failed"));

        // Act & Assert
        assertThrows(CartDeleteException.class, () -> cartService.DeleteCart(1));
        verify(cartDao, times(1)).DeleteCart(1);
    }

}
