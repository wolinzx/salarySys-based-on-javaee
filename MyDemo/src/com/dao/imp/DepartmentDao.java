package com.dao.imp;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBUtil;
import com.dao.IDepartmentDao;
import com.entity.Department;

public class DepartmentDao implements IDepartmentDao {

	public boolean insert(Department users) {
		// TODO Auto-generated method stub
		return false;
	}

	public Department login(String username, String password) {
		Department department = new Department();
		DBUtil db=DBUtil.getInstance();
		Connection con=db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from t_department where username=? and password=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return department;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.close(con, pstmt, rs);
		}
	}

}
