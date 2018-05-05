package app.fitness.com.fitness.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReviewList implements Serializable {

	
private List<Review> reviewlist = new ArrayList<Review>();
	
	public List<Review> getReviewList() {
		return reviewlist;
	}

	public void setReviewList(List<Review> reviewlist) {
		this.reviewlist = reviewlist;
	}

	public ReviewList(List<Review> reviewlist) {
		super();
		this.reviewlist = reviewlist;
	}

	public ReviewList(){
	}
}
