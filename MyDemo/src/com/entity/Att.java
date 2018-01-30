package com.entity;

import java.util.Date;

public class Att {
	private int staffid;
	private Date attdate;
	private String type;
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	

	public Date getAttdate() {
		return attdate;
	}
	public void setAttdate(Date attdate) {
		this.attdate = attdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
