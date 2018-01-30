package com.dao;

import com.entity.Department;

public interface IDepartmentDao {
	public boolean insert(Department users);
	public Department login(String no,String password);
}
