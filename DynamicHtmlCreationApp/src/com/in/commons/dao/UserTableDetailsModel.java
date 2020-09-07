package com.in.commons.dao;

public class UserTableDetailsModel {
	
	private int Sno;
	private String Name;
	private String Keyword;
	private String URL;
	
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	@Override
	public String toString() {
		return "UserTableDetailsModel [Sno=" + Sno + ", Name=" + Name + ", Keyword=" + Keyword + ", URL=" + URL + "]";
	}
	
	
	
	
}
