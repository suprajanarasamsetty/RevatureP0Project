package com.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.Exception.UserCreationException;
import com.Exception.UserDeleteException;
import com.Exception.UserNotFoundException;
import com.Exception.UserUpdateException;
import com.dto.UserRegistrationRequest;
import com.dto.UserRegistrationResponse;
import com.entity.Role;
import com.utils.ConnectionFactory;

import ch.qos.logback.classic.Logger;

public class UserRegistrationDAOClass implements UserRegistrationDAO {
	
	private static final Logger logger=(Logger) LoggerFactory.getLogger(UserRegistrationDAOClass.class);

	@Override
	public UserRegistrationResponse getUserById(long id) throws SQLException, UserNotFoundException {
		logger.debug("Enter getUserById method with id: {}",id);
		
		MDC.put("user_id", String.valueOf(id));
		
		String query="SELECT *FROM user_registration WHERE user_id=?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
		PreparedStatement stmt=con.prepareStatement(query);
				
		//Optional<UserRegistrationResponse> User=Optional.empty();
		
		stmt.setLong(1, id);
		
		try(ResultSet rs=stmt.executeQuery()){
			
			if (rs.next()) {
                // Retrieve the role string from the ResultSet
                String roleString = rs.getString("role");

                // Convert the role string to the Role enum
                Role role;
                try {
                    role = Role.valueOf(roleString.toUpperCase()); // Convert to uppercase if needed
                } catch (IllegalArgumentException e) {
                    logger.error("Invalid role value retrieved: {}", roleString);
                    throw new UserNotFoundException("Invalid role value for user ID: " + id);
                }

				UserRegistrationResponse userRegistrationResponse=new UserRegistrationResponse(
						rs.getInt("user_id"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getString("user_password"),
						rs.getLong("user_phonenumber"),
						rs.getString("user_address"),
						role);
				
				//User.of(userRegistrationResponse);
				
				logger.info("User Retrieved Succesfully");
				return userRegistrationResponse;
			}
			else { 
				logger.warn("User not Found with ID: {}",id);
				throw new UserNotFoundException("User Not Found With ID: "+id);
				
			}
		}
		//return User.orElse(()->new UserNotFoundException("User Not Found"));
	}
		catch(SQLException e) {
			logger.error("unable to find the user");
			e.printStackTrace();
		}finally {
			MDC.remove("user_id");
		}
		return null;
	}

	@Override
	public boolean CreateUser(UserRegistrationRequest userRegistrationRequest) throws UserCreationException {
		logger.debug("Entering CreateUser method");
		String query="INSERT INTO user_registration(user_first_name, user_last_name, user_email, user_password, user_phonenumber, user_address, role) value(?,?,?,?,?,?,?)";
	
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setString(1, userRegistrationRequest.getUser_first_name());
			stmt.setString(2, userRegistrationRequest.getUser_last_name());
			stmt.setString(3, userRegistrationRequest.getUser_email());
			stmt.setString(4, userRegistrationRequest.getUser_password());
			stmt.setLong(5, userRegistrationRequest.getUser_phonenumber());
			stmt.setString(6, userRegistrationRequest.getUser_address());
			stmt.setString(7, userRegistrationRequest.getRole().name());
			
			int result=stmt.executeUpdate();
			if(result>0) {
				logger.info("User Created Successfully");
				return true;
			}

			else {
				logger.warn("Failed to Create user");
				throw new UserCreationException("User Not Created");
			}
			
		}
		catch(SQLException e) {
			logger.error("SQL Error");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UpdateUser(UserRegistrationResponse userRegistrationResponse) throws SQLException, UserUpdateException {
		logger.debug("Entering UpdateUser method with request: {}",userRegistrationResponse.getUser_id());
		
        String query="UPDATE user_registration SET user_first_name=?, user_last_name=?, user_email=?, user_password=?, user_phonenumber=?, user_address=?, role=? WHERE user_id=?";
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
		
			
			stmt.setString(1, userRegistrationResponse.getUser_first_name());
			stmt.setString(2, userRegistrationResponse.getUser_last_name());
			stmt.setString(3, userRegistrationResponse.getUser_email());
			stmt.setString(4, userRegistrationResponse.getUser_password());
			stmt.setLong(5, userRegistrationResponse.getUser_phonenumber());
			stmt.setString(6, userRegistrationResponse.getUser_address());
			stmt.setString(7, userRegistrationResponse.getRole().name());
			stmt.setInt(8, userRegistrationResponse.getUser_id());

			
			int result=stmt.executeUpdate();
			
			if(result>0) {
				logger.info("User Updated Successfully with ID: {}",userRegistrationResponse.getUser_id());
				return true;
			}
			else{
				logger.warn("Failed to Update user with ID: {}", userRegistrationResponse.getUser_id());
				throw new UserUpdateException("User Not Updated with ID: "+userRegistrationResponse.getUser_id());
				
			}
		}
		catch(SQLException e) {
			logger.warn("Failed to update user with ID: {}", userRegistrationResponse.getUser_id());
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean DeleteUser(long id) throws UserDeleteException {
		logger.debug("Entering DeleteUser method with id: {}",id);
		String query="DELETE FROM user_registration where user_id = ?";
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()){
			
			PreparedStatement stmt=con.prepareStatement(query);
			
			stmt.setLong(1, id);
			
			int  result=stmt.executeUpdate();
			System.out.println("Rows Affected "+result);
			
			if(result>0) {
				logger.info("User deleted successfully with ID: {}",id);
				return true;
			}
			else {
				logger.warn("Failed to delete user with ID: {}",id);
				throw new UserDeleteException("User not delted with id: "+id);
			}
		}
		catch(SQLException e) {
			logger.error("SQL Error Occured with ID: {}",id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserRegistrationResponse> getAllUsers() throws SQLException {
		logger.debug("Entering getAllUsers method");
		
        String query="select *from user_registration";
		
		List<UserRegistrationResponse> userRegistrationResponse=new ArrayList<>();
		
		try(Connection con=ConnectionFactory.getConnectionFactory().getConnection()) {
			
			PreparedStatement stmt= con.prepareStatement(query);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()) {
				 String roleString = rs.getString("role"); // Retrieve role from ResultSet

		            // Convert the roleString to Role enum
		            Role role;
		            try {
		                role = Role.valueOf(roleString.toUpperCase());
		            } catch (IllegalArgumentException e) {
		                logger.warn("Unexpected role value: {}", roleString);
		                role = Role.BUYER; // Default to a fallback role
		            }

		            // Retrieve other fields
		            int user_id = rs.getInt("user_id");
		            String user_first_name = rs.getString("user_first_name");
		            String user_last_name = rs.getString("user_last_name");
		            String user_email = rs.getString("user_email");
		            String user_password = rs.getString("user_password");
		            long user_phonenumber = rs.getLong("user_phonenumber");
		            String user_address = rs.getString("user_address");

		            // Create UserRegistrationResponse object
		            UserRegistrationResponse user = new UserRegistrationResponse(user_id, user_first_name, user_last_name, user_email, user_password, user_phonenumber, user_address, role);
		            
				    userRegistrationResponse.add(user);
				
			}
			logger.info("Retrieved {} users",userRegistrationResponse.size());
			return userRegistrationResponse;
				
		}
		catch(SQLException e) {
			logger.error("SQL Error Occured with all users");
			e.printStackTrace();
			//throw new UserNotFoundException("User Not Found Exception");
		}
		return null;
	}
	public boolean authenticateUser(String user_email, String user_password) {
        // SQL query to select the user by username and password
        String query = "SELECT COUNT(*) FROM user_registration WHERE user_email = ? AND user_password = ?";

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             
             
             // Set parameters
             preparedStatement.setString(1, user_email);
             preparedStatement.setString(2, user_password); // Note: passwords should be hashed in practice

             // Execute query
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 // Check if user exists
                 if (resultSet.next()) {
                     int count = resultSet.getInt(1);
                     return count > 0; // Return true if count > 0, otherwise false
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log exception
        }
        return false; // Default to false if exception occurs or no result
    }
	// Method to authenticate user and return their role
    public Role authenticateRole(String email, String password) {
        // Replace with your actual database connection code
        String query = "SELECT role FROM user_registration WHERE user_email = ? AND user_password = ?";
        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get the role from the result set
                String roleString = rs.getString("role");

                // Convert the roleString to Role enum
                try {
                    return Role.valueOf(roleString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Handle unknown role
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Authentication failed or user not found
    }
    
    public boolean validateUser(String user_email, String user_password) {
        boolean isAuthenticated = false;

        String query = "SELECT * FROM user_registration WHERE user_email = ? AND user_password = ?";

        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Set parameters
            stmt.setString(1, user_email);
            stmt.setString(2, user_password); // Ensure passwords are hashed in production

            try (ResultSet rs = stmt.executeQuery()) {
                // Check if any user with the provided credentials exists
                if (rs.next()) {
                    isAuthenticated = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them or rethrow as a custom exception)
        }
        
        return isAuthenticated;
    }
    
    public UserRegistrationResponse getUserByEmail(String email) throws SQLException {
        UserRegistrationResponse userRegistrationResponse = null;
        String query = "SELECT * FROM user_registration WHERE user_email = ?";

        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Convert roleString to Role enum
                    String roleString = rs.getString("role");
                    Role role = Role.valueOf(roleString.toUpperCase());

                    // Create UserRegistrationResponse object
                    userRegistrationResponse = new UserRegistrationResponse(
                        rs.getInt("user_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getLong("user_phonenumber"),
                        rs.getString("user_address"),
                        role
                    );
                }
            }
        }

        return userRegistrationResponse;
    }
    
    public Integer getUserId(String user_email, String user_password) {
        String query = "SELECT user_id FROM user_registration WHERE user_email = ? AND user_password = ?";
        
        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            // Set parameters for the query
            stmt.setString(1, user_email);
            stmt.setString(2, user_password);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                // Check if a result is returned
                if (rs.next()) {
                    // Retrieve the user ID
                    return rs.getInt("user_id");
                } else {
                    // No user found
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
            throw new RuntimeException("Database error occurred while retrieving user ID", e);
        }
    }
    
    public Integer getSellerIdByEmail(String email) throws SQLException {
        Integer sellerID = null;
        String query = "SELECT SellerID FROM seller WHERE user_email = ?";

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){

        	PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    sellerID = resultSet.getInt("SellerID");
                }
            }
        }
        return sellerID;
    }


}
