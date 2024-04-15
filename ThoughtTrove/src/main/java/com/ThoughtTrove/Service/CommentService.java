package com.ThoughtTrove.Service;

import com.ThoughtTrove.Dto.Request.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto , Integer postId);
     void deletePost(Integer commentId);
}
