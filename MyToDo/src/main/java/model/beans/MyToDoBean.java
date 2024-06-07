package model.beans;

import java.time.LocalDate;

public class MyToDoBean {
	private int id ;
	private String title ;
	private String priority ;
	private LocalDate deadline ;
	private String content ;
	private int completed;
	private int user_id;
	
	
	public int getId() {
		return id;
	}
	
	public MyToDoBean(int id,String t,int p,LocalDate d,String c, int completed, int ui){
		this.id = id;
		title = t;
		
		switch(p) {
		case 1 : 
			 priority = "低";
			 break;
		case 2 :
			 priority = "中";
			 break;
		case 3 :
			 priority = "高";
			 break;
		}

		deadline = d;
		content = c;
		this.completed = completed;
		user_id = ui;
	}
	
	//getter setter
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
