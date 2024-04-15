package com.ThoughtTrove.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {
    private  int categoryId;
    @NotEmpty
    @Size(min = 4 , message = "size of title must be greater then 4")
    private  String categoryTitle;
    @NotBlank
    @Size(min = 6 , message = "please enter a detail description")
    private  String categoryDescrption;
}
