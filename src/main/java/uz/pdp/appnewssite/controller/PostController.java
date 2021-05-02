package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDTO;
import uz.pdp.appnewssite.service.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize(value = "hasAnyAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@RequestBody PostDTO postDTO) {

        ApiResponse apiResponse = postService.addPost(postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //Edit Post
    @PreAuthorize(value = "hasAnyAuthority('EDIT_POST')")
    @PutMapping(value = "/{postId}")
    public HttpEntity<?> editPost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {

        ApiResponse apiResponse = postService.editPost(postId, postDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //Delete Post
    @PreAuthorize(value = "hasAnyAuthority('DELTE_POST')")
    @DeleteMapping(value = "/{postId}")
    public HttpEntity<?> deletePost(@PathVariable Long postId) {

        ApiResponse apiResponse = postService.deletePost(postId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //View all posts without permissions
    @GetMapping
    public HttpEntity<?> getPosts() {

        List<Post> postList = postService.getPosts();
        return ResponseEntity.ok(postList);
    }
}
