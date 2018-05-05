package app.fitness.com.fitness.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hobby implements Serializable {

	private List<String> hobbyname = new ArrayList<String>();
	
	public List<String> getHobbyname() {
		return hobbyname;
	}

	public void setHobbyname(List<String> hobbyname) {
		this.hobbyname = hobbyname;
	}

	public Hobby(List<String> hobbyname) {
		super();
		this.hobbyname = hobbyname;
	}

	public Hobby(){
	}
		
	
}
