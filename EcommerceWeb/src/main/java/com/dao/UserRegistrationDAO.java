package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.UserCreationException;
import com.Exception.UserDeleteException;
import com.Exception.UserNotFoundException;
import com.Exception.UserUpdateException;
import com.dto.UserRegistrationRequest;
import com.dto.UserRegistrationResponse;


public interface UserRegistrationDAO {
	
	UserRegistrationResponse getUserById(long id) throws SQLException, UserNotFoundException;
	boolean CreateUser(UserRegistrationRequest userRegistrationRequest) throws UserCreationException;
	boolean UpdateUser(UserRegistrationResponse userRegistrationResponse) throws SQLException, UserUpdateException;
	boolean DeleteUser(long id) throws UserDeleteException;
	List<UserRegistrationResponse> getAllUsers() throws SQLException, UserNotFoundException; 

}
