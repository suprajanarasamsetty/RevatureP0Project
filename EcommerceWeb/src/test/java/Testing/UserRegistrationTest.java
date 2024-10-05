package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.Exception.UserCreationException;
import com.Exception.UserDeleteException;
import com.Exception.UserNotFoundException;
import com.Exception.UserUpdateException;
import com.dao.UserRegistrationDAOClass;
import com.dto.UserRegistrationRequest;
import com.dto.UserRegistrationResponse;
import com.entity.Role;
import com.service.UserRegistrationService;

public class UserRegistrationTest {
	
	@Mock
    private UserRegistrationDAOClass userRegistrationDao;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @BeforeEach
    void setUp() {
        // No need for custom setup since Mockito will handle the injection
    }

    @Test
    void testGetUserById() throws SQLException, UserNotFoundException {
        // Arrange
        UserRegistrationResponse mockResponse = new UserRegistrationResponse(1, "Mohammed", "Majaaz", "majaaz0014@gmail.com", "password", 1234567890, "Address", Role.BUYER);
        when(userRegistrationDao.getUserById(1)).thenReturn(mockResponse);

        // Act
        UserRegistrationResponse response = userRegistrationService.getUserById(1);

        // Assert
        assertEquals(mockResponse, response);
        verify(userRegistrationDao, times(1)).getUserById(1);
    }

    @Test
    void testCreateUser() throws SQLException, UserCreationException {
        // Arrange
        UserRegistrationRequest request = new UserRegistrationRequest("Majaaz","Mohammed","majaaz00@gmail.com", "password", 987654321, "MehdiPatnam, Hyderabad", Role.BUYER);
        when(userRegistrationDao.CreateUser(request)).thenReturn(true);

        // Act
        boolean result = userRegistrationService.CreateUser(request);

        // Assert
        assertTrue(result);
        verify(userRegistrationDao, times(1)).CreateUser(request);
    }

    @Test
    void testGetAllUsers() throws SQLException, UserNotFoundException {
        // Arrange
        UserRegistrationResponse user1 = new UserRegistrationResponse(1, "Mohammed", "Majaaz", "majaaz0014@gmail.com", "password", 1234567890, "Address", Role.BUYER);
        UserRegistrationResponse user2 = new UserRegistrationResponse(2, "John", "Doe", "john.doe@gmail.com", "password123", 12345678, "Address", Role.SELLER);
        List<UserRegistrationResponse> mockList = Arrays.asList(user1, user2);
        when(userRegistrationDao.getAllUsers()).thenReturn(mockList);

        // Act
        List<UserRegistrationResponse> users = userRegistrationService.getAllUsers();

        // Assert
        assertNotNull(users, "The list of users should not be null");
        assertEquals(2, users.size(), "The size of the list should be 2");
        assertTrue(users.contains(user1), "The list should contain user1");
        assertTrue(users.contains(user2), "The list should contain user2");
        verify(userRegistrationDao, times(1)).getAllUsers();
    }

    @Test
    void testUpdateUser() throws SQLException, UserUpdateException {
        // Arrange
        UserRegistrationResponse response = new UserRegistrationResponse(1, "Mohammed", "Majaaz", "newemail@gmail.com", "newpassword", 1234567890, "New Address", Role.BUYER);
        when(userRegistrationDao.UpdateUser(response)).thenReturn(true);

        // Act
        boolean result = userRegistrationService.UpdateUser(response);

        // Assert
        assertTrue(result);
        verify(userRegistrationDao, times(1)).UpdateUser(response);
    }

    @Test
    void testDeleteUser() throws SQLException, UserDeleteException {
        // Arrange
        long id = 1;
        when(userRegistrationDao.DeleteUser(id)).thenReturn(true);

        // Act
        boolean result = userRegistrationService.DeleteUser(id);

        // Assert
        assertTrue(result);
        verify(userRegistrationDao, times(1)).DeleteUser(id);
    }

}
