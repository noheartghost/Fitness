package app.fitness.com.fitness.util;

import java.sql.Date;

public class Exercise {
	
	private int exercise_id,steps,heat,kilometers,user_id;
	private Date time;
	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exercise(int exercise_id, int steps, int heat, int kilometers,
			int user_id, Date time) {
		super();
		this.exercise_id = exercise_id;
		this.steps = steps;
		this.heat = heat;
		this.kilometers = kilometers;
		this.user_id = user_id;
		this.time = time;
	}
	public int getExercise_id() {
		return exercise_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public int getKilometers() {
		return kilometers;
	}
	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
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
