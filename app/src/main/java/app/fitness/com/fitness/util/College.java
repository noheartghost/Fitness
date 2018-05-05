package app.fitness.com.fitness.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class College implements Serializable {

	private List<String> collegename = new ArrayList<String>();
	
	public List<String> getCollegename() {
		return collegename;
	}

	public void setCollegename(List<String> collegename) {
		this.collegename = collegename;
	}

	public College(List<String> collegename) {
		super();
		this.collegename = collegename;
	}

	public College(){
	}
	
}
