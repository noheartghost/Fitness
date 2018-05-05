package app.fitness.com.fitness.util;

import java.sql.Timestamp;


public class Trend {
	
	private int trend_id,good,user_id;
	private Timestamp time;
	private String content;
	public Trend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trend(int trend_id, int good, int user_id, Timestamp time, String content) {
		super();
		this.trend_id = trend_id;
		this.good = good;
		this.user_id = user_id;
		this.time = time;
		this.content = content;
	}
	public int getTrend_id() {
		return trend_id;
	}
	public void setTrend_id(int trend_id) {
		this.trend_id = trend_id;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
