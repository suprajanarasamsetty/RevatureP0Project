package com.service;

import java.sql.SQLException;
import java.util.List;

import com.Exception.CategoryCreateException;
import com.Exception.CategoryDeleteException;
import com.Exception.CategoryNotFoundException;
import com.Exception.CategoryUpdateException;
import com.dao.CategoryDAOClass;
import com.dto.CategoryRequest;
import com.dto.CategoryResponse;

public class CategoryService {
	
	private final CategoryDAOClass categoryDao;
	
	public CategoryService() {
		categoryDao =new CategoryDAOClass();
	}
	
	public CategoryResponse getCategoryById(long id) throws SQLException, CategoryNotFoundException {
		return categoryDao.getCategoryById(id);
	}
	
	public boolean CreateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryCreateException {
		return categoryDao.CreateCategory(categoryRequest);
	}
	
	public boolean UpdateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryUpdateException{
		return categoryDao.UpdateCategory(categoryRequest);
	}
	
	public boolean DeleteCategoryById(long id) throws SQLException, CategoryDeleteException {
		return categoryDao.DeleteCategoryById(id);
	}

	public List<CategoryResponse> getAllCategories() throws SQLException, CategoryNotFoundException {
		return categoryDao.getAllCategories();
	}
	
	public static void main(String[] args) throws SQLException, CategoryNotFoundException, CategoryUpdateException {
		CategoryService Category = new CategoryService();	
		
		
		//for inserting values in Category table
//		System.out.println(Category.CreateCategory(new CategoryRequest(1, "Fashion")));
//		System.out.println(Category.CreateCategory(new CategoryRequest(2, "Electronics")));
//		System.out.println(Category.CreateCategory(new CategoryRequest(3, "Beauty")));
//		System.out.println(Category.CreateCategory(new CategoryRequest(4, "Appliances")));
//		System.out.println(Category.CreateCategory(new CategoryRequest(5, "Books")));
		
		//for fetching all values from Category table
//		List<CategoryResponse> category=Category.getAllCategories();
//		
//		for(CategoryResponse CR : category) {
//			System.out.println(CR.toString());
//		}
	}

}
