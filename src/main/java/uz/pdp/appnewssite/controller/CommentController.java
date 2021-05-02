package uz.pdp.appnewssite.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDTO;
import uz.pdp.appnewssite.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //Writing a comment to Post
    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDTO commentDTO) {

        ApiResponse apiResponse = commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    //View Comments without authorities
    @GetMapping
    public HttpEntity<?> getComments() {

        List<Comment> commentList = commentService.getCommentList();
        return ResponseEntity.ok(commentList);
    }

    //Deleting an other's Comment from admin side
    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT')")
    @DeleteMapping(value = "/{commentId}")
    public HttpEntity<?> deleteComments(@PathVariable Long commentId) {

        ApiResponse apiResponse = commentService.deleteComments(commentId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    // Deleting ow comment
    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping(value = "/myComment/{commentId}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long commentId) {

        ApiResponse apiResponse = commentService.deleteMyComment(commentId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
