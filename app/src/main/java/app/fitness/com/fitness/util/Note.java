package app.fitness.com.fitness.util;

import java.sql.Timestamp;

/**
 * @author lenovo
 *
 */
public class Note {
	
	private int id,user_id;
	private Timestamp time;
	private String content;
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(int id, int user_id, Timestamp time, String content) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.time = time;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
