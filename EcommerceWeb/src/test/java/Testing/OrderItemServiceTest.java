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

import com.Exception.OrderItemCreateException;
import com.Exception.OrderItemDeleteException;
import com.Exception.OrderItemNotFoundException;
import com.Exception.OrderItemUpdateException;
import com.dao.OrderItemDAOClass;
import com.dto.OrderItemRequest;
import com.dto.OrderItemResponse;
import com.service.OrderItemService;

public class OrderItemServiceTest {
	
	@Mock
    private OrderItemDAOClass orderItemDao;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderItemById() throws SQLException, OrderItemNotFoundException {
        // Arrange
        OrderItemResponse mockOrderItem = new OrderItemResponse(1, 1, 1, 01, 7000.00);
        when(orderItemDao.getOrderItemById(1)).thenReturn(mockOrderItem);

        // Act
        OrderItemResponse orderItem = orderItemService.getOrderItemById(1);

        // Assert
        assertEquals(mockOrderItem, orderItem);
        verify(orderItemDao, times(1)).getOrderItemById(1);
    }

    @Test
    void testCreateOrderItem() throws SQLException, OrderItemCreateException {
        // Arrange
        OrderItemRequest orderItemRequest = new OrderItemRequest(1, 1, 1, 01, 7000.00);
        when(orderItemDao.CreateOrderItem(orderItemRequest)).thenReturn(true);

        // Act
        boolean result = orderItemService.CreateOrderItem(orderItemRequest);

        // Assert
        assertEquals(true, result);
        verify(orderItemDao, times(1)).CreateOrderItem(orderItemRequest);
    }

    @Test
    void testUpdateOrderItem() throws SQLException, OrderItemUpdateException {
        // Arrange
        OrderItemRequest orderItemRequest = new OrderItemRequest(3, 39, 3, 1, 7000.00);
        when(orderItemDao.UpdateOrderItem(orderItemRequest)).thenReturn(true);

        // Act
        boolean result = orderItemService.UpdateOrderItem(orderItemRequest);

        // Assert
        assertEquals(true, result);
        verify(orderItemDao, times(1)).UpdateOrderItem(orderItemRequest);
    }

    @Test
    void testDeleteOrderItem() throws SQLException, OrderItemDeleteException {
        // Arrange
        when(orderItemDao.DeleteOrderItem(3)).thenReturn(true);

        // Act
        boolean result = orderItemService.DeleteOrderItem(3);

        // Assert
        assertEquals(true, result);
        verify(orderItemDao, times(1)).DeleteOrderItem(3);
    }

    @Test
    void testGetAllOrderItems() throws SQLException, OrderItemNotFoundException {
        // Arrange
        OrderItemResponse orderItem1 = new OrderItemResponse(1, 1, 1, 01, 7000.00);
        OrderItemResponse orderItem2 = new OrderItemResponse(2, 2, 2, 02, 4998.00);
        List<OrderItemResponse> mockOrderItemList = Arrays.asList(orderItem1, orderItem2);
        when(orderItemDao.getAllOrderItems()).thenReturn(mockOrderItemList);

        // Act
        List<OrderItemResponse> orderItems = orderItemService.getAllOrderItems();

        // Assert
        assertEquals(mockOrderItemList, orderItems);
        verify(orderItemDao, times(1)).getAllOrderItems();
    }

    @Test
    void testGetOrderItemByIdThrowsException() throws SQLException, OrderItemNotFoundException {
        // Arrange
        when(orderItemDao.getOrderItemById(1)).thenThrow(new OrderItemNotFoundException("Order item not found"));

        // Act & Assert
        assertThrows(OrderItemNotFoundException.class, () -> orderItemService.getOrderItemById(1));
        verify(orderItemDao, times(1)).getOrderItemById(1);
    }

    @Test
    void testCreateOrderItemThrowsException() throws SQLException, OrderItemCreateException {
        // Arrange
        OrderItemRequest orderItemRequest = new OrderItemRequest(1, 1, 1, 01, 7000.00);
        when(orderItemDao.CreateOrderItem(orderItemRequest)).thenThrow(new OrderItemCreateException("Order item creation failed"));

        // Act & Assert
        assertThrows(OrderItemCreateException.class, () -> orderItemService.CreateOrderItem(orderItemRequest));
        verify(orderItemDao, times(1)).CreateOrderItem(orderItemRequest);
    }

    @Test
    void testUpdateOrderItemThrowsException() throws SQLException, OrderItemUpdateException {
        // Arrange
        OrderItemRequest orderItemRequest = new OrderItemRequest(1, 1, 1, 01, 7000.00);
        when(orderItemDao.UpdateOrderItem(orderItemRequest)).thenThrow(new OrderItemUpdateException("Order item update failed"));

        // Act & Assert
        assertThrows(OrderItemUpdateException.class, () -> orderItemService.UpdateOrderItem(orderItemRequest));
        verify(orderItemDao, times(1)).UpdateOrderItem(orderItemRequest);
    }

    @Test
    void testDeleteOrderItemThrowsException() throws SQLException, OrderItemDeleteException {
        // Arrange
        when(orderItemDao.DeleteOrderItem(1)).thenThrow(new OrderItemDeleteException("Order item deletion failed"));

        // Act & Assert
        assertThrows(OrderItemDeleteException.class, () -> orderItemService.DeleteOrderItem(1));
        verify(orderItemDao, times(1)).DeleteOrderItem(1);
    }

}
