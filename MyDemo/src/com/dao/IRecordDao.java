package com.dao;

import java.util.ArrayList;

import com.entity.Record;
import com.entity.Staff;

public interface IRecordDao {
	public Staff login(String username,String password);
	public ArrayList<Record> searchByNo(String username,int up);
	public boolean update(Staff staff);
	public boolean deleteById(String id);
	public boolean insert(Record record);
}
