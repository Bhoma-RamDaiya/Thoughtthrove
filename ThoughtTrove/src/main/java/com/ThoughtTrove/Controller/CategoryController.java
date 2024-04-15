package com.ThoughtTrove.Controller;

import com.ThoughtTrove.Dto.Request.CategoryRequestDto;
import com.ThoughtTrove.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
     CategoryService categoryService;
    @PostMapping("/addCategory")
    public ResponseEntity createCategory(@Valid @RequestBody CategoryRequestDto dto){
        CategoryRequestDto requestDto = categoryService.createCategory(dto);
        return  new ResponseEntity<>(requestDto , HttpStatus.CREATED);
    }
    @PutMapping("/updateCategory/{catagoryId}")
    public ResponseEntity updateCategory(@Valid @RequestBody CategoryRequestDto dto , @PathVariable int catagoryId){
        CategoryRequestDto requestDto = categoryService.updateCategory(dto ,catagoryId);
        return  new ResponseEntity<>(requestDto , HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity deleteCategory( @RequestParam("catagoryId") int catagoryId){
         categoryService.deleteCategory(catagoryId);
        return  new ResponseEntity<>("Message deleted" , HttpStatus.ACCEPTED);
    }
    @GetMapping("/getCategory")
    public ResponseEntity getById( @RequestParam("catagoryId") int catagoryId){
       CategoryRequestDto requestDto =  categoryService.getCategoryById(catagoryId);
        return  new ResponseEntity<>(requestDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllCAtegory")
    public ResponseEntity getAll(){
       List<CategoryRequestDto> requestDtos =  categoryService.getAllCategory();
        return  new ResponseEntity<>(requestDtos , HttpStatus.ACCEPTED);
    }
}
