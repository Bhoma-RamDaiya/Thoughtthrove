package com.ThoughtTrove.Service;

import com.ThoughtTrove.Dto.Request.PostDto;
import com.ThoughtTrove.Dto.Response.PostResponseDto;
import com.ThoughtTrove.Entity.Post;

import java.util.List;

public interface PostService {
//create post
    PostDto createPost(PostDto postDto , int userId , int categoryId);

    PostDto updatePost(PostDto postDto , int postId);
//Delete post
    void deletePost(int postId);
    // Single post get

    PostDto getPostByPostId(int postId);

    //get all post
    PostResponseDto getAllPost(Integer pageNo  , Integer pageSize , String sortBy ,String sortDir);
    //get post by category
     List<PostDto> getPostByCategory(int catagoryId);
     // get post by user
    List<PostDto> getPostByuser(int userId);

    //Search Post By category
    List<PostDto> searchPosts(String keyword);


}
