package com.ThoughtTrove.Dto.Response;

import com.ThoughtTrove.Dto.Request.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostResponseDto {
    private List<PostDto> content;
    private int pageNumber;
    private  int pageSize;
    private  long totalElements;
    private  int totalPages;
    private  boolean lastPage;


}
