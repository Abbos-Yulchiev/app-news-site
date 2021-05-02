package uz.pdp.appnewssite.payload;

import lombok.Data;

@Data
public class CommentDTO {

    private String text;
    private Long postId;
}
