package uz.pdp.appnewssite.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDTO;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    final CommentRepository commentRepository;
    final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ApiResponse addComment(CommentDTO commentDTO) {

        Optional<Post> optionalPost = postRepository.findById(commentDTO.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("Invalid Post id!", false);

        Comment comment = new Comment();
        comment.setText(comment.getText());
        comment.setPost(optionalPost.get());
        commentRepository.save(comment);
        return new ApiResponse("New Comment saved", true);
    }

    public List<Comment> getCommentList() {
        return commentRepository.findAll();
    }

    public ApiResponse deleteComments(Long commentId) {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent())
            return new ApiResponse("Invalid comment Id!", false);
        commentRepository.deleteById(commentId);
        return new ApiResponse("Comment deleted!", false);
    }

    public ApiResponse deleteMyComment(Long commentId) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (!optionalComment.isPresent())
            return new ApiResponse("Invalid comment Id!", false);

        User creator = optionalComment.get().getCreatedBy();
        if (!creator.getId().equals(user.getId()))
            return new ApiResponse("This is not your comment!", false);
        commentRepository.deleteById(commentId);
        return new ApiResponse("Comment deleted!", false);
    }
}
