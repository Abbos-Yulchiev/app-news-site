package uz.pdp.appnewssite.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDTO;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ApiResponse addPost(PostDTO postDTO) {

        boolean existsByUrl = postRepository.existsByUrl(postDTO.getUrl());
        if (existsByUrl)
            return new ApiResponse("This url already used!", false);
        Post post = new Post(
                postDTO.getTitle(),
                postDTO.getText(),
                postDTO.getUrl()
        );
        postRepository.save(post);
        return new ApiResponse("New Post successfully added", true);
    }

    public ApiResponse editPost(Long postId, PostDTO postDTO) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent())
            return new ApiResponse("Invalid Post Id!", false);
        boolean exists = postRepository.existsByUrlAndIdNot(postDTO.getUrl(), postId);
        if (exists)
            return new ApiResponse("This url already used!", false);

        Post post = optionalPost.get();
        post.setTitle(post.getTitle());
        post.setText(postDTO.getText());
        post.setUrl(postDTO.getUrl());
        postRepository.save(post);
        return new ApiResponse("Post successfully edited", true);
    }

    public ApiResponse deletePost(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent())
            return new ApiResponse("Invalid Post Id!", false);

        postRepository.deleteById(postId);
        return new ApiResponse("Post successfully deleted", true);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }
}
