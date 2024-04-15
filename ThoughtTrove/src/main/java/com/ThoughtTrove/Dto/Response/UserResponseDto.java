package com.ThoughtTrove.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private  int userId;

    private  String name;
    private String emailId;
    private String password;
    private String about;
}
