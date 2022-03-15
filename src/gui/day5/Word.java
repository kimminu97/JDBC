package gui.day5;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Word implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String english;
	private String korean;
	private int level;    
	private LocalDate wday;    
	
	public Word() { }
	
	public Word(String english, String korean, int level, LocalDate wday) {
		super();
		this.english = english;
		this.korean = korean;
		this.level = level;
		this.wday = wday;
	}

	
	//getter,setter
	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getKorean() {
		return korean;
	}

	public void setKorean(String korean) {
		this.korean = korean;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LocalDate getWday() {
		return wday;
	}

	public void setWday(LocalDate wday) {
		this.wday = wday;
	}
	
	
}
