package app.fitness.com.fitness.util;

import java.io.Serializable;
import java.sql.Timestamp;

public class MyActivity implements Serializable{
	
	private int activityid ;
	private int hobbyid;
	private String title;
	private String content;
	private Timestamp register_begin_time;
	private Timestamp register_end_time;
	private Timestamp activity_begin_time;
	private Timestamp activity_end_time;
	private String location;
	private String entrepreneur;
	private String contractor;
	private String level;
	private int activityscore;
	private String activityphoto;
	public MyActivity(){};
	public MyActivity(int activityid, int hobbyid, String title, String content,
			Timestamp register_begin_time, Timestamp register_end_time,
			Timestamp activity_begin_time, Timestamp activity_end_time,
			String location, String entrepreneur, String contractor,
			String level, int activityscore, String activityphoto) {
		super();
		this.activityid = activityid;
		this.hobbyid = hobbyid;
		this.title = title;
		this.content = content;
		this.register_begin_time = register_begin_time;
		this.register_end_time = register_end_time;
		this.activity_begin_time = activity_begin_time;
		this.activity_end_time = activity_end_time;
		this.location = location;
		this.entrepreneur = entrepreneur;
		this.contractor = contractor;
		this.level = level;
		this.activityscore = activityscore;
		this.activityphoto = activityphoto;
	}
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}
	public int getHobbyid() {
		return hobbyid;
	}
	public void setHobbyid(int hobbyid) {
		this.hobbyid = hobbyid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegister_begin_time() {
		return register_begin_time;
	}
	public void setRegister_begin_time(Timestamp register_begin_time) {
		this.register_begin_time = register_begin_time;
	}
	public Timestamp getRegister_end_time() {
		return register_end_time;
	}
	public void setRegister_end_time(Timestamp register_end_time) {
		this.register_end_time = register_end_time;
	}
	public Timestamp getActivity_begin_time() {
		return activity_begin_time;
	}
	public void setActivity_begin_time(Timestamp activity_begin_time) {
		this.activity_begin_time = activity_begin_time;
	}
	public Timestamp getActivity_end_time() {
		return activity_end_time;
	}
	public void setActivity_end_time(Timestamp activity_end_time) {
		this.activity_end_time = activity_end_time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEntrepreneur() {
		return entrepreneur;
	}
	public void setEntrepreneur(String entrepreneur) {
		this.entrepreneur = entrepreneur;
	}
	public String getContractor() {
		return contractor;
	}
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getActivityscore() {
		return activityscore;
	}
	public void setActivityscore(int activityscore) {
		this.activityscore = activityscore;
	}
	public String getActivityphoto() {
		return activityphoto;
	}
	public void setActivityphoto(String activityphoto) {
		this.activityphoto = activityphoto;
	}
	

}
