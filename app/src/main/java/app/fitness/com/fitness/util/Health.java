package app.fitness.com.fitness.util;

import java.sql.Date;

public class Health {
	
	private int health,sleep_time,heart_jump,user_id;
	private Date time;
	
	public Health() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Health(int health, int sleep_time, int heart_jump, int user_id,
			Date time) {
		super();
		this.health = health;
		this.sleep_time = sleep_time;
		this.heart_jump = heart_jump;
		this.user_id = user_id;
		this.time = time;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSleep_time() {
		return sleep_time;
	}

	public void setSleep_time(int sleep_time) {
		this.sleep_time = sleep_time;
	}

	public int getHeart_jump() {
		return heart_jump;
	}

	public void setHeart_jump(int heart_jump) {
		this.heart_jump = heart_jump;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
	

}
