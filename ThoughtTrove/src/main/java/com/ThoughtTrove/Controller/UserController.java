package com.ThoughtTrove.Controller;

import com.ThoughtTrove.Dto.Request.UserRequestDto;
import com.ThoughtTrove.Dto.Response.UserResponseDto;
import com.ThoughtTrove.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity createUser(@Validated @RequestBody UserRequestDto userRequestDto){

            System.out.println(userRequestDto.getName() +" "+ userRequestDto.getAbout()+" " + userRequestDto.getEmailId()) ;

            UserResponseDto userResponseDto = userService.createUser(userRequestDto);
            return  new ResponseEntity<>(userResponseDto , HttpStatus.CREATED);

    }
    @GetMapping("/get_all_user")
    public  ResponseEntity getAllUser(){


            List<UserResponseDto> userResponseDtos = userService.getAllUser();
            return  new ResponseEntity<>(userResponseDtos , HttpStatus.ACCEPTED);



    }
    @GetMapping("/get_by_userId")
    public  ResponseEntity getUserByUserId(@Validated @RequestParam("userId") int userId){

            UserResponseDto userResponseDto = userService.getUserByUserId(userId);
            return  new ResponseEntity<>(userResponseDto , HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/delete_userId")
    public  ResponseEntity deleteUser(@Validated @RequestParam("userId") int userId){

            String msg = userService.deleteUser(userId);
            return  new ResponseEntity<>(msg , HttpStatus.ACCEPTED);

    }
    @PutMapping("/{userId}")
    public  ResponseEntity updateUser(@Validated @PathVariable("userId") Integer userId, @RequestBody UserRequestDto userRequestDto){

            UserResponseDto userResponseDto = userService.updateUser(userId ,userRequestDto );
            return  new ResponseEntity<>(userResponseDto , HttpStatus.ACCEPTED);

    }
}
