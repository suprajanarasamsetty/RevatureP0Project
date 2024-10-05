package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Exception.OrderCreateException;
import com.Exception.OrderDeleteException;
import com.Exception.OrderNotFoundException;
import com.dao.OrdersDAOClass;
import com.dto.OrdersRequest;
import com.dto.OrdersResponse;
import com.service.OrdersService;

public class OrdersServiceTest {
	
	@Mock
    private OrdersDAOClass ordersDao;

    @InjectMocks
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderById() throws SQLException, OrderNotFoundException {
        // Arrange
        OrdersResponse mockOrder = new OrdersResponse(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Processing", 8);
        when(ordersDao.getOrderById(1)).thenReturn(mockOrder);

        // Act
        OrdersResponse order = ordersService.getOrderById(1);

        // Assert
        assertEquals(mockOrder, order);
        verify(ordersDao, times(1)).getOrderById(1);
    }

    @Test
    void testCreateOrder() throws SQLException, OrderCreateException {
        // Arrange
        OrdersRequest orderRequest = new OrdersRequest(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Processing", 8);
        when(ordersDao.CreateOrder(orderRequest)).thenReturn(true);

        // Act
        boolean result = ordersService.CreateOrder(orderRequest);

        // Assert
        assertEquals(true, result);
        verify(ordersDao, times(1)).CreateOrder(orderRequest);
    }

    @Test
    void testUpdateOrder() throws SQLException {
        // Arrange
        OrdersResponse orderResponse = new OrdersResponse(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Shipped", 8);
        when(ordersDao.UpateOrder(orderResponse)).thenReturn(true);

        // Act
        boolean result = ordersService.UpateOrder(orderResponse);

        // Assert
        assertEquals(true, result);
        verify(ordersDao, times(1)).UpateOrder(orderResponse);
    }

    @Test
    void testDeleteOrder() throws SQLException, OrderDeleteException {
        // Arrange
        when(ordersDao.DeleteOrder(38)).thenReturn(true);

        // Act
        boolean result = ordersService.DeleteOrder(38);

        // Assert
        assertEquals(true, result);
        verify(ordersDao, times(1)).DeleteOrder(38);
    }

    @Test
    void testGetAllOrders() throws SQLException, OrderNotFoundException {
        // Arrange
        OrdersResponse order1 = new OrdersResponse(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Processing", 8);
        OrdersResponse order2 = new OrdersResponse(2, 2, "456 Maple Ave", "789 Oak St", LocalDateTime.now(), "Shipped", 9);
        List<OrdersResponse> mockOrderList = Arrays.asList(order1, order2);
        when(ordersDao.getAllOrders()).thenReturn(mockOrderList);

        // Act
        List<OrdersResponse> orders = ordersService.getAllOrders();

        // Assert
        assertEquals(mockOrderList, orders);
        verify(ordersDao, times(1)).getAllOrders();
    }

    @Test
    void testGetOrdersByUserId() throws SQLException, OrderNotFoundException {
        // Arrange
        OrdersResponse order1 = new OrdersResponse(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Processing", 8);
        OrdersResponse order2 = new OrdersResponse(2, 1, "456 Maple Ave", "789 Oak St", LocalDateTime.now(), "Shipped", 9);
        List<OrdersResponse> mockOrderList = Arrays.asList(order1, order2);
        when(ordersDao.getOrdersByUserId(1)).thenReturn(mockOrderList);

        // Act
        List<OrdersResponse> orders = ordersService.getOrdersByUserId(1);

        // Assert
        assertEquals(mockOrderList, orders);
        verify(ordersDao, times(1)).getOrdersByUserId(1);
    }

    @Test
    void testGetOrderByIdThrowsException() throws SQLException, OrderNotFoundException {
        // Arrange
        when(ordersDao.getOrderById(1)).thenThrow(new OrderNotFoundException("Order not found"));

        // Act & Assert
        assertThrows(OrderNotFoundException.class, () -> ordersService.getOrderById(1));
        verify(ordersDao, times(1)).getOrderById(1);
    }

    @Test
    void testCreateOrderThrowsException() throws SQLException, OrderCreateException {
        // Arrange
        OrdersRequest orderRequest = new OrdersRequest(1, 1, "123 Elm St", "123 Elm St", LocalDateTime.now(), "Processing", 8);
        when(ordersDao.CreateOrder(orderRequest)).thenThrow(new OrderCreateException("Order creation failed"));

        // Act & Assert
        assertThrows(OrderCreateException.class, () -> ordersService.CreateOrder(orderRequest));
        verify(ordersDao, times(1)).CreateOrder(orderRequest);
    }

}
