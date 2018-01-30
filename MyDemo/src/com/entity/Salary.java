package com.entity;

import java.util.Date;

public class Salary extends Att{
	private int staffid;
	private int salary;
	private int allsalary;
	private Date lastdate;
	
	public int getStaffid() {
		return staffid;
	}
	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAllsalary() {
		return allsalary;
	}
	public void setAllsalary(int allsalary) {
		this.allsalary = allsalary;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	
}
