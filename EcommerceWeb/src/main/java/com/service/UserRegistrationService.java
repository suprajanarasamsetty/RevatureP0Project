package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.UserCreationException;
import com.Exception.UserDeleteException;
import com.Exception.UserNotFoundException;
import com.Exception.UserUpdateException;
import com.dao.UserRegistrationDAOClass;
import com.dto.UserRegistrationRequest;
import com.dto.UserRegistrationResponse;


public class UserRegistrationService {
	
	private final UserRegistrationDAOClass userRegistrationDao;
		
		public UserRegistrationService() {
			userRegistrationDao =new UserRegistrationDAOClass();
		}
		public UserRegistrationResponse getUserById(long id) throws UserNotFoundException, SQLException{
			return userRegistrationDao.getUserById(id);
		}
		
		public boolean DeleteUser(long id) throws UserDeleteException {
			return userRegistrationDao.DeleteUser(id);
		}			
		
		public boolean UpdateUser(UserRegistrationResponse userRegistrationResponse) throws SQLException, UserUpdateException {
			return userRegistrationDao.UpdateUser(userRegistrationResponse);
			
		}
		public boolean CreateUser(UserRegistrationRequest userRegistrationRequest) throws SQLException, UserCreationException {
			return userRegistrationDao.CreateUser(userRegistrationRequest);
			
		}
		public List<UserRegistrationResponse> getAllUsers() throws SQLException, UserNotFoundException{
			return userRegistrationDao.getAllUsers();
		}
		
		public static void main(String[] args) throws SQLException, UserDeleteException, UserUpdateException {
			UserRegistrationService urs=new UserRegistrationService();
			
			//System.out.println(urs.DeleteUser(8));
			
			System.out.println(urs.getUserById(9));
			
			//for Updating the user
			//System.out.println(urs.UpdateUser(new UserRegistrationResponse(1, "Raheem", "Sheik", "babulal@gmail.com", "babulal123", 949479728, "Konthamuru, Rajahmundry",Role.BUYER)));
			
			//for inserting values in user_registration table
			//System.out.println(urs.UpdateUser(new UserRegistrationResponse(2, "Suneel", "Karri", "suneel@gmail.com", "suneel@123", 730663981, "Bobbili, Visakhapatnam", Role.BUYER)));
//			System.out.println(urs.UpdateUser(new UserRegistrationResponse(6, "kiran", "Kammidi", "kammidikiran@gmail.com", "KiranSindhu", 759425865, "Lalacheruvu, Rajahmundry", Role.SELLER)));
//			System.out.println(urs.UpdateUser(new UserRegistrationResponse(3, "Emily", "Johnson", "emily.johnson@example.com", "EmilyJohn@123", 345-678-9012, "789 Pine St, Somewhere, USA", Role.BUYER)));
//			System.out.println(urs.UpdateUser(new UserRegistrationResponse(4, "Michael", "Brown", "michael.brown@example.com", "Michael@4455", 456-789-0123, "101 Maple Ave, Cityville, USA",Role.BUYER)));
//			System.out.println(urs.UpdateUser(new UserRegistrationResponse(5, "Olivia", "Davis", "olivia.davis@example.com", "Olivia$554", 567-890-1234, "202 Oak St, Townsville, USA",Role.BUYER)));
//			System.out.println(urs.UpdateUser(new UserRegistrationResponse(7, "John","Doe","johndoe@example.com", "johndoe", 754125685, "123 street, Texas , America", Role.BUYER)));
			
			
			//for fetching the values from user table
//			List<UserRegistrationResponse> user=urs.getAllUsers();
//			
//			for(UserRegistrationResponse User:user) {
//				System.out.println(User.toString());
//				
//			}
								
		}

}
