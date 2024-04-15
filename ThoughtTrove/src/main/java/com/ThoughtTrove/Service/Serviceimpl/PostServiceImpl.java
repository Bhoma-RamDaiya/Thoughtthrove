package com.ThoughtTrove.Service.Serviceimpl;

import com.ThoughtTrove.Dto.Request.PostDto;
import com.ThoughtTrove.Dto.Response.PostResponseDto;
import com.ThoughtTrove.Entity.Category;
import com.ThoughtTrove.Entity.Post;
import com.ThoughtTrove.Entity.User;
import com.ThoughtTrove.Exceptions.ResourceNotFoundException;
import com.ThoughtTrove.Repository.CategoryRepository;
import com.ThoughtTrove.Repository.PostRepository;
import com.ThoughtTrove.Repository.UserRepository;
import com.ThoughtTrove.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;
     @Autowired
    CategoryRepository categoryRepository;
    @Override
    public PostDto createPost(PostDto postDto , int userId , int categoryId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post =  modelMapper.map(postDto , Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepository.save(post);
        return modelMapper.map(savedPost , PostDto.class);


    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "postId" , postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost , PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
 Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "postId" , postId));
 postRepository.delete(post);
    }

    @Override
    public PostDto getPostByPostId(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post" , "postId" , postId));
        return modelMapper.map(post , PostDto.class);
    }

    @Override
    public PostResponseDto getAllPost(Integer pageNo , Integer pageSize , String sortBy , String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
        Pageable pageable = PageRequest.of(pageNo ,pageSize , sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post>postList = postPage.getContent();


      List<PostDto>postDtos =  postList.stream().map(post -> modelMapper.map(post , PostDto.class)).collect(Collectors.toList());
         PostResponseDto responseDto = new PostResponseDto();
         responseDto.setContent(postDtos);
         responseDto.setPageNumber(postPage.getNumber());
         responseDto.setTotalElements(postPage.getTotalElements());
         responseDto.setPageSize(postPage.getSize());
         responseDto.setTotalPages(postPage.getTotalPages());
         responseDto.setLastPage(postPage.isLast());
        return responseDto;
    }

    @Override
    public List<PostDto> getPostByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId", categoryId));

        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map((post-> modelMapper.map(post , PostDto.class))).toList();
    }

    @Override
    public List<PostDto> getPostByuser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User" , "userId",userId));
        List<Post>posts = postRepository.findByUser(user);
//it is returning all the dto list i.e = List<PostDto> postdtos = posts.stream().map(post -> modelMapper.map(post , PostDto.class)).toList();
        return posts.stream().map(post -> modelMapper.map(post , PostDto.class)).toList();
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
//        List<Post>posts = postRepository.findByTitleContaining(keyword);
//        return posts.stream().map((post) -> modelMapper.map(post , PostDto.class)).toList();
          List<Post>posts = postRepository.searchByTitle("%" +keyword+"%");
        return posts.stream().map((post) -> modelMapper.map(post , PostDto.class)).toList();
         // return null;
    }
}
