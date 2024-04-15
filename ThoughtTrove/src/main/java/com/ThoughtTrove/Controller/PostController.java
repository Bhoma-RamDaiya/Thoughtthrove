package com.ThoughtTrove.Controller;

import com.ThoughtTrove.Config.AppConstant;
import com.ThoughtTrove.Dto.Request.PostDto;
import com.ThoughtTrove.Dto.Response.ApiResponse;
import com.ThoughtTrove.Dto.Response.PostResponseDto;
import com.ThoughtTrove.Entity.Post;
import com.ThoughtTrove.Service.FileService;
import com.ThoughtTrove.Service.Serviceimpl.PostServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private  String path ;
//create post
    @PostMapping("/User/{userId}/Category/{categoryId}")
    public ResponseEntity createPost(@RequestBody PostDto postDto , @PathVariable int userId ,@PathVariable int categoryId){
        PostDto postDtos = postService.createPost(postDto ,userId ,categoryId);
        return new ResponseEntity<>(postDtos , HttpStatus.CREATED);
    }
//get post by category
    @GetMapping("/getPostByCategory/{categoryId}")
    public ResponseEntity getAllPostByCategory(@PathVariable int categoryId){
        List<PostDto> postDtos = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos , HttpStatus.ACCEPTED);
    }
    //get post by user
    @GetMapping("/getPostByUser/{userId}")
    public ResponseEntity getAllPostByUser(@PathVariable int userId){
        List<PostDto> postDtos = postService.getPostByuser(userId);
        return new ResponseEntity<>(postDtos , HttpStatus.ACCEPTED);
    }
    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity getByPostId(@PathVariable int postId){
        PostDto postDtos = postService.getPostByPostId(postId);
        return new ResponseEntity<>(postDtos , HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllPost")
    public ResponseEntity getAll(@RequestParam(value = "pageNo" , defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNo ,
                                 @RequestParam(value = "pageSize" , defaultValue = AppConstant.PAGE_SIZE , required = false) Integer pageSize
                                 , @RequestParam(value = "sortBy" , defaultValue = AppConstant.SORT_BY ,required = false) String sortBy,
                                 @RequestParam(value = "sortDir" ,defaultValue = AppConstant.SORT_DIR ,required = false) String sortDir){
        PostResponseDto postDtos = postService.getAllPost(pageNo , pageSize , sortBy ,sortDir);
        return new ResponseEntity<>(postDtos , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletePost/{postId}")
    public  ResponseEntity deletePostById( @PathVariable int postId){
        postService.deletePost(postId);
        return  new ResponseEntity<>(new ApiResponse("Post Is successfullu deleted " , true) , HttpStatus.ACCEPTED);
    }

    @PutMapping("/updatePost/{postId}")
    public  ResponseEntity updatePost( @RequestBody PostDto postDto , @PathVariable int postId){
        PostDto postDto1 = postService.updatePost(postDto , postId);
        return  new ResponseEntity<>(postDto1 , HttpStatus.ACCEPTED);
    }
    @GetMapping("/search")
  public  ResponseEntity postByTitle( @RequestParam("kewords") String kewords){
        List<PostDto> postDtos = postService.searchPosts(kewords);
        return new ResponseEntity<>(postDtos , HttpStatus.ACCEPTED);
  }

    //upload Image
    @PostMapping("/image/uploadImage/{postId}")
    public ResponseEntity uploadPostImage(@RequestParam("image")MultipartFile image , @PathVariable int postId ) throws IOException {

        PostDto postDto = postService.getPostByPostId(postId);

        String filename = fileService.uploadImage(path, image);

            postDto.setImageName(filename);
            PostDto updatedPost = postService.updatePost(postDto , postId);
            return  new ResponseEntity<>(updatedPost , HttpStatus.OK);

    }
    //Method to serve file
    @GetMapping(value = "/image/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
    public  void  downloadImage(@PathVariable("imageName") String imageName , HttpServletResponse response)
            throws IOException{
        InputStream resource = fileService.getResource(path , imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource , response.getOutputStream());
    }
}
