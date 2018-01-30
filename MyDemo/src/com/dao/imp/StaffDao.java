package com.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.dao.IStaffDao;
import com.entity.Staff;
import com.util.DBUtil;

public class StaffDao implements IStaffDao {

	public Staff login(String username, String password) {
		Staff staff = new Staff();
		DBUtil db=DBUtil.getInstance();
		Connection con=db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from t_staff where username=? and password=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return staff;
			}else{
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally{
			db.close(con, pstmt, rs);
		}
	}

	public ArrayList<Staff> searchByNo(String username,int up) {
		// TODO Auto-generated method stub
		ArrayList<Staff> result=new ArrayList<Staff>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql;
		try {
			if(username!=""){			
				 sql="select * from t_staff where staffid like ? ";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setString(1, "%"+username+"%");
			}else{
				 sql="select * from t_staff limit ?,6";
				 pstmt = con.prepareStatement(sql);
				 pstmt.setInt(1, up);
			}		
			rs=pstmt.executeQuery();
			while(rs.next()){
				Staff staff = new Staff();
				staff.setStaffid(Integer.parseInt(rs.getString("staffid")));
				staff.setUserName(rs.getString("username"));
				staff.setPassWord(rs.getString("password"));
				staff.setSex(rs.getString("sex"));
				staff.setSeniority(rs.getInt("seniority"));
				staff.setBirth(rs.getDate("birth"));			
				staff.setPost(rs.getString("post"));
				staff.setDepid(rs.getString("depid"));
				staff.setPhone(rs.getString("phone"));
				result.add(staff);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}
	
	
	public boolean update(Staff staff) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="update t_staff set username=?,sex=?,seniority=?,birth=?,post=?,depid=?,phone=? where staffid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, staff.getUserName());
			pstmt.setString(2, staff.getSex());
			pstmt.setInt(3, staff.getSeniority());
			pstmt.setDate(4,java.sql.Date.valueOf(staff.getBirth().toString()));
			pstmt.setString(5, staff.getPost());
			pstmt.setString(6, staff.getDepid());
			pstmt.setString(7, staff.getPhone());
			pstmt.setInt(8, staff.getStaffid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
	
	public boolean deleteById(String id) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="delete a,b from t_staff a,t_salary b where a.staffid=? and b.staffid=? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
	
	public boolean insert(Staff staff,int salary) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="insert into t_staff(username,password,sex,seniority,birth,post,depid,phone) values(?,?,?,?,?,?,?,?)";
		String sql2="insert into t_salary(salary) values(?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, staff.getUserName());
			pstmt.setString(2, staff.getPassWord());
			pstmt.setString(3, staff.getSex());
			pstmt.setInt(4, staff.getSeniority());
			pstmt.setDate(5, java.sql.Date.valueOf(staff.getBirth().toString()));
			pstmt.setString(6, staff.getPost());
			pstmt.setString(7, staff.getDepid());
			pstmt.setString(8, staff.getPhone());
			
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1,salary);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}
}

