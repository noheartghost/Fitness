package app.fitness.com.fitness.util;

public class Dress {
	
	private int dress_id,user_id;
	private String dress_kind,dress_path;
	public Dress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dress(int dress_id, int user_id, String dress_kind, String dress_path) {
		super();
		this.dress_id = dress_id;
		this.user_id = user_id;
		this.dress_kind = dress_kind;
		this.dress_path = dress_path;
	}
	public int getDress_id() {
		return dress_id;
	}
	public void setDress_id(int dress_id) {
		this.dress_id = dress_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDress_kind() {
		return dress_kind;
	}
	public void setDress_kind(String dress_kind) {
		this.dress_kind = dress_kind;
	}
	public String getDress_path() {
		return dress_path;
	}
	public void setDress_path(String dress_path) {
		this.dress_path = dress_path;
	}
	
	

}
