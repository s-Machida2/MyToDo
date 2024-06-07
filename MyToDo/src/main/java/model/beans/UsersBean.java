package model.beans;

public class UsersBean {
	private int user_id;
	private String name;
	private String e_mail;
	private String password;

	public UsersBean(int ui, String n, String e, String p) {
		user_id = ui;
		name = n;
		e_mail = e;
		password = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}
