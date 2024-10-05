package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.Exception.CategoryCreateException;
import com.Exception.CategoryDeleteException;
import com.Exception.CategoryNotFoundException;
import com.Exception.CategoryUpdateException;
import com.dto.CategoryRequest;
import com.dto.CategoryResponse;

public interface CategoryDAO {
	
	CategoryResponse getCategoryById(long id) throws SQLException, CategoryNotFoundException;
	boolean CreateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryCreateException;
	boolean UpdateCategory(CategoryRequest categoryRequest) throws SQLException, CategoryUpdateException;
	boolean DeleteCategoryById(long id) throws SQLException, CategoryDeleteException;
	List<CategoryResponse> getAllCategories() throws SQLException, CategoryNotFoundException;

}
