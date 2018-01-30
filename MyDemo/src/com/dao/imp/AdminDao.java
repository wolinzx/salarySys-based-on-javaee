package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.IAdminDao;
import com.entity.Admin;
import com.util.DBUtil;

public class AdminDao implements IAdminDao {

	public Admin login(String username, String password) {
		Admin admin = new Admin();
		DBUtil db=DBUtil.getInstance();
		Connection con=db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from t_admin where username=? and password=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return admin;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.close(con, pstmt, rs);
		}
	}

	public ArrayList<Admin> searchByNo(String username) {
		ArrayList<Admin> result=new ArrayList<Admin>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_admin where username like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+username+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				Admin admin=new Admin();
				admin.setUserName(rs.getString("username"));
				admin.setPassWord(rs.getString("password"));
				result.add(admin);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}


}
