package com.ThoughtTrove.Service.Serviceimpl;

import com.ThoughtTrove.Dto.Request.UserRequestDto;
import com.ThoughtTrove.Dto.Response.UserResponseDto;
import com.ThoughtTrove.Entity.User;
import com.ThoughtTrove.Exceptions.ResourceNotFoundException;
import com.ThoughtTrove.Repository.UserRepository;
import com.ThoughtTrove.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
  private   UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userDtoToUser(userRequestDto);
        User savedUser = userRepository.save(user);
        UserResponseDto userResponse = UserTouserDto(savedUser);
        return userResponse;
    }

    @Override
    public UserResponseDto updateUser(int userId , UserRequestDto userRequestDto ) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
           User user = optional.get();
           user.setPassword(userRequestDto.getPassword());
           user.setName(userRequestDto.getName());
           user.setAbout(userRequestDto.getAbout());
           user.setEmailId(userRequestDto.getEmailId());
           User updateuser = userRepository.save(user);

            return UserTouserDto(updateuser);
        } else {
            throw new ResourceNotFoundException("User" , "id" , userId);
        }

    }

    @Override
    public String deleteUser(int userId) {
        Optional<User>optional = userRepository.findById(userId);
        if(optional.isEmpty()){
          throw  new ResourceNotFoundException("User", " user_Id" , userId);
        }
        User user = optional.get();
        userRepository.delete(user);
        return "User is deleted id no is :"+ userId;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(int i = 0; i<users.size(); i++){
            UserResponseDto userResponseDto = UserTouserDto(users.get(i));
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;

       // List<UserResponseDto> userResponseDtos = users.stream().map(this::UserTouserDto).toList();
      //  return userResponseDtos;
    }

    @Override
    public UserResponseDto getUserByUserId(Integer userid) {
 Optional<User> optional = userRepository.findById(userid);
 if(optional.isPresent()){
     User  user = optional.get();
     return  UserTouserDto(user);
 } else{
     throw  new ResourceNotFoundException("User" , "user_id" , userid);
 }


    }
    private User userDtoToUser(UserRequestDto userRequestDto){
//        User user = new User();
//        user.setName(userRequestDto.getName());
//        user.setEmailId(userRequestDto.getEmailId());
//        user.setAbout(userRequestDto.getAbout());
//        user.setPassword(userRequestDto.getPassword());
        User user = modelMapper.map(userRequestDto , User.class);

        return user;
    }
    private UserResponseDto UserTouserDto(User user){
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(user.getName());
//        userResponseDto.setEmailId(user.getEmailId());
//        userResponseDto.setAbout(user.getAbout());
//        userResponseDto.setPassword(user.getPassword());
//        userResponseDto.setId(user.getUserId());
        UserResponseDto userResponseDto = modelMapper.map(user , UserResponseDto.class);
        return userResponseDto;
    }
}
