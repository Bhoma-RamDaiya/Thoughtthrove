package com.ThoughtTrove.Service.Serviceimpl;

import com.ThoughtTrove.Dto.Request.CategoryRequestDto;
import com.ThoughtTrove.Entity.Category;
import com.ThoughtTrove.Exceptions.ResourceNotFoundException;
import com.ThoughtTrove.Repository.CategoryRepository;
import com.ThoughtTrove.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
  private   CategoryRepository categoryRepository;
    @Autowired
   private ModelMapper modelMapper;
    @Override
    public CategoryRequestDto createCategory(CategoryRequestDto dto) {

        Category category = modelMapper.map(dto , Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory , CategoryRequestDto.class);
    }

    @Override
    public CategoryRequestDto updateCategory(CategoryRequestDto dto, int categoryId) {
        Optional<Category>optional = categoryRepository.findById(categoryId);
        if(optional.isPresent()){
            Category category = optional.get();
            System.out.println(dto.getCategoryTitle()+" "+ dto.getCategoryDescrption()+" "+categoryId);
            category.setCategoryTitle(dto.getCategoryTitle());
            category.setCategoryDescrption(dto.getCategoryDescrption());
            Category updatedCategory = categoryRepository.save(category);
            return modelMapper.map(updatedCategory, CategoryRequestDto.class);
        }
       else{
           throw new ResourceNotFoundException("Category","categoryId" , categoryId);
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        Optional<Category>optional = categoryRepository.findById(categoryId);
        if(optional.isPresent()){
            Category category = optional.get();
            categoryRepository.delete(category);

        }
        else{
            throw new ResourceNotFoundException("Category","categoryId" , categoryId);
        }
    }

    @Override
    public CategoryRequestDto getCategoryById(int categoryId) {
        Optional<Category>optional = categoryRepository.findById(categoryId);
        if(optional.isPresent()){
            Category category = optional.get();
            return modelMapper.map(category , CategoryRequestDto.class);

        }
        else{
            throw new ResourceNotFoundException("Category","categoryId" , categoryId);
        }
    }

    @Override
    public List<CategoryRequestDto> getAllCategory() {
       List<Category> categories=  categoryRepository.findAll();
        List<CategoryRequestDto> categoryRequestDtoList = categories.stream().map((cat)->modelMapper.map(cat ,CategoryRequestDto.class)).toList();
        //collect(Collectors.toList())
        return categoryRequestDtoList;

    }

}
