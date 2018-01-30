package com.dao;

import java.util.ArrayList;

import com.entity.Admin;

public interface IAdminDao {
	public Admin login(String username,String password);
	public ArrayList<Admin> searchByNo(String username);
}
