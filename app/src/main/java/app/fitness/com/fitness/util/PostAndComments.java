package app.fitness.com.fitness.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yinyanting on 2017/9/24.
 */

public class PostAndComments {
    private List<Comment> comments;
    private Post post;

    PostAndComments(){
        comments = new ArrayList<Comment>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
