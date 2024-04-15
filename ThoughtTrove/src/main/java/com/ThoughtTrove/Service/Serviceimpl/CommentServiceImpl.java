package com.ThoughtTrove.Service.Serviceimpl;

import com.ThoughtTrove.Dto.Request.CommentDto;
import com.ThoughtTrove.Entity.Comment;
import com.ThoughtTrove.Entity.Post;
import com.ThoughtTrove.Exceptions.ResourceNotFoundException;
import com.ThoughtTrove.Repository.CommentRepository;
import com.ThoughtTrove.Repository.PostRepository;
import com.ThoughtTrove.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "postId", postId));
        System.out.print(postId);
        Comment comment =  modelMapper.map(commentDto , Comment.class);
        comment.setPost(post);
        System.out.println(post);
        comment.setDate(new Date());
        Comment saveComment =  commentRepository.save(comment);
 CommentDto commentDto1 =  modelMapper.map(saveComment , CommentDto.class);
        return commentDto1;

    }

    @Override
    public void deletePost(Integer commentId) {
Comment com = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment" ,"commentId" , commentId));
commentRepository.delete(com);
    }
}
