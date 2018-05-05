package app.fitness.com.fitness.util;

public class Friend {
	
	private int friend_id,user_id,other_id;

	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friend(int friend_id, int user_id, int other_id) {
		super();
		this.friend_id = friend_id;
		this.user_id = user_id;
		this.other_id = other_id;
	}

	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getOther_id() {
		return other_id;
	}

	public void setOther_id(int other_id) {
		this.other_id = other_id;
	}
	
	
	

}
