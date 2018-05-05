package app.fitness.com.fitness.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yinyanting on 2017/9/24.
 */

public class Post {
    private String content, title, userName;
    private int likeCount, postId, userId;
    private List<Integer> photos;
    private CreateTime createTime;

    public CreateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(CreateTime createTime) {
        this.createTime = createTime;
    }

    Post(){
        photos = new ArrayList<Integer>();
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }
}
