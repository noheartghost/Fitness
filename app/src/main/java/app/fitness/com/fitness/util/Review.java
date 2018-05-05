package app.fitness.com.fitness.util;

import java.io.Serializable;
import java.sql.Date;

;

public class Review  implements Serializable {

	
	private int reviewid;
	private int userid;
	private int activityid ;
	private String reviewcontent;
	private float reviewscore;
	private Date datetime;
	
	
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	private String userphoto;
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}
	public String getReviewcontent() {
		return reviewcontent;
	}
	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}
	public float getReviewscore() {
		return reviewscore;
	}
	public void setReviewscore(float reviewscore) {
		this.reviewscore = reviewscore;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date date) {
		this.datetime = date;
	}
	
}
