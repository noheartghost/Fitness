package app.fitness.com.fitness.util;

import java.sql.Date;
import java.sql.Timestamp;

public class Common {
	
	private int common_id,user_id,trend_id;
	private Timestamp time;
	private String content;
	
	public Common() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Common(int common_id, int user_id, int trend_id, Timestamp time,
			String content) {
		super();
		this.common_id = common_id;
		this.user_id = user_id;
		this.trend_id = trend_id;
		this.time = time;
		this.content = content;
	}

	public int getCommon_id() {
		return common_id;
	}

	public void setCommon_id(int common_id) {
		this.common_id = common_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getTrend_id() {
		return trend_id;
	}

	public void setTrend_id(int trend_id) {
		this.trend_id = trend_id;
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
