package com.dao;

import java.util.ArrayList;

import com.entity.Att;
import com.entity.Staff;

public interface IAttDao {
	public Staff login(String username,String password);
	public ArrayList<Staff> searchByNo(String username,int up);
	public boolean update(Staff staff);
	public boolean deleteById(String id);
	public boolean insert(Att att);
}
