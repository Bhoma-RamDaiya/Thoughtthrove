package com.ThoughtTrove.Dto.Request;

import com.ThoughtTrove.Entity.Category;
import com.ThoughtTrove.Entity.Comment;
import com.ThoughtTrove.Entity.User;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private  int postId;
    private   String title;
    private   String content;

    private  String imageName;
    private Date addedDate;
    private CategoryRequestDto category;
    private UserRequestDto user;
    private Set<CommentDto> comments = new HashSet<>();

}
