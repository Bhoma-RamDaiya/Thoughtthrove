package com.ThoughtTrove.Dto.Request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserRequestDto {
    private  int userId;
@NotEmpty(message = "name can not be Empty")
    private  String name;

@Email(message = "Please Enter a Valid Email Id")
    private String emailId;
    @NotEmpty(message = "Password not be blank")
    private String password;
 @NotEmpty(message = "Please Enter at least 10 words about you")
    private String about;

}
