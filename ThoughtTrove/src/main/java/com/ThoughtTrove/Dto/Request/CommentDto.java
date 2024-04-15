package com.ThoughtTrove.Dto.Request;

import com.ThoughtTrove.Entity.Post;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private  int commentId;
    private  String content;
    private Date date;
}
