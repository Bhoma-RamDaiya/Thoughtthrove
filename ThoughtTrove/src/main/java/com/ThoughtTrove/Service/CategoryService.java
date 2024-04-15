package com.ThoughtTrove.Service;

import com.ThoughtTrove.Dto.Request.CategoryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    //create category
    public CategoryRequestDto createCategory(CategoryRequestDto dto);

    //update category
    public CategoryRequestDto updateCategory(CategoryRequestDto dto , int categoryId);
    //Delete
    public void deleteCategory(int categoryId);
    //findby id
    public CategoryRequestDto getCategoryById(int catagoryid);
    //get All

    public List<CategoryRequestDto> getAllCategory();


}
