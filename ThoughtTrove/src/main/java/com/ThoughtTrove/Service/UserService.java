package com.ThoughtTrove.Service;

import com.ThoughtTrove.Dto.Request.UserRequestDto;
import com.ThoughtTrove.Dto.Response.UserResponseDto;

import java.util.List;

public interface UserService {
 public UserResponseDto createUser(UserRequestDto userRequestDto);
    public UserResponseDto updateUser(int userId ,UserRequestDto userRequestDto);
    public String deleteUser(int userId);
    public List<UserResponseDto> getAllUser( );
    public UserResponseDto getUserByUserId(Integer userid);

}
