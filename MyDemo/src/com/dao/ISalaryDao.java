package com.dao;

import java.util.ArrayList;

import com.entity.Staff;

public interface ISalaryDao {
	public ArrayList<Staff> searchByNo(String username,int up);
	public boolean update(Staff staff);
}
